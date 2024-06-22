(* Programming Languages, Dan Grossman *)
(* Section 1: Options *)
(* SMLでの実行時例外／エラーオブジェクトなどの実装法 *)

(* 数列作成用Helper *)
fun countup(from : int, to : int) =
	if (from = to)
		then	to::[]
		else 	from::countup(from+1, to)
;

fun countdown(from:int, to:int) =
	if from = to
		then  to::[]
		else  from::countdown(from-1, to)
;

(* 前回作成した高速版max *)
fun old_max(xs: int list) =
	if (null xs)
		then 0	(* 空のリストなので、実行時例外かエラーヲ返したい *)
	else if (null (tl xs))	(* 項目が1個のリスト *)
		then hd xs
	else
		let
			val tl_max = old_max(tl xs)
		in
			if hd xs > tl_max
				then hd xs
				else tl_max
		end
	;


(* Option : すべての型に適合 -> エラー時にリターン値の代わりに使える *)

(* Optionsを使った max *)
(* OPTIONS:
	NONE: 値なし
	SOME: 値あり
	isSome: option n が値を持つ(=SOME)  true
	valOf x: option nから値を取り出す *)

(* fn: int -> int option *)
fun max1(xs: int list) =
	if (null xs)
		then NONE	(* OPTION : 値が存在しない *)
	else
		let
			val tl_max = max1(tl xs)	(* int option *)
		in
			if (isSome tl_max) andalso ((valOf tl_max) > hd xs)
				then tl_max
				else SOME (hd xs)	(* OPTION : 値 hd xsを持つ *)
		end
	;


(* max1 test *)
max1([]); (* = NONE *)
(* valOf(max1([]));*)	(* 実行時例外が発声 *)

valOf(max1 [3, 7, 5]) + 1; (* =8 : int *)


(* max2: SAMEを最後にだけ使用するVersion *)
fun max2(xs: int list) : int option =
	if (null xs)
		then NONE
		else
			(* Helper Functionsをつかって空ではないリストをチェック *)
			let
			  fun local_max(xs: int list) : int =
				if (null (tl xs))	(* xsは最低1つは項目あり *)
					then hd xs
					else
                        let
                            val tl_max = local_max(tl xs)
						in
							if hd xs > tl_max
								then hd xs
								else tl_max
						end
			in
				SOME (local_max xs)
			end
;

(* max2 test *)
max2([]); (* = NONE *)
max2 [3, 7, 5];
