#lang racket
;; Copyright (c) 2023 Furukawa, Atsushi <atsushifx@aglabo.com>
;;
;; This software is released under the MIT License.
;; https://opensource.org/licenses/MIT

;: Hello World
(displayln "\n -- 文字列/文字 --\n")
; 文字列
(displayln "Hello 世界")

; リスト
(displayln '("Hello" "世界" "!!"))

; 文字
(displayln '(#\H #\e #\l #\l #\o))

;; 数値
(displayln "\n -- 数値 --\n")

; 整数
(displayln 12)
(displayln -8)
(display 3/8)
(displayln " 分数")

; 実数
(display .46)
(displayln " 実数")

; 複素数
(display 1+3i)
(displayln " 複素数")

;; Symbol
(displayln "\n -- Symbol --\n")
(define Hello "こんにちは 世界")
(displayln 'Hello) ; シンボル表示
(displayln Hello) ; シンボルにバインドされた文字列
