import { Ingredient } from './ingredient';
import { Step } from './step';

export class Recipe {
    title: String;
    description: String;
    cuisineName: String;
    category: String;
    ingredients: Ingredient[];
    photos: String[];
    steps: Step[];
}