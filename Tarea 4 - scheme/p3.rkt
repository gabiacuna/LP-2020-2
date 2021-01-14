#lang scheme
;;(get-sublist lista num1 num2 i)
;;A travez de resurción simple, retorna una sublista de lista desde el indice num1 hasta num2. i es un contador.
;;Una sublista de lista.
(define get-sublist
  (lambda (lista num1 num2 i)
    (if (null? lista)
        '()
        (if (and (>= i num1) (< i num2))
            (cons (car lista) (get-sublist (cdr lista) num1 num2 (+ i 1)))
            (get-sublist (cdr lista) num1 num2 (+ i 1))))))


(define (crossrd l1 l2)
  (define largo (length l1))
  (define cortes (sort (take (shuffle (range (add1 largo))) 2) <))    ;Se eligen 2 numeros random entre 0 y el largo
  (define corte_1 (car cortes))    ;se extraen los puntos de corte
  (define corte_2 (cadr cortes))
  ;se juntan los tecios segun los cortes:
  (define l_nueva1 (append (get-sublist l1 0 corte_1 0) (get-sublist l2 corte_1 corte_2 0) (get-sublist l1 corte_2 largo 0)))
  (define l_nueva2 (append (get-sublist l2 0 corte_1 0) (get-sublist l1 corte_1 corte_2 0) (get-sublist l2 corte_2 largo 0)))
  (display l_nueva1)
  (display l_nueva2);usé display ya que no se pueden retornar 2 valores
  )