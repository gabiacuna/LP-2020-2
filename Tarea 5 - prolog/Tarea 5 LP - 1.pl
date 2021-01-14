/*
-Lista que representa al sector, con al menos 3 segmentos: [P1, P2, X, Y|T]
———————————-
Primera regla que revisa si se intersectan el primer y tercer segmento, luego pasa al 4to segmento. */
sector([P1, P2, X, Y|T]):-
    !, inter([P1, P2], [X ,Y]),	/*Cut para que si inter retorna false, no haga backtracking*/
    sector([P1, P2, Y|T]),!.	/*Avanza al siguiente "par de puntos"*/

/*
-Lista que representa al sector, con al menos 3 segmentos: [P1, P2, X, Y|T]
———————————-
Pasa al siguiente segmento que se tomara como referencia. */
sector([P1, P2, X, Y|T]):-
    sector([P2, X, Y|T]), !.	/*avanza al siguiente segmento*/

/*
-Un sector con 3 puntos (2 segmentos): [A, B, C]
———————————-
Ya que estos no se podran intersectar, es el caso base. */
sector([A, B, C]).

/*
-Segmento que se usará como referencia: [[X1,Y1], [X2,Y2]]
-Segmento del cual se usaran sus extremos para revisar si se intersecta con el primero: [[X3,Y3], [X4,Y4]]
———————————-
Calcula la distancia entre el segmento de referencia y los puntos extremo del segundo segmento. */
inter([[X1,Y1], [X2,Y2]], [[X3,Y3], [X4,Y4]]):-
    D1 is ((X3 - X1) * (Y2 - Y1)) - ((Y3 - Y1)*(X2 - X1)),	/*Distancia segmento-punto*/
    D2 is ((X4 - X1) * (Y2 - Y1)) - ((Y4 - Y1)*(X2 - X1)),
    comp(D1, D2).

/*
-Segmento que se usará como referencia: [[X3,Y3], [X4,Y4]]
-Segmento del cual se usaran sus extremos para revisar si se intersecta con el primero: [[X1,Y1], [X2,Y2]]
———————————-
Usa el segundo segmento de la regla anterior como referencia, para casos especiales (explicados en el readme).*/
inter([[X3,Y3], [X4,Y4]], [[X1,Y1], [X2,Y2]]):-
    D1 is ((X3 - X1) * (Y2 - Y1)) - ((Y3 - Y1)*(X2 - X1)),	/*Distancia segmento-punto*/
    D2 is ((X4 - X1) * (Y2 - Y1)) - ((Y4 - Y1)*(X2 - X1)),
    comp(D1, D2).

/*
-Numero que representa una distacia punto-segmento: A
-Numero que representa una distacia punto-segmento: B
———————————-
Se acepta si ambos tienen el mismo signo. */
comp(A, B):-		/*Si intersectan retorna False*/
    A > 0 , B > 0.

/*
-Numero que representa una distacia punto-segmento: A
-Numero que representa una distacia punto-segmento: B
———————————-
Se acepta si ambos tienen el mismo signo. */
comp(A, B):-
    A < 0 , B < 0.

/*
-Numero que representa una distacia punto-segmento: A
-Numero que representa una distacia punto-segmento: B
———————————-
Se revisa si alguna distancia es 0, significando que son segmentos adhyasentes. */
comp(A, B):-	/*Si alguno pertenece al segmento retorna True ver como hacer que evalue ambos*/
    A = 0.

/*
-Numero que representa una distacia punto-segmento: A
-Numero que representa una distacia punto-segmento: B
———————————-
Se revisa si alguna distancia es 0, significando que son segmentos adhyasentes. */
comp(A, B):-
    B = 0.