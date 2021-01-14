/*
-Lista con minimo 2 elementos: [X,Y|T]
-Segunda lista que representa al segundo sector: L2
———————————-
Manda a revisar si el primer segmento del sector 1 pertenece al sector 2. (Se asume que ambos sectores ingresados son validos)*/
ady([X,Y|T], L2):-
    check(X, Y, L2).

/*
-Lista 1 con el primer segmento: [X, Y|T]
-Lista 2 con el segundo segmento: L2
———————————-
Luego de probar el chequeo con el primer segmento de L1, hace recursividad para pasar al siguiente segmento. */
ady([X,Y|T], L2):-
    ady([Y|T], L2), !.	/*Pasa al siguiente segmento ("Acortando" a L1)*/

/*
-Punto 1 del Segmento a revisar del sector 1: X
-Punto 2 del Segmento a revisar del sector 1: Y
-Sector 2, se revisan los primeros 2 elementos : [H1, H2|T]
———————————-
Se revisa si los 2 puntos del segmento del sector 1 estan en el sector 2 y son adhyacentes. */
check(X, Y, [H1, H2|T]):-
    X = H1,
    Y = H2, !.

/*
-Punto 1 del Segmento a revisar del sector 1: X
-Punto 2 del Segmento a revisar del sector 1: Y
-Sector 2, se revisan los primeros 2 elementos : [H1, H2|T]
———————————-
Revisa lo mismo que la regla anterior, pero en caso de que esten en sentido opuesto. */
check(X, Y, [H1, H2|T]):-
    X = H2,
    Y = H1, !.

/*
-Punto 1 del Segmento a revisar del sector 1: X
-Punto 2 del Segmento a revisar del sector 1: Y
-Sector 2, separado en head y tail.
———————————-
"Avanza" en el sector 2, para revisar el siguiente par de puntos. */
check(X, Y, [H|T]):-
    check(X,Y,T).