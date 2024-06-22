; process list (with recursion)
#lang racket
(provide (all-defined-out))

; list processing: null, cons, null?, car, cdr
; we won't use pattern-matching in Racket
(define (sum-list xs) ;;  calc sum from list / can't calc atom number;
  (if (null? xs)
      0
      (+ (car xs) (sum-list (cdr xs)))
      ))

(display "sum-list: ")
(sum-list `[6 8]) ; not quoted as eval first number as procedure


;; append
;; 2つのリストを1つのリストにつなげる
(define
  (my-append-list xs ys)
  (if (null? xs)
      ys
      (cons (car xs) (my-append-list (cdr xs) ys))
      ))

(display "my-append-list: ")
(my-append-list '['a 'c] '[ 'a 'b])
; -> ['a 'c 'a 'b]

;; map
; list xsの各項目に対して、関数fを実行する
(define (my-map f xs)
  (if (null? xs)
      null  ; '() returns '()
      (cons
       (f (car xs))  ; 最初の項目に関数fを実行
       (my-map f (cdr xs)) ; 末尾再帰
       )
      ))

(define (my-inc x)
  (+ x 1))

(display "exec my-map: ")
(my-map my-inc '[5 6])  ; param f is func not quoted
(display "my-map with lambda: ")
(my-map (lambda (x) (+ x 1)) '[ 1 2 3] )  ;; parm f can call with lambda


