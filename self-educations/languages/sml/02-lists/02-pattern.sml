(* 02-2:パターンマッチング *)

(* 階乗(パターンマッチング) *)
fun fact 0 = 1 |
	fact n = n * fact(n-1)


(* リストの連結(pattern) *)
fun append1 (nil,ys) = ys |
	append1 (x::xs,ys) =
		x::append1(xs, ys)

;
(* パターンマッチの例 *)
(* ユニットを含んだリストを分解 *)
val (x,y)::xs =  [(1,"apple"), (2,"orange"), (3, "banana")]

(* リストを含んだリストを分解 *)
val (x::xs)::ys =[[1, 2, 3], [4,5], [6,7]];


(* 例題: quick sort *)
(* for quick sort: リストを引数nによって小さい方のリスト、大きい方のリストに分割する *)
(* quick sortを定義する前に、定義しておく必要がある *)
fun partition(_,nil) = (nil, nil) |
	partition(n, x::xs) =
		let
			val (xs2,xs3) = partition(n, xs)
		in
			if x < n
			then (x::xs2, xs3)
			else (xs2, x::xs3)
		end

(* quick sort main *)
fun quick_sort nil = nil |
	quick_sort(x::xs) =
		let
			val(xss,xsl) = partition(x, xs)
		in
			quick_sort(xss)@(x::quick_sort(xsl))
		end

