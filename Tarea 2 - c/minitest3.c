#include<stdio.h>
#include<stdlib.h>

typedef struct
{
    int num;
    void** arr;

    int (*cmp)(void*, void*);
    void (*print)(void*);
}chinn;

int cmpEntero(void* a, void* b){
    return *((int*)a)>*((int*)b)?0:1;
}

void printInt ( void* x ) {
  printf("%d\n", *((int*)x));
}

void printHeap(chinn* n){
    for (int i = 0; i < 3; i++)
    {
        n->print(n->arr[i]);
    }
    
}

int main(){
    chinn *n = (chinn *)malloc(sizeof(chinn));
    n->num = 5;
    n->arr = malloc(sizeof(void*)*3);
    n->cmp = cmpEntero;
    n->print = printInt;

    void *a,*b, *c;
    int d = 9, e=10, f =8;

    a = &d;
    b=&e;
    c=&f; 

    n->arr[0] = a;
    n->arr[1] = b;
    n->arr[2] = c;

    printHeap(n);

    free(n->arr);
    free(n);
}