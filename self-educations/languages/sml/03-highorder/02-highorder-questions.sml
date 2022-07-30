(* 高階関数 : 練習問題 *)

(* q.1: s から e までの整数に関数 f を適用し、その合計値を求める関数 sum_of(f, s, e) *)
fun countup(s, e) =
  if s > e then
      nil
    else
      s::countup(s+1,e)

(* じぶんの答え *)
fun sum_of(f, s, e) =
  if s < e then
    f(s) + sum_of(f, s+1, e)
  else
    f(e)

(* q.1: 解答例 *)
fun sum_of2(f, s, e) =
  if s > e then
    0
  else
    f(s) + sum_of2(f, s+1, e)

(* q.2: s から e までの整数に関数 f を適用し、その結果をリストに格納して返す関数 tabulate(f, s, e) *)
fun tabulate(f, s, e) =
  if s > e then
    nil
  else
    f(s) :: tabulate(f, s+1, e)

(* q.2:解答 *)
fun tabulate2(f, s, e) =
  if s > e then nil
  else f s :: tabulate2(f, s + 1, e)


(* q.3: 関数 f にリストを渡すマップ関数 maplist(f, xs) *)
fun maplist(f, nil) = nil |
  maplist(f, x::xs) = f(x)::maplist(f, xs)

(* q.3 : 解答例 *)
fun maplist2(_, nil) = nil | (* _によるワイルドカード:リストがnilなら関数は何でもいい *)
    maplist2(f, xs) = f xs :: maplist2(f, tl xs) (* 引数としてリストxsをわたす *)

(* q.4: 述語 pred を満たす要素を先頭から取り出す関数 take_while(pred, xs) *)
fun take_while(_, nil) = nil
  | take_while(p, x::xs) =
      if (p x) then
        x :: take_while(p, xs)
      else
        take_while(p, xs) (* nil: これだと、先頭以外の要素も取り出す *)

(* q.4 : 解答例 *)
fun take_while2(_ , nil) = nil |
    take_while2(pred, x::xs) =
      if pred x then
        x :: take_while2(pred, xs) (* predを満たす要素のリスト *)
      else
        nil

(* q.5: 述語 pred を満たす要素を先頭から取り除く関数 drop_while(pred, xs) *)
fun drop_while(_, nil) = nil |
  drop_while(p, x::xs) =
    if p x then
      drop_while(p, xs)
    else
      x :: drop_while(p, xs)  (* pred xとな全xを除く : 2番目以降も *)

(* q.5: 解答例 *)
fun drop_while2(_, nil) = nil
  | drop_while2(pred, xs) =
      if pred (hd xs) then
        drop_while2(pred, tl xs)
      else
        xs


(* q.6: リスト xs を先頭から畳み込むとき、計算途中の累積値をリストに格納して返す scan_left(f, a, xs) *)
fun scan_left(_, a, nil) = [a] |
  scan_left(f, a, x::xs) =
    a :: scan_left(f,f(x, a), xs) (* a: リストが渡る *)

(* q.6 解答例 *)
fun scan_left2(f, a, nil) = [a] |
    scan_left2(f, a, x::xs) =
      a :: scan_left2(f, f(a, x), xs)


(* q.7: リスト xs を末尾から畳み込むとき、計算途中の累積値をリストに格納して返す scan_right(f, a, xs) *)
fun scan_right(f, a, nil) = [a] |
  scan_right(f, a, x::xs) =
     (* foldr(f, f(a , x), xs) : 間違い *)
    let
      val ys = scan_right(f, a, xs)
    in
      f(x, hd ys)::ys
    end


(* q.7: 解答例 *)
fun scan_right2(f, a, nil) = [a] |
  scan_right2(f, a, x::xs) =
    let
      val ys = scan_right2(f, a, xs)
    in
      f(x, hd ys)
    end
