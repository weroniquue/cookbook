import { Ingredient } from './ingredient';
import { Step } from './step';

export class Recipe {
    title: number;
    description: string;
    cuisineName: string;
    category: string;
    ingredients: Ingredient[];
    photos: string[];
    steps: Step[];
}