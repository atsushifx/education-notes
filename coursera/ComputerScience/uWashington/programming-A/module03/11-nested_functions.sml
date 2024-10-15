(* Programming Languages A : SML/NJ *)
(* lecture: nested functions *)

(* let内で、ローカル変数の代わりに関数を宣言できる *)

fun countup(from : int, to : int) =  (* [2, 3,4, 5] *)
	if (from = to)
		then	to::[]
		else 	from::countup(from+1, to)
;

fun countup_from1(to : int) =
	countup(1, to);

countup_from1(7);


(* using nested function *)
fun countup_from1_2(to: int) = (* *)
	let
		(* nested function *)
		fun local_countup(from : int, to : int) =
			if (from = to)
				then	to::[]
				else 	from::local_countup(from+1, to)
	in
	  local_countup(1, to)
	end
	;

(* scope内なので引数 to はいらない *)
fun countup_from1_3(to: int) = (* *)
	let
		(* nested function *)
		fun local_countup(from : int) =
			if (from = to)
				then	to::[]
				else 	from::local_countup(from+1)	(* to は外の countup_fomr1で定義済み *)
	in
	  local_countup(1)
	end
	;


countup_from1_3(7);
