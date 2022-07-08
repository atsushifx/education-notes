#lang racket
;; cond.scm: 条件式
; equal:等しい
(display "\n condition '='\n")
(= 4 4) ; equal
(= 3 4)


; greater than)
(display "\n condition greater '>'\n")
(> 3 6)
(> 7 5)
(> 8.5 8.5)


(display "\n greater equal '>=' \n")
(>= 8 8)
(>= 8 4)
(>= 3 6)

(display "\n lesser than '<'\n")
(< 3 4)
(< 6 6)
(< 8 5)

(display "\n lesser equal '<=' \n")
(<= 5 6)
(<= 8 8)
(<= 9 4)
