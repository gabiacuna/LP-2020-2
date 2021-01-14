#lang scheme

;;(fact n)
;;utiliza recurcion de cola para calcular el valor de n!.
;;n!.
(define fact
  (lambda(n)
    (let fact ((i n) (a 1))
      (if (= i 0)
          a
          (fact (- i 1) (* a i))
          ))))
;;se crea una funcion que retorna la promesa para calcular el valor correspondiente a una
;;posicion en un piso del triangulo de pascal.
(define (formula n i) (delay (/ (fact n) (* (fact i) (fact (- n i))))))

(define (lazypascal n)
  (let loop ((n n) (i 0) (piso '()))
    (define valor (formula n i))
    (cond
      ((zero? n) '(1))    ;caso base de la pidamide
      ((zero? i) (loop n (+ i 1) '(1)))    ;caso primer numero del piso siempre es 1
      ((and (= i 1) (> i 2)) (loop n (+ i 1) (list piso n)))    ;caso segundo numero del piso, casi siempre es n
      ((and (> i (/ n 2)) (odd? n)) (append piso (reverse piso)))    ;Cuando se llega a la mitad del piso se repite la primera mitad
      ((and (= i (/ n 2)) (even? n)) (append piso (list (force valor)) (reverse piso)))
      (else (loop n (+ i 1) (append piso (list (force valor))))))))
