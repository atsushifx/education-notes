; Programming Languages B
; Section 5: dynamic type

#lang racket
(provide (all-defined-out))

;; Racketでは、型が違ったatomノリスとが作成できる

(define xs '[4 5 6])
(define ys (list (list 5 (list 5 0)) 6 7 (list 8) 9 2 3 (list 0 1)))
(define zs [list #f "hi" 14])

;; functions
; nested listに対応したsum
(define (sum1 xs)
  (if (null? xs)
      0
      (if (number? (car xs))
          (+ (car xs) (sum1 (cdr xs))) ; car xsが数値以外は、runtime error
          (+ (sum1 (car xs)) (sum1 (cdr xs))))))

; 数値以外の型に対応する
(define (sum2 xs)
  (if (null? xs)
      0
      (cond
        [(list? (car xs)) (+ (sum2 (car xs)) (sum2 (cdr xs)))]
        [(number? (car xs)) (+ (car xs) (sum2 (cdr xs)))]
        [else (sum2 (cdr xs))]	;; 数値以外は、無視して加算しない
        )
      ))

;; print display
(displayln (sum1 xs))
(displayln (sum1 ys))

;; input from repl
(displayln (sum2 [list "hi" [list [list 4]] 5 [list 7 2]]))
(displayln (sum2 [list "hi" [list [list 4]] #f [list 7 2]]))

; (display (sum2 "hi"))	;; atom/string なので、runtime error

(println (cdr zs))
