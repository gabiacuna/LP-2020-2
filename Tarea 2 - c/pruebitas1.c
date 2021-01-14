#include<stdio.h>
#include<string.h>
#define type(T) _Generic( (T), char *: 1, int: 2, float: 3, int *: 4,default: 0)

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
    int nb = ((int *)a)[0], sumb = 0;
    
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

void printString(void* x){
    printf("%s\n", (char*)x);
}

void printEntero(void* x){
    printf("%d\n", *((int*)x));
}

void printFlotante(void* x){
    printf("%f\n", *((float*)x));
}

void printArreglo(void* x){
    int i = ((int*)x)[0], n = 1;
    while (i>0)
    {
        printf("%d ", ((int*)x)[n]);
        i--;
        n++;
    }
    printf("\n");
}

int main(){

    void *a, *b;
    int ar1[] = {3,1,1,1};
    int ar2[] = {3,2,2,2};

    a = ar1;
    b = &(ar2[0]);

    printf("%d\n", cmpArreglo(a, b));

    printArreglo(a);
    printEntero(b);
}