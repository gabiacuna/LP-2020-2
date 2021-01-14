#include"heap.h"

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
    for (int i = 1; i < h->nElems; i++)
    {
        h->print(h->heap[i]);
        printf(" ,");
    }
    printf("]\n");
}
