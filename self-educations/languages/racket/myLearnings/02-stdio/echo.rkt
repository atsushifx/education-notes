#lang racket

(define (echo-input)
  (display "input: ")
  (let ([input (read)])
    (display "value: ")
    (print input)
    (newline)
    input))

(define (echo-until-eof)
  (let ([input (echo-input)])
    (unless (eqv? input eof)
      (echo-until-eof))))


(echo-until-eof)
