/**
 * 変数の不変性チェック
 */

fn main() {
    println!("\n変数の代入");
    variables_x();

    println!("\n定数宣言");
    with_const();

    println!("\nシャドーイング");
    shadowing();
}

// 1st variables
fn variables_x() {
    let x = 1;
    println!("x={}", x);

    // x = 6; // immutable なので代入不可
    let x = 6; //不変なので、letで新しい変数xにbind
    println!("change: x={}", x);
}

// 定数宣言
fn with_const() {
    const Y: i32 = 10; // 定数は大文字、型が必須
                       // const mut Z: i32 = 20; // 定数にmut は使えない

    println!("Y={}", Y);
}

// シャドーイング
fn shadowing() {
    let x = 5;
    let x = x + 1;

    {
        let x = x * 2; // ブロック内スコープ
        println!("in block: x = {}", x);
    }
    println!("out block: x = {}", x); // ブロック内の変更に影響されない

    // letでは方が変わってもよい
    let spaces = "1234567";
    let spaces = spaces.len();
    println!("spaces = {}", spaces);
}
