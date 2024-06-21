// about interface
interface PersonStates {
    name: string;
    isHungry: boolean;
}

const showPersonMessage = (p: PersonStates): String => {
    return p.name + " is " + (p.isHungry ? "hungly!!" : "match")
}

const anakin: PersonStates = {
    name: "Anakin Skywalker",
    isHungry: true,
}
//console.log(showPersonMessage(anakin));
class Pet {
    protected name: string;
    constructor(theName: string) {
        this.name = theName
    }

    walking(meters: number = 0): void {
        console.log(this.name + "は" + meters + " メートル、散歩した");
    }
}

class SnakePet extends Pet {
    walking(meters: number = 5): void {
        super.walking(meters);
    }
}

class Kitty extends Pet {
    constructor() {
        super('仔猫チャン');
    }
    getName(): string {
        return "ペットの名前は" + this.name;
    }
}

let myPet = new Kitty();

console.log(myPet.getName());
myPet.walking(3);
