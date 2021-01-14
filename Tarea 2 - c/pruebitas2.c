#include<stdio.h>
#include<stdlib.h>

int main(){
	void ** heap = malloc(sizeof(void*)*(3));
	void * a;
	int b = 5;
	a = &b;
	heap[0] = a;
	printf("%d\n", *((int*)heap[0]));
	free(heap);
}