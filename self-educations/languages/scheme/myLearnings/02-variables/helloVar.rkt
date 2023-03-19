;; Copyright (c) 2023 Furukawa, Atsushi <atsushifx@aglabo.com>
;;
;; This software is released under the MIT License.
;; https://opensource.org/licenses/MIT
#lang racket

; 1st var
(define greet "おはよう、世界")
(display greet)
(newline)

; 2nd var
(set! greet "good night world")
(display greet)
(newline)

; var with list
(define greet-2 '("Hello," "world."))
(display greet-2)
(newline)

