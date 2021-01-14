
#include<stdio.h>

int func(int a, int b){
    return a + b;
}

int main()
{
    int res;
    int (*ptr)(int, int) = func;
    
    res = ptr(10, 21);
    printf("%d", res);
    return 0;
}
