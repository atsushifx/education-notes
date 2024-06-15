#lang racket

(define (echo-input)
  (display "input: ")
  (let ([input (read-line)])
    (display "value: ")
    (displayln input)
    input))

(define (echo-until-eof)
  (let ([input (echo-input)])
    (unless (eof-object? input)
      (echo-until-eof))))


(echo-until-eof)
