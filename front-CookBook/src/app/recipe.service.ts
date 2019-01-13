import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Recipe } from './models/recipe';
import { ReceivedRecipe } from './models/received-recipe';
//import { mock_recipes } from './mock-recipes';
import { MessageService } from './message.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class RecipeService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  private recipesUrl = 'http://localhost:8080/cookbook/api/recipes';

  private log(message: string) {
    this.messageService.add(`RecipeService: ${message}`);
  }

  getRecipes(): Observable<ReceivedRecipe[]> {
    return this.http.get<ReceivedRecipe[]>(this.recipesUrl);
  }

  /** GET recipero by id. Will 404 if id not found */
  getRecipe(id: number): Observable<ReceivedRecipe> {
    const url = `${this.recipesUrl}/${id}`;
    return this.http.get<ReceivedRecipe>(url);
  }

}
