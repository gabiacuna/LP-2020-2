#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct{
    void** heap;

    int capacidad;
    int nElems;
    int error;

    int (*cmp)(void*, void*);
    void (*print)(void*);
}tHeap;

typedef struct{
    int base;
    char* numero;
    int nb10;   //Agregue este campo para guardar el mismo numero pero en base decimal :)
}tbase;

tHeap* newHeap(int (*comparador)(void*, void*), void (*printElem)(void*)){
    tHeap* h = (tHeap*)malloc(sizeof(tHeap));
    if(h == NULL){
        return NULL;
    }

    h->error = 0;
    h->capacidad = 1;
    h->nElems = 0;
    h->heap = malloc(sizeof(void*)*(h->capacidad+1));
    if(h->heap == NULL) h->error = 1;

    h->cmp = comparador;
    h->print = printElem;

    return h;
}

void deleteHeap(tHeap* h){
    free(h->heap);
    free(h);
    return;
}

void clearHeap(tHeap* h){
    h->nElems = 0;
    return;
}

void* topHeap(tHeap* h){
    return h->heap[1];
}

int sizeHeap(tHeap* h){
    return h->nElems;
}

/*****
*   void swapElemsHeap
******
*   intercambia dos elementos de un heap.
******
*   Input:
*       tHeap* h : heap en el que se intercambiaran dos elementos.
*       int i    : indice en el que esta el primer elemento a intercambiar en el heap.
*       int j    : indice en el que esta el segundo elemento a intercambiar en el heap.
*****/
void swapElemsHeap(tHeap* h, int i, int j){
    void* tmp = h->heap[i];
    h->heap[i] = h->heap[j];
    h->heap[j] = tmp;
    return;
}

/*****
*   void flotar
******
*   flota un nodo dentro del heap, hasta dejarlo en la posicion que le corresponda.
******
*   Input:
*       tHeap* h : heap al cual pertenece el nodo a flotar.
*       int i   : índice del nodo a flotar en el arreglo.
*****/
void flotar(tHeap* h, int i){
    while (i>1 && h->cmp(h->heap[i], h->heap[i/2]))
    {
        swapElemsHeap(h, i, i/2);
        i = i/2;
    }
}

/*****
*   void hundir
******
*   hunde un nodo en el heap para dejarlo en su pocisión correspondiente.
*
******
*   Input:
*       tHeap* h : puntero al heap al cual pertenece el nodo a hundir.
*       int i   : index del nodo a hundir en el arreglo que contiene al heap.
*****/
void hundir(tHeap*h, int i){
    while (2*i+1 <= h->nElems && (h->cmp(h->heap[2*i], h->heap[i])|| h->cmp(h->heap[(2*i)+1], h->heap[i])))
    {
        if (h->cmp(h->heap[2*i], h->heap[i]) && h->cmp(h->heap[2*i], h->heap[(2*i)+1])){
            swapElemsHeap(h,2*i,i);
            i = i*2;
        }else{
            swapElemsHeap(h,(2*i)+1,i);
            i = (i*2)+1;
        } 
    }
    if(2*i <= h->nElems && h->cmp(h->heap[2*i], h->heap[i])){
        swapElemsHeap(h, 2*i,i);
    }
}

void popHeap(tHeap* h){
    h->heap[1] = h->heap[h->nElems]; //Reemplazamos por el ultimo elemento
    h->nElems--; //"Sacamos" el último elemento del arreglo
    if (h->nElems > 1 && (h->cmp(h->heap[2],h->heap[1]) || h->cmp(h->heap[3],h->heap[1])))
    {
        hundir(h, 1);
    }
}

void growHeap(tHeap* h){
    h->capacidad *= 2;
    void** temp = (void*)realloc(h->heap, sizeof(void*)*(h->capacidad+1));
    if(temp == NULL){
        h->error = 1;
        return;
    }
    h->heap = temp;
    return;
}

void pushHeap(tHeap* h, void* elem){
    int i = h->nElems+1;
    if(i > h->capacidad){
        growHeap(h);
        if(!is_goodHeap(h)){
            printf("Error al expandir la memoria del heap\n");
            return;
        }
    }
    h->heap[i] = elem;
    flotar(h, i);
    h->nElems++;
}

int is_goodHeap(tHeap* h){
    return !h->error;
}

int emptyHeap(tHeap* h){
    return !(h->nElems > 0);
}

void printHeap(tHeap* h){
    printf("[");
    for (int i = 0; i < h->nElems; i++)
    {
        h->print(h->heap[i]);
        printf(" ,");
    }
    printf("]\n");
}

int cmpString(void* a, void* b){
    return ((char*)a)[0]>((char*)b)[0]?0:1;
}

int cmpEntero(void* a, void* b){
    return *((int*)a)>*((int*)b)?0:1;
}

int cmpFlotante(void* a, void* b){
    return *((float*)a)>*((float*)b)?0:1;
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

int cmpStuct(void* a, void* b){
    return ((tbase*)a)->nb10 > ((tbase*)b)->nb10?1:0;
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
    printf("%d", ((int*)x)[n]);
    printf("]");
}

void printStruct(void* x){
    printf("%s", ((tbase*)x)->numero);
}

void heapSort(void** arr ,int N,int (*comparador)(void*, void*) ,void (*printElem)(void*)){
    tHeap *h = newHeap(comparador, printElem);

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

int val(char c) 
{ 
    if (c >= '0' && c <= '9') 
        return (int)c - '0'; 
    else
        return (int)c - 'A' + 10; 
} 

void toB10(tbase* x){
    int len = strlen(x->numero), b = x->base, res = 0, p = 1;
    for (int i = 0; i < len; i++)
    {
        res += p*val(x->numero[len-i-1]);
        p*=b;
    }
    x->nb10 = res;   
}

void maxBases(tbase* arr, int N, int cantMax){

    tHeap *h = newHeap(cmpStuct, printStruct);

    for (int i = 0; i < N; i++)
    {
        toB10(&arr[i]);
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


int main(){
    int *arrI[15];
    int a3[] = {4, 9 ,4, 3, 2}, b3[] = {2, 1, 3}, c3[] = {3, 9, 7, 4}, d3[]={3, 9, 78, 5};
    int e3[] = {6, 1,2,3,4,5,6}, f3[] = {2, 1, 1}, g3[] = {3, 9, 10, 4}, h3[]={5, 9, 78, 5, 1, 2};
    int i3[] = {9, 9 ,4, 3, 2,5,4,3,7,9}, j3[] = {2, 1, 20}, k3[] = {3,19, 7, 4}, l3[]={3, 29, 78, 35};
    int m3[] = {4, 93 ,43, 3, 2}, n3[] = {2, 61, 35}, o3[] = {3, 79, 7, 4};
    arrI[0] = a3;
    arrI[1] = b3;
    arrI[2] = c3;
    arrI[3] = d3;
    arrI[4] = e3;
    arrI[5] = f3;
    arrI[6] = g3;
    arrI[7] = h3;
    arrI[8] = i3;
    arrI[9] = j3;
    arrI[10] = k3;
    arrI[11] = l3;
    arrI[12] = m3;
    arrI[13] = n3;
    arrI[14] = o3;

    heapSort((void**)arrI, 15, cmpArreglo, printArreglo);

    printf("\t~~~~\t\n");
    /*Test para float

    float *arrf[4];
    float a4 = 1.2 , b4 = 2.3, c4 = 1.1, d4 = 3.3;
    arrf[0] = &a4;
    arrf[1] = &b4;
    arrf[2] = &c4;
    arrf[3] = &d4;

    heapSort((void**)arrf, 4, cmpFlotante, printFlotante);*/

    return 0;
}