#lang scheme

(define (preorden arbol funcion)
  (if (null? arbol)
      '()    ;si se llego a una hoja vacia se retorna una lista vacia, y nose agrega nada a la lista
      (append (funcion (car arbol))    ;se le aplica la funcion dada al valor de la raiz del subarbol
              (preorden (cadr arbol) funcion)   ;se llama la funcion con el subarbol izq. para evaluar en preorden
              (preorden (caddr arbol) funcion))))    ;se llama con el subarbol derecho para mantener el orden