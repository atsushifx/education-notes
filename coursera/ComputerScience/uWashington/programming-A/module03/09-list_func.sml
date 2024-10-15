(* Programming Languages A:SML/NJ *)
(* segment: functions uses list *)

(* sum list : list内の各要素を足す :*)
fun sum_list(xs: int list) =
	if (null xs)
		then 0
		else hd(xs) + sum_list(tl(xs))
;

sum_list([1, 2, 3, 4, 5]);


(* exam: list_product *)
fun list_product(xs: int list) =
    if (null xs)
		then 1
		else (hd xs) * list_product(tl xs)
;

list_product([]);
list_product([5]);
list_product([2, 4 , 2]);


(* lecture: countdounw *)
fun countdown(x: int) = (* [7, 6, 5, ..., 1] *)
	if (x = 0)
		then []	(* x =0 : 空のリスト *)
		else
			x::countdown(x -1)
;

countdown(7);

(* append : 2角リストを結合して1つのリストにする *)
fun append(xs: int list, ys: int list) =
	if (null xs)
		then ys
		else hd(xs)::append(tl(xs), ys)
;


(* pair を要素に持つlist function *)

(* sum_pair_list: リスト内のペアの要素をすべて足す *)
fun sum_pair_list(xs: (int * int) list) =
	if (null xs)
	then 0
	else #1 (hd xs) + #2 (hd xs) + sum_pair_list(tl xs)
;

sum_pair_list([(3, 4), (5, 6)]); (* = 3 + 4 + 5 + 6 *)

(* firsts: pairの1番目の項目のリスト *)
fun firsts(xs: (int*int) list) = (* [(3.4), [(5,6)] => [3. 5] *)
	if (null xs)
	then []
	else  (#1 (hd xs))::firsts(tl xs)
;

firsts([(3,4), (5,6)]);

(* secondss: pairの1番目の項目のリスト *)
fun seconds(xs: (int*int) list) = (* [(3.4), [(5,6)] => [3. 5] *)
	if (null xs)
	then []
	else  (#2 (hd xs))::seconds(tl xs)
;

seconds([(3,4), (5,6)]);


(* pairの要素を足す *)
fun sum_pair_list2(xs: (int*int) list) =
	sum_list(firsts(xs)) + sum_list(seconds(xs))
;
sum_pair_list2([(3,4), (5, 6)]);
sum_pair_list2([(3,4), (5, 6), (9, ~3)]);
