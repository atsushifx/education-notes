; parenthse
;  (racket構文における括弧の意味)
#lang racket

;  (e)    ; eを関数として評価
; ((e))  ; (e)、つまりeを関数として評価した結果を、関数として評価
;        ; (e)が引数なしの関数が返ってくるものと考える

;; practice: fact 階乗での例
; 正式
(define (fact n)
  (if (= n 0)
      1
      (* n (fact (- n 1)))))

;; 括弧による問題が起きる例
(define (fact1 n)
  (if (= n 0)
      (1)
      (* n (fact1 (- n 1)))))

;; n <> 0 : call fact (正しい関数)
(define (fact1b n)
  (if (= n 0)
      (1)
      (* n (fact (- n 1)))))

;; =
;(define (fact2 n)
;  (if = n 0
;      1
;      (* n (fact2 (- n 1)))))


; parameter に括弧をつけてはいけない
;
;(define (fact3 (n))
;  (if (= n 0)
;      1
;      (* n (fact3 (- n 1)))))

; 再帰呼び出し時に（）の付け忘れ
;  -> fact4 が変数として評価される
;
(define (fact4 n)
  (if (= n 0)
      1
      (* n fact4 (- n 1))
      ))

; 関数名のみ（）でくくる
;
(define (fact5 n)
  (if (= n 0)
      1
      (* n ((fact5) (- n 1)))
      ))

; 普通の式のように書く
; -> 
;
(define (fact6 n)
  (if (= n 0)
      1
      (n * (fact6 (- n 1)))
      ))


;; check function
(display "fact: ")
(fact6 5)
