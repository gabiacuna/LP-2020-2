#lang scheme

;Si se incluyen negativos, se retorna 0 a menos que haya una suma mayor.

(define (monedas_iter l1)
  (define lenListas (length l1))
  (let ((sum1 0) (sum2 0) (lenListas lenListas) (l1 l1) (suma_temp 0))
    (do ((i 0 (+ i 1)))
      ((= i lenListas)    ;Se termina de iterar cuando se revisa la lista completa
       (if (< sum1 sum2)    ;Se retorna la suma mayor
           sum2
           sum1))
      (if (< sum1 sum2)    ;Se guarda la mayor suma hasta el momento, no se considera el elemento actual
          (set! suma_temp sum2)
          (set! suma_temp sum1))
      (set! sum1 (+ sum2 (car l1)))    ;Se actualiza la suma1 con el elemento actual + sum2 -> para evitar adyasencia
      (set! l1 (cdr l1))    ;Se elimina el elemento actual de la lista
      (set! sum2 suma_temp)     ;Se guarda la mayor suma de la iteracion anterior en sum2
      ))
  )
  
;usé el mismo algoritmo que para monedas_iter, solo cambie la implementacion

(define (monedas_rec l1)
  (let loop ((l1 l1) (sum1 0) (sum2 0))
    (if (not (null? l1))
        (cond
          ((<= sum1 sum2) (loop (cdr l1) (+ sum2 (car l1)) sum2))    ;se guarda en sum2 la suma previa mayor,
          ((> sum1 sum2) (loop (cdr l1) (+ sum2 (car l1)) sum1)))    ;y se actualiza sum1 con el elemento actual + sum2
        (if (< sum1 sum2)   ;Cuandola lista ya esta vacía se retorna la mayor suma
            sum2
            sum1))
    ))

