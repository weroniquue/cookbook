import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Recipe } from './models/recipe';
import { mock_recipes } from './mock-recipes';
import { MessageService } from './message.service';

@Injectable({ providedIn: 'root' })
export class RecipeService {

  constructor(private messageService: MessageService) { }

  /*getRecipes(): Recipe[] {
    return mock_recipes;
  }*/

  getRecipes(): Observable<Recipe[]> {
    // TODO: send the message  a f t e r  fetching the heroes
    this.messageService.add('RecipeService: fetched recipes');
    return of(mock_recipes);
  }

  getRecipe(id: number): Observable<Recipe> {
    // TODO: send the message  a f t e r  fetching the heroes
    this.messageService.add(`RecipeService: fetched recipe id=${id}`);
    return of(mock_recipes.find(recipe => recipe.id === id));
  }

}
