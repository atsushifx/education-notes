(* Programming Languages, Dan Grossman *)
(* Section 1: Let Expressions to Avoid Repeated Computation *)
(* letにより、再帰時の再計算を防ぐ *)

(* 数列作成用Helper *)
fun countup(from : int, to : int) =  (* [2, 3,4, 5] *)
	if (from = to)
		then	to::[]
		else 	from::countup(from+1, to)
;

fun countdown(from:int, to:int) =
	if from = to
		then  to::[]
		else  from::countdown(from-1, to)
;

(* list内の最大値を取得 *)
fun bad_max(xs: int list) =
	if (null xs)
		then 0
	else if (null (tl xs))	(* 項目が1個のリスト *)
		then hd xs
	else if (hd xs > bad_max (tl xs))
        then hd xs
	else
		bad_max(tl xs)
	;

(* test bad_max *)
(*bad_max(countdown(30, 1));*)
(*bad_max(countup(1, 30));*)	(* bad_maxを何回も呼び出すため遅い *)

(* letを使って最大値をメモ化 *)
fun let_max(xs: int list) =
	if (null xs)
		then 0
	else if (null (tl xs))	(* 項目が1個のリスト *)
		then hd xs
	else
		let
			val tl_max = let_max(tl xs)
		in
			if hd xs > tl_max
				then hd xs
				else tl_max
		end
	;


let_max(countdown(10000,1));
let_max(countup(1, 10000));
