(* Programming Languages, Dan Grossman *)
(* Section 1: Booleans *)

(* SMLでの論理演算子*)
(* ans: andalso
   or: orelse
   not: not *)

(* SML では」 and/or/not はif式で書換えられる *)
e1 andalso e2
if e1
	then e2
	else false
;

e1 orelse e2

if e1
	then true
	else e2
;

not e1;
if e1
	then false
	else true
;

(* 比較演算子 *)
>, >=, <, <=
(* int, real のみ比較可能 *)
3.0 > 2;  (* real, intなのでinvalid *)
3.0 > (real)2 (* true: (real)で実数に変換する *)
3.0 > Real.fromInt 2 (* realに変換 *)

=, <> (* 等しくない *)
(* equality をもつすべての型 *)
3.0 = 3.0 (* invalid: 実数同士の比較は溺愛 *)

