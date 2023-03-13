#lang racket
;; Copyright (c) 2023 Furukawa, Atsushi <atsushifx@aglabo.com>
;;
;; This software is released under the MIT License.
;; https://opensource.org/licenses/MIT

(display "rakcet calculation sample program\n\n")

;; add
(+ 1 2) ; = 3

;; subtract
(- 2 3) ; = -1

;; multiply
(* 3 4) ; = 12

;; divide
(/ 8 4)

;; modulo
(remainder 8 5)

;; n乗
(expt 2 3) ; = 8 (= 2 ^3: 2*2*2)


;; -
(- -3)

;; 論理演算
(display "\n -- logic calc --\n\n")

(= 1 2)  ; false
(> 1 2)  ; false
(< 1 2)  ; true
(>= 3 3)  ; true
(<= 4 3)   ; false

;;
(display "\n論理演算子\n")
(not #f)
(and #t #t #t)
(or #f #f #f)
(xor #t #f)
(xor #f #f)



;; 複雑な計算
(+ 1 (* 2 (- 3 (/ 4 5)))) ; 27/5 .. 分数がかえる
(+ 1/3 1/4)



