#include<stdio.h>
#define type(T) _Generic( (T), char *: 1, int: 2, float: 3, int *: 4,default: 0)

void func ( void (*f)(int) ) {
  for ( int ctr = 0 ; ctr < 5 ; ctr++ ) {
    (*f)(ctr);
  }
}

void print ( int x ) {
  printf("%d\n", x);
}

int main()
{
    func(print);

    void *c;
    int a = 555;
    c = &a;

    print(*(int *)c);
    printf("tipo a:\t%d\n", type(a));

    void *cs;
    char* s = "olaola";
    cs = s;

    printf("%s\n",(char *)cs);
    printf("tipo s:\t%d\n", type(s));
    
    void *cf;
    float f = 1.23;
    cf = &f;
    printf("%f\n", *(float*)cf);
    printf("tipo f:\t%d\n", type(f));

    void *arri;
    int i[] = {1,2,3};
    arri = i;
    int n = 3;
    while (n--)
    {
        printf("%d\n", ((int *)arri)[n]);
    }
    printf("tipo i:\t%d\n", type(i));

    printf("tipo char:\t%d\n", type('b')); // lo reconoce como un int

    return 0;
}
