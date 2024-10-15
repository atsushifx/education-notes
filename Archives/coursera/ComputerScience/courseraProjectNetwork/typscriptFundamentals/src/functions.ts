// typescriptの関数について

const sum = (num1: number, num2: number): number => {
    return num1 + num2;
}
console.log(sum(8, 9));

const sum2 = (num1: string | number, num2: string | number): number => {
    typeof num1 == "string" ? num1 = parseInt(num1) : null;
    typeof num2 == "string" ? num2 = parseInt(num2) : null;
    return num1 + num2;
}
console.log("Sum2:", sum2(4, "--"));

const sum3 = (num1: string | number, num2: string | number): void => {
    typeof num1 == "string" ? num1 = parseInt(num1) : null;
    typeof num2 == "string" ? num2 = parseInt(num2) : null;
    // return num1 + num2;
}
console.log("Sum3:", sum3(4, "08"));
