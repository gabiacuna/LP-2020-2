Gabriela Acuña
rol : 201973504-7

Consideraciones:
*   mi archivo Makefile se llama makefile y no MAKEFILE por que el terminal no me reconocia el comando MAKE, pero si el make. Espero que no sea un problema.
*   los comentarios de las funciones extras creadas se encuentran en las mismas definiciones de ellas, no en el header.
*   agregue algunos campos al struct del heap, los cuales comente en la definicion del struct en el header heap.h
*   realize todo mi codigo y sus pruebas en sistema ArchLinux (Manjaro).
*   Asumi que se les pasarian los datos correctos (como que en maxBases no se pasara un numero invalido como "12h0").
*   Utilize las librerias string.h , stdlib.h y stdio.h .

Para compilar se puede usar
    make
o
    make heap

Considere que la funcion main sera agregada al final de archivo main.c

para correr el programa usar:
    ./heap
(el ejecutable queda con nombre heap)

para correr el programa con valgrind (sin flags):
    make valg

para eliminar los arch creados con la compilacion se puede usar:
    make clean