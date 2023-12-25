/**
 * データ型についてまとめた
 */
fn main() {
    println!("\nデータ型");
    datatype1();

    println!("\n数値リテラル");
    number_literal();

    println!("\n数値演算");
    datatype_calc();
}

// データ型定義必須
fn datatype1() {
    let x: u32; // 型定義は必須
    x = "42".parse().expect("not num"); // letで宣言しているだけなので代入可能
    println!("x={}", x);
}

// 数値リテラル
fn number_literal() {
    println!("Dec:{}", 98_222); // '_'は見た目上の区切り（無視される)
    println!("Hex:0x12 = {}", 0x12);
    println!("Oct:0o26 = {}", 0o26);
    println!("Bin:0b1101 = {}", 0b1101);
    println!("Byte:'A' = {}", b'A');
}

// 数値演算
fn datatype_calc() {
    let sum = 10 + 5;
    //let sum = 10 + 5.0; // 実数と整数の演算は不可
    println!("足し算: sum = {}", sum);

    let sub = 95.5 - 4.2;
    println!("引き算: subtracion = {}", sub);

    let product = 5 * 13;
    println!("かけ算: product = {}", product);

    let quotient = 56.7 / 32.2;
    println!("割り算(実数) qutient = {}", quotient);

    let quotient = 2 / 3; // 結果は0
    println!("割り算 (整数) 2/3 = {}", quotient);

    let sur = 5 % 2;
    println!("剰余 (整数): 5 % 2 = {}", sur);

    let sur = 5.5 % 2 as f32; // 整数と実数の演算はできない -> as で型キャスト
    println!("剰余 (整数): 5.5 % 2 = {}", sur);
}
