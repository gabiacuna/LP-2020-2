#include"heap.h"

#include<string.h>

int cmpString(void* a, void* b){
    char a2, b2;
    a2 = ((char*)a)[0];
    b2 = ((char*)b)[0];
    if (((char*)a)[0] >= 'A' && ((char*)a)[0] <= 'Z') a2 = ((char*)a)[0] + 32;  //Si la primera letra esta en Mayuscula se Cambia a minuscula :)
    if (((char*)b)[0] >= 'A' && ((char*)b)[0] <= 'Z') b2 = ((char*)b)[0] + 32;
    return a2>b2?0:1;
}

int cmpEntero(void* a, void* b){
    return *((int*)a)>*((int*)b)?0:1;
}

int cmpFlotante(void* a, void* b){
    return *((float*)a)<*((float*)b)?0:1;
}

int cmpArreglo(void* a, void* b){
    int na = ((int *)a)[0], suma = 0;
    int nb = ((int *)b)[0], sumb = 0;
    
    while (na>0)
    {
        suma += ((int *)a)[na];
        na--;
    }

    while (nb>0)
    {
        sumb += ((int *)b)[nb];
        nb--;
    }

    return suma>sumb?0:1;
}

/*****
*   int val
******
*   Retorna el valor literal de un int escrito en un char. (si el char es '1' retorna 1, no el calor ascii)
******
*   Input:
*       char c : caracter que contiene un numero entero.
******
*   Output:
*       int : el int escrito en el char.
*****/
int val(char c) 
{ 
    if (c >= '0' && c <= '9')   //revisa si el char es un numero entero para así retornarlo en int
        return (int)c - '0'; 
    else if (c == '-')
        return -1;
    else
        return -2;  //Si no era un numero retorna -2 de
} 

/*****
*   int toB10
******
*   Transforma un numero de cualquier base a base 10.
******
*   Input:
*       tbase* x : Es el struct del que se extraera el numero en base 2,4,8 o 10.
******
*   Output:
*       int : el numero en base 10 (Si el numero ya estaba en base 10, se devuelve igual).
*****/
int toB10(tbase* x){
    int len = strlen(x->numero), b = x->base, res = 0, p = 1;

    if(val(x->numero[0]) == -1){
        for (int i = 0; i < len-1; i++)
        {
            res += p*val(x->numero[len-i-1]);
            p*=b;
        }
        return res*-1;
    }
    for (int i = 0; i < len; i++)
    {
        res += p*val(x->numero[len-i-1]);
        p*=b;
    }
    return res;   
}

int cmpStuct(void* a, void* b){
    int a10 = toB10((tbase*)a);
    int b10 = toB10((tbase*)b);
    return a10<b10?0:1;
}

void printString(void* x){
    printf("'%s'", (char*)x);
}

void printEntero(void* x){
    printf("%d", *((int*)x));
}

void printFlotante(void* x){
    printf("%f", *((float*)x));
}

void printArreglo(void* x){
    int i = ((int*)x)[0], n = 1;
    printf("[");
    while (i>1)
    {
        printf("%d ", ((int*)x)[n]);
        i--;
        n++;
    }
    if(i==0){
        printf("0]");
        return;
    }
    printf("%d", ((int*)x)[n]);
    printf("]");
}

void printStruct(void* x){  //Imprime (base, numero)
    printf("(%d,", ((tbase*)x)->base);
    printf(" %s)", ((tbase*)x)->numero);
}

void heapSort(void** arr ,int N,int (*comparador)(void*, void*) ,void (*printElem)(void*)){
    tHeap *h = newHeap(comparador, printElem);

    if(!is_goodHeap(h))
        return;
    for (int i = 0; i < N; i++)
    { 
        pushHeap(h, arr[i]);
    }
    printf("[");
    for (int j = 0; j < N-1; j++)
    {
        h->print(topHeap(h));
        printf(" ,");
        popHeap(h);
    }
    h->print(topHeap(h));
    printf("]\n");
    deleteHeap(h);  
}

void maxBases(tbase* arr, int N, int cantMax){

    tHeap *h = newHeap(cmpStuct, printStruct);

    for (int i = 0; i < N; i++)
    {
        pushHeap(h, (void*)&arr[i]);
    }

    printf("[");
    for (int j = 0; j < cantMax-1; j++)
    {
        h->print(topHeap(h));
        printf(" ,");
        popHeap(h);
    }
    h->print(topHeap(h));
    printf("]\n");

    deleteHeap(h);
}



