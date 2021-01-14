#include<stdio.h>
#include<stdlib.h>

int cmpFlotante(void* a, void* b){
    return *((float*)a)>*((float*)b)?0:1;
}
void printString(void* x){
    printf("%s\n", (char*)x);
}

int main(){
    //Test para float

    float *arrf[4];
    float a4 = 1.2 , b4 = 2.3, c4 = 1.1, d4 = 3.3;
    arrf[0] = &a4;
    arrf[1] = &b4;
    arrf[2] = &c4;
    arrf[3] = &d4;

    printf("a vs b = %d\n", cmpFlotante((void*)&a4, (void*)&b4));
    printf("a vs c = %d\n", cmpFlotante((void*)&a4, (void*)&c4));
    printf("c vs b = %d\n", cmpFlotante((void*)&c4, (void*)&b4));
    printf("d vs b = %d\n", cmpFlotante((void*)&d4, (void*)&b4));
}