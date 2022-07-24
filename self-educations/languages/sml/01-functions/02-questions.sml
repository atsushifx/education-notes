(* おきらく SML の問題 *)
(* URL: http://www.nct9.ne.jp/m_hiroi/func/smlnj01.html *)

(* q.1 *)
fun square n =
		n * n

(* q.2 *)
fun cube n =
		n * n * n


fun nlist n  =
		if n = 0
		then []
		else n::nlist(n-1)


(* q.3 *)
fun sum n =
		if n = 0
		then 0
		else n + sum(n-1)


(* q.4 *)
fun square_sum n =
		if n = 0
		then 0
		else n*n + square_sum(n-1)

(* q.5 *)
fun cube_sum n:int =
		if n = 0
		then 0
		else cube(n) + cube_sum(n-1)

(* q.6 *)
fun half r:real=
		r / 2.0

(* q.7 *)
fun medium(n,m) =
		(n+m) / 2.0


(* q.8: 2乗の平均値 *)
fun square_medium (n, m) =
		(n*n + m*m) / 2.0

(* q.9: 引数をリストに *)
fun make_list1 a =
		a::[]
(* 答え： [a] で十分 *)

fun make_list2(a,b) =
		a::make_list1(b)
(* 答え: [a,b ] *)

fun make_list3(a,b,c) =
		a::make_list2(b, c)
(* 答え: [a,b,c] *)

(* q.10: リストの要素取りだし *)
fun first xs =
		hd xs;

fun second xs =
		hd (tl xs)

fun third xs =
		hd(tl (tl xs))
