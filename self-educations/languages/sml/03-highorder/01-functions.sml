(* 高階関数 *)

(* mappingの例 *)
fun
  map2 (_, nil) = nil |
  map2 (f, y::ys) = f (y)::map2(f, ys)

(* 使い方:匿名関数 *)
(* val a = map2(fn n => n*n, [1,2,3,4,5]); *)


(* フィルターの例 *)
(* t->削除 *)
fun
  rm_if(_, nil) = nil |
  rm_if(p, x::xs) =
    if (p x) then (* 要素xをpで評価 *)
      rm_if(p, xs) (* t: 削除 *)
    else
      x::rm_if(p, xs) (* f:削除しない *)

(* rm_if: サンプル *)
val a = rm_if(fn x => x>=10, [1, 10, 2, 12, 3, 13]) (* should [1,2,3]) *)
val b = rm_if(fn x => x="abc", ["abc", "def", "abc", "ghi"] )


(** 畳み込み **)
(* 畳み込み left *)
(* f:function, g:初期値, xs:リスト *)
fun f_l (f, g, nil) = g | (* 初期値を返す *)
  f_l(f, g, x::xs) = f_l(f, f(g, x), xs)

(* 畳み込み right *)
fun f_r(f, g, nil) = g |
  f_r(f, g, x::xs) = f (x, f_r(f, g, xs))

(* 畳み込みの例 *)
val a = f_l(op +, 0, [1,2,3,4,5]) (* リストの合計 *)
val b = f_l(fn(a,x)=>x::a, nil, ["abc", "hello", "world"]) (* リスト反転 *)

(* 畳み込みの例 *)
val c = f_r(op +, 0, [1,2,3,4,5]) (* リストの合計 *)
val d = f_r(op ::, nil, ["abc", "hello", "world"]) (* リストをそのまま返す *)


(* f_l, f_r を使って高階関数 *)
(* length *)
val l1 = f_l(fn (x, _) => x+1, 0, [1, 2, 3, 4, 5, 6, 6])
val l2 = f_r(fn (_, a) => a+1, 0, [1, 2, 3, 4, 5, 6, 7])

(* mapping *)
val am = f_r(fn (x,a)=>(x*x)::a, nil, [1, 2, 3, 4, 5])

(* filter *)
val af = f_r( (* 偶数を抽出 *)
  fn (x, a) => if x mod 2 = 0 then x::a else a,
      nil,
      [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

(* count if *)
val ac = f_l(
  fn (a, x) => (if x mod 2 = 0 then a+1 else a), (* 偶数ならカウント *)
  0,
  [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
