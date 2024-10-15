(* Programming Languages A : SML/NJ *)
(* lecture: local variables / ローカル変数 *)

(* let を使った関数サンプル *)

fun silly1(z: int) =
	let
	  val x = if z > 0	then z	else 34
	  val y = x + z + 9
	in
	  if x > y
	  then
        x * 2
		else
			y * y
	end
;

fun silly2() =
    let
		val x = 1
	in
		(let val x = 2 in x + 1 end) (* x<-2 .. x+1 = 2+1 = 3 *)
		+
		(let val y = x + 2 in y + 1 end) (* y <- 1+2 = 3; y+1 =3+1 = 4 *)
	end
	;


(* exam: silly3 *)
fun silly3() =
    let
        val x = (let val x = 5 in x + 10 end) (* x= 5 + 10 = 15 *)
    in
		(x, (* 上記 letより x=15 *)
			(let val x = 2 in x end), (* letがあるので、x=2 *)
			(let val x = 10 in (let val x = 2 in x end) end)) (* 内側の x=2 が入る *)
	end
	;

silly2();
silly3();

