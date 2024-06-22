(* shadowing: new binding on variables *)

val a = 10

val b = a * 2


val a = 5 (* a -> 10: shadowing: new binding value on new *)

val c = b

val d = a;

val a = a + 1; (* new binding value on A *)

val f = d * 2

;;
