use rand::Rng;
use std::cmp::Ordering;
use std::io;

fn main() {
    println!("Guessing Numnber Game.");

    // guessで当てる番号を取得
    let secret_number: u32 = rand::thread_rng().gen_range(1..101);
    // for Debug
    println!("secret number is: {}", secret_number);

    // init var
    let mut cnt: u32 = 1;

    loop {
        println!("Count {}", cnt);
        println!("Input Number [1..100]:");
        let mut buff: String = String::new();
        io::stdin()
            .read_line(&mut buff)
            .expect("Failed to read line");

        let guess: u32 = match buff.trim().parse() {
            Ok(n) => n,
            Err(_) => {
                println!("Not Number");
                continue;
            }
        };

        println!("You guessed: {}", guess);

        // 数値を比較して、数当て
        // match {enum,}
        match guess.cmp(&secret_number) {
            Ordering::Equal => {
                println!("Match, You win!");
                break; // 数当てゲームから抜ける
            }
            Ordering::Greater => {
                cnt += 1;
                println!("Too big");
            }
            Ordering::Less => {
                cnt += 1;
                println!("Too small");
            }
        }
        if cnt > 5 {
            println!("Count out\n You loose");
            break;
        }
    }
    println!("-----\n End of game.");
}
