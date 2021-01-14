Gabriela Acuña, Rol: 201973505-7

* Asumí que los sectores entregados siempre tenian formato válido,
con esto me refiero a que el primer punto solo se repite al final del sctor.

*Las preguntas se deben realzar al igual que en el pdf.

*Algoritmo Problema 1:

- Se compara cada segmento con todos los otros.
- Se toman 2 segmentos(AB y CD), uno se usa como referencia (AB), y para el otro se toman los puntos extremos(C y D). Se comparan los signos de las distancias de los puntos al segmento de referencia. Si tienen el mismo signo no se intersectan. Si tienen sigunos diferentes se intersectan. si alguna distancia es 0, quiere decir que uno de los puntos pertenece al segmento y para este caso eso no se considera interseccion). Despues de revisar los primeros 2 puntos, se pasa a los siguientes 2. Y al compararlo con todos los extremos de los segmentos posibles, se pasa al siguiente segmento.
- Por lo tanto si se tiene un sector, [A, B, C, D, E], con A = E.
	1° se compara AB con C y D, luego con D y E.
	2° Se compara BC con D y E.

- Linea 36: Casos especiales, si por ejempo se comparan los segmentos.[[11, 8], [9, 5]] y [[11, 3], [4, 3]]. Usando como referencia el primero, segun las distancias se concluiria que se intersectan, pero claramente no es así. por eso se "doble chequea" comparando las distancias al revez tambien.


*Algoritmo Problema 2:

- Me base en como funciona la funcion 'member' pero revisado de a 2 en vez de a 1. Este queda mas claro en el codigo que el anterior.