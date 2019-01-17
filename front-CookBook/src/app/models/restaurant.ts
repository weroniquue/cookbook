export class Restaurant {

    constructor(
        name: string,
        city: string,
        code: string,
        address: string,
        recipes: number[]
    ) {
        this.name = name;
        this.city = city;
        this.code = code;
        this.address = address;
        this.recipes = recipes;
    }

    name: string;
    city: string;
    code: string;
    address: string;
    recipes: number[];
}