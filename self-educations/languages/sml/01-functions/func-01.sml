(* function: mlによる関数定義の例 *)

fun kai1 (n:int) =
		if n = 0
		then 1
		else n * kai1(n-1)

fun pow1 (x:real, y:int) =
		if y = 0 then 1.0
		else x * pow1(x, y - 1);

(* div 2を使って高速化 *)
fun pow2 (x:real, y:int) =
		if y = 0 then 1.0
		else
			if y mod 2 = 0 then pow2(x, y div 2) * pow2(x, y div 2)
			else x * pow2(x, y div 2) * pow2(x, y div 2)

(* local var *)
fun pow3 (x:real, y:int) =
		if y = 0 then 1.0
		else
			let
				val z = pow3(x, y div 2)
			in
				if y mod 2 = 0
				then z * z
				else x * z * z
			end

