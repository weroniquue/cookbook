import {Injectable, OnInit} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';

import { Recipe } from './models/recipe';
import { ReceivedRecipe } from './models/received-recipe';
//import { mock_recipes } from './mock-recipes';
import { MessageService } from './message.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import {PagedResponse} from './models/paged-response';
import {catchError, tap} from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class RecipeService implements OnInit{

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {}

  private recipesUrl = 'http://localhost:8080/cookbook/api/recipes';

  private log(message: string) {
    this.messageService.add(`RecipeService: ${message}`);
  }

  /*getRecipes(): Observable<ReceivedRecipe[]> {
    return this.http.get<ReceivedRecipe[]>(this.recipesUrl);
  }*/

  /** GET recipero by id. Will 404 if id not found */
  getRecipeById(id: number): Observable<ReceivedRecipe> {
    const url = `${this.recipesUrl}/${id}`;
    return this.http.get<ReceivedRecipe>(url)
      .pipe(
        tap(data => {
            console.log(data);
          }
        ),
        catchError(err => {
          console.log(err.error.message);
          return throwError(err);
        })
      );
  }




}
