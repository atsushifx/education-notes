#lang racket

(displayln"\n---\n真偽値")
#t  ;; true/真
#f  ;; false/偽

(display "1 == 1 : ") (= 1 1)
(display "1 == 2 : ") (= 1 2)

;; 型チェック
(displayln "\n---\n型チェック")
(displayln "boolean")
(display "check #f = ") (boolean? #f)
(display "check 1 = ") (boolean? 1)
(display "check null = ") (boolean? null)

;; 数値チェック
(displayln "\n---\n数値チェック")
(display "null = ") (number? null)
(display "x = " ) (number? 'x)
(define x 6.3)
(display "x <- 6.3 = ") (number? x)
(define y "5.5")
(display "y <- '5.5' = ") (number? y)
(display "整数: 1.5 = ") (integer? 1.5)


