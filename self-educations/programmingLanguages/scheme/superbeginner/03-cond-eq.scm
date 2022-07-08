#lang racket
;;;; 比較について、もっと詳しく

;; = : 数値専用
(display "\n '=': 数値の比較")
(= 5 5) 
(= 6/5 (+ 1 1/5))

;; eq
(display "\n 'eq': オブジェクトref\n")
(define a "Hello1")
(define b "Hello2")
(eq? a b)
(define c "Hello1")
(eq? a c)

;; eqv
(display "\n 'eqv': オブジェクトvalue(Java's equal)\n")
(eqv? "hello" "hello")

;; equal?
(display "\n 'equal:':オブジェクトvalue(再帰)\n")
(equal? '("Hello" "racket") '("Hello" "racket"))



