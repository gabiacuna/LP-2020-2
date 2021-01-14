Gabriela Acuña
rol: 201973504-7

Realice toda la tarea desde Linux (Manjaro Gnome).

Respecto a la implementacion, use las librerias swing, awt e imageIo. La clase Juego la extendi de JFrame, para poder hacer los botones con imagenes.
-> En el package Cartas, estan las clases Carta, Estudio, Ramo y Evento. En el package Juego, estan las clases Tablero, Mano y Mazo. 
-> En el directorio classes quedan todos los archivos.class luego de correr el make. Creo que esto no funciona desde Windows así que deje comentado en
el makefile como sería para que funcione en Windows y ahí no serviria de nada el directorio classes.

para crear clases: 
    make
para correr el juego:
    java -cp \classes Juego
    
para borrar las clases creadas:
    male clean
    
interaccion con la interfaz :

-La forma del tablero es:
_________________________________
| __________________  __________ |      -En el area Menu, se encuentran las opciones jugar carta y terminar turno. Jugar Carta permite seleccionar una carta
||					||			||     de la mano del jugador y, si es de estudio, seleccionar sobre que ramo aplicarla o si es de evento, dependiendo del
||      Mazos		||	 Menu	||     tipo, sobre que area aplicarla. En el area Menu tambien se muestra, cuantas horas quedan en el turno y cuantos ramos
||__________________||__________||     aprobados y reprobados van. [Ojo, una vez que se selecciona una carta para jugar de la mano, se debe tomar la siguiente
| ______________________________ |     elección o la carta se perderá]
||								||      
||                              ||      -Para iniciar un juego, luego de correr el programa con el comando make, se debe apretar empezar. Solo se pueden
||			Ramos				||     robar cartas al comienzo del turno, basta con que se seleccione la opcion Jugar Carta, para que el turno se de
||				    			||     por iniciado y ya no se podran robar cartas del mazo Universidad.
||								||     
||______________________________||      Para termiar el turno, se debe apretar la opcion Terminar Turno, se puede hacer en cualquier momento de la partida.
| ______________________________ |     Solo cuando se termine el turno se daran a conocer los promedios de los ramos en el tablero.
||								||
||								||      El juego se termina cuando se gana o se pierde, despues de esto se puede empezar un juego nuevo o no.
||			Mano				||
||                              ||      Paraver la info de una carta, hay que presionarla.
||______________________________||     
|________________________________| 

*Intente considerar todo lo aclarado en la ayudantía y en el foro pero se me puede haber pasado algo.

Perdon por lo largo :c