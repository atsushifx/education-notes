(* 02:リスト操作と多相性 *)

(* リストの長さ(項目の数)を求める *)
fun my_length xs=
		if xs=[](* null xs のほうがいい *)
		then 0
		else 1 + my_length(tl xs)

fun identity x= x; (* 恒等関数 *)


(* リストを連結する : 演算子@*)
fun append(xs, ys) =
		(*	xs::ys(* これはうまくいかない *) *)
		if null xs then
			ys
		else
			(hd xs)::append((tl xs), ys)

(* リストを反転する *)
fun my_rev(xs) =
		if null xs then
			[]
		else
			my_rev(tl xs)@[(hd xs)]
;

(* リストの検索:member (in common lisp) *)
fun member(x , ys) =
		if null ys
		then nil
		else
			if x = (hd ys)
			then
				ys
			else
				member(x, tl ys)



