import { Ingredient } from './ingredient';
import { Step } from './step';

export class Recipe {
    id: number;
    title: String;
    description: String;
    cuisineName: String;
    category: String;
    ingredients: Ingredient[];
    photos: String[];
    steps: Step[];
}