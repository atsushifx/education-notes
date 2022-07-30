(* お気楽SML/NJ 練習問題 2 末尾再帰 *)

(* q.1 sum: 1-nまでの合計を求める *)
fun sum0 (n) =
  let
    fun
      s0 (0, s) = s |
      s0 (n, s) = n + s0(n-1, s+n)
  in
    s0(n, 0)
  end

(* q.1 : answer *)
fun sum (n) =
  let
    fun s0 (0, sum) = sum |
      s0(n, sum) = s0(n-1, sum + n)
  in
    s0(n, 0)
  end

(* q.2: 1-n までの平方数の総和 *)
fun
  square_sum0(0) = 0 |
  square_sum0(n) = (n * n) + square_sum0(n-1)

(* 末尾再帰 : うまくいかない*)
fun square_sum2(n) =
  let
    fun
      ss2 (0, sum) = sum |
      ss2 (n, sum) =  ss2(n - 1, sum+((n-1) * (n-1)))
  in
    ss2(n, 0)
  end

(* q.2 answer *)
fun square_sum(n) =
  let
    fun
      ss3 (0, sum) = sum |
      ss3 (n, sum) = ss3(n - 1, sum + n*n)
  in
    ss3(n, 0)
  end

  (* q.3 : 1-n n^3の合計 / 末尾再帰 *)
  fun cube_sum(n) =
    let
      fun cs2(0, sum) = sum |
          cs2(n, sum) = cs2 (n-1, n*n*n + sum)
    in
      cs2(n, 0)
    end

