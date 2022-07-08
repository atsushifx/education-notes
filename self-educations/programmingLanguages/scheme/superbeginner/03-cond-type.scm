;; cond-type: 値の型のチェック
#lang racket

;; boolean型
(display "\n bool\n")
(boolean? #f)
(boolean? (+ 5 6))
(boolean? (< 9 8))

;; 文字列
(display "\n 文字列\n")
(string? "これは文字列")
(string? 9)
(string? #t)

;; 数値型
(display "\n 数値型\n")
(number? 8)
(number? 7.4)
(number? 3/4)
(number? (+ 1 (/ 2 3)))


;; 整数型
(display "\n 整数型\n")
(integer? 9)
(integer? 0)
(integer? -7)
(integer? 3/4)
(integer? 0.7)
(integer? 0.0)

;; 実数型
(display "\n 実数型\n")
(real? 1.9)
(real? 1.0)
(real? 4/2)
(real? 4/5)

(display "\n シンボル\n")
#t
#f 

