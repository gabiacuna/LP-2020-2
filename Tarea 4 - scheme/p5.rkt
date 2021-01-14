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

(define (seno x)
  (let sumatoria ((x x) (n 0) (suma 0))
    (let* ((x x) (n n) (suma suma)
                   (term (+ (* n 2) 1))    ;Se calulan los terminos necesarios para la sumatoria
                   (arriba (expt x term))
                   (abajo (fact term)))
    (if (= n 500)    ;n=500 da presicion hasta sin(360).
      (exact->inexact suma)    ;Para que no retorne fracciones.
      (if (odd? n)
          (sumatoria x (+ n 1) (- suma (/ arriba abajo)))    ;En remplazo de -1^n solo se revisa la pariedad de n.
          (sumatoria x (+ n 1) (+ suma (/ arriba abajo))))))))