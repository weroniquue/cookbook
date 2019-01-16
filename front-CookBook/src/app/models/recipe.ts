import { Ingredient } from './ingredient';
import { Step } from './step';

export class Recipe {

    constructor(
        title: string,
        description: string,
        cuisineName: string,
        category: string,
        ingredients: Ingredient[],
        photos: string[],
        steps: Step[]
    ) {
        this.title = title;
        this.description = description;
        this.cuisineName = cuisineName;
        this.category = category;
        this.ingredients = ingredients;
        this.photos = photos;
        this.steps = steps;
    }

    title: string;
    description: string;
    cuisineName: string;
    category: string;
    ingredients: Ingredient[];
    photos: string[];
    steps: Step[];
}