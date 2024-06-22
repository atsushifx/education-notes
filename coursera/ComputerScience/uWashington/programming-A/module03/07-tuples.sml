(* Programming Languages A:SML/NJ *)
(* segment tuple *)

(* pairs *)
fun swap (pr : int*bool) = (* parameter1 = (x1:int, x2:bool *)
    (#2 pr, #1 pr)	(* return (x2:bool, x1:int) *)

fun sum_pair(pr : int * int) =
	#1 pr + #2 pr

fun sum_two_pairs(pr1 : int * int, pr2 : int * int) =
	(#1 pr1) + (#2 pr1) + #1 pr2 + #2 pr2

fun div_mod(x: int, y: int) =
	(x div y, x mod y)


fun sort_pair(pr: int * int) =
	if (#1 pr < #2 pr)
		then (#1 pr, #2 pr)
		else (#2 pr, #1 pr)


;; (* nested tuple (pair) *)
val x1 = (8, (true, 9))

val x2 = #1 (#2 x1)	(*) (#2 x1) -> (true, 9) ... #1 -> true *)

val x3 = (#2 x1)

val x4 = ((3, 5), ((4, 8), (0, 0)))

(* practice *)
val x =  (3, (4, (5, 6)));
val y = (#2 x, (#1 x, #2 (#2 x)));
val ans = (#2 y, 4)
