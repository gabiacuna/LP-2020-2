#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

typedef struct{
    int base;
    char* numero;
}tbase;


int val(char c) 
{ 
    if (c >= '0' && c <= '9') 
        return (int)c - '0'; 
    else
        return (int)c - 'A' + 10; 
} 

int toB10(tbase* x){
    int len = strlen(x->numero), b = x->base, res = 0, p = 1;
    for (int i = 0; i < len; i++)
    {
        res += p*val(x->numero[len-i-1]);
        p*=b;
    }
    return res;    
}

int * toIntArr(tbase** arrOg, int len){
    int *res = (int*)malloc(sizeof(int)*len);
    for (int i = 0; i < len; i++)
    {
        res[i] = toB10(arrOg[i]);
    }
    return res;   
}


int main()
{
    /*tbase* guau = malloc(sizeof(tbase));
    guau->base = 2;
    guau->numero = strdup("10");
    printf("el valor de 10 en base 2 en base 10 es: %d\n", toB10(guau));
    guau->base = 8;
    guau->numero = strdup("14");
    printf("el valor de 14 en base 8 en base 10 es: %d\n", toB10(guau));
    guau->base = 4;
    guau->numero = strdup("13");
    printf("el valor de 13 en base 4 en base 10 es: %d\n", toB10(guau));
    free(guau);*/

    tbase *arr[3];
    for (int i = 0; i < 3; i++)
    {
        arr[i]= malloc(sizeof(tbase));
    }
    
    arr[0]->base = 2;
    arr[0]->numero = strdup("10");
    arr[1]->base = 8;
    arr[1]->numero = strdup("14");
    arr[2]->base = 4;
    arr[2]->numero = strdup("13");

    int* arrI = toIntArr(arr, 3);

    for (int i = 0; i < 3; i++)
    {
        printf("arr[%d] = %d\n",i, arrI[i]);
    }
    
    free(arrI);
    for (int i = 0; i < 3; i++)
    {
        free(arr[i]->numero);
        free(arr[i]);
    }

    return 0;
}
