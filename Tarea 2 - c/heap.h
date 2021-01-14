#ifndef __HEAP_H__
#define __HEAP_H__

#include <stdio.h>
#include <stdlib.h>

typedef struct{
    void** heap;

    int capacidad;
    int nElems;
    int error;

    int (*cmp)(void*, void*);   //Guarda la funcion de comparación a usar en el heap
    void (*print)(void*);       //Guarda la funcion de impreción a usar en el heap
}tHeap;

typedef struct{
    int base;
    char* numero;
}tbase;

void maxBases(tbase* arr, int N, int cantMax);

tHeap* newHeap(int (*comparador)(void*, void*), void (*printElem)(void*));

void deleteHeap(tHeap* h);

void clearHeap(tHeap* h);

void* topHeap(tHeap* h);

int sizeHeap(tHeap* h);

void popHeap(tHeap* h);

void pushHeap(tHeap* h, void* elem);

int is_goodHeap(tHeap* h);

int emptyHeap(tHeap* h);

void printHeap(tHeap* h);


#endif
