// typescript
/*
let myString: string;
myString = "Hello typescript again";

let myNum: number = 3

console.log(myString);
console.log("set num to: ", myNum)

let myBool: boolean = true;
console.log("Bool:", myBool)
*/


// array
/*
let strArr: string[] = ["3", "Hello", "world"]
console.log(strArr);

let numArr: number[] = [42, -0.5, 3]
console.log(numArr);

let boolArr: boolean[] = [true, false, null, undefined]
console.log(boolArr);
*/

// tuple
let strnumTuple: [string, string, number] = ["eldenring", "痩せ人", 24]
console.log(strnumTuple)

let myUnion: [string | number, string | number] = ["rogue leagcy", 1.0]
console.log("union is ", myUnion);
