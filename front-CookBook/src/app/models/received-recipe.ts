import { Ingredient } from './ingredient';
import { Step } from './step';
import { UserProfileData } from './user-profile-data';
import { Restaurant } from './restaurant';
import { Comment } from './comment';

export class ReceivedRecipe {
    id: number;
    category: string;
    cuisine: string;
    tittle: string;
    description: string;
    createdBy: UserProfileData;
    steps: Step[];
    photos: string[];
    restaurants: Restaurant[];
    comments: Comment[];
    ingredients: Ingredient[];
}