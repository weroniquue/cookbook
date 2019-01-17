import {Injectable, OnInit} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';

import { Comment } from './models/comment';
import { ReceivedRecipe } from './models/received-recipe';

import { MessageService } from './message.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PagedResponse } from './models/paged-response';
import { catchError, tap } from 'rxjs/operators';
import { UserService } from './user.service';
import { CommentResponse } from './models/commentResponse';
import { Restaurant } from './models/restaurant';

const httpOptionsWithCredential = {
  headers: new HttpHeaders({'Content-type': 'application/json'}),
  withCredentials: true
};

@Injectable({ providedIn: 'root' })
export class RecipeService implements OnInit {

  private recipesUrl = 'http://localhost:8080/cookbook/api/recipes';
  categoryUrl = 'http://localhost:8080/cookbook/api/category';
  cuisineUrl = 'http://localhost:8080/cookbook/api/cuisine';


  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {}



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

  getRecipes(): Observable<PagedResponse> {
    return this.http.get<PagedResponse>(this.recipesUrl)
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

  getRecipeByCategory(category: string): Observable<PagedResponse> {
    const url = `${this.categoryUrl}/${category}`;
    return this.http.get<PagedResponse>(url)
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

  getRecipeByCuisine(cuisine: string): Observable<PagedResponse> {
    const url = `${this.cuisineUrl}/${cuisine}`;
    return this.http.get<PagedResponse>(url)
      .pipe(
        tap(),
        catchError(err => {
          return throwError(err);
        })
      );
  }

  deleteRecipe(id: number) {
    const url = `${this.recipesUrl}/${id}`;
    return this.http.delete(url, httpOptionsWithCredential)
      .pipe(
        tap(),
        catchError(err => {
          return throwError(err);
        })
      );

  }

  createRecipe(data: any){
    return this.http.post(this.recipesUrl, data, httpOptionsWithCredential)
      .pipe(
        tap(),
        catchError(err => {
          return throwError(err);
        })
      );
  }

  editRecipe(data:any, id:number){
    const url = `${this.recipesUrl}/${id}/edit`;
    return this.http.post(url, data, httpOptionsWithCredential)
      .pipe(
        tap(),
        catchError(err => {
          return throwError(err);
        })
      );
  }

  getComments(id: number) {
    const url = `${this.recipesUrl}/${id}/comments`;
    console.log(url);
    return this.http.get(url, httpOptionsWithCredential)
      .pipe(catchError(err => {
        console.log(err.error.message);
        return throwError(err);
      }));
  }

  createComment(id: number, comment: Comment) {
    const url = `${this.recipesUrl}/${id}/comments`;
    return this.http.post(url, comment , httpOptionsWithCredential)
      .pipe(
        catchError(err => {
        console.log(err.error.message);
        return throwError(err);
      }));

  }

  deleteComment(id: number, comment: CommentResponse) {
    const idComment = comment.id;
    const url = `${this.recipesUrl}/${id}/comments/${idComment}`;
    return this.http.delete(url, httpOptionsWithCredential)
      .pipe(
        catchError(err => {
          console.log(err.error.message);
          return throwError(err);
        }));

  }

  getCategories() {
    const url = `${this.categoryUrl}/all`;
    return this.http.get(url, httpOptionsWithCredential)
      .pipe(catchError(err => {
        console.log(err.error.message);
        return throwError(err);
      }));
  }

  getCuisine() {
    const url = `${this.cuisineUrl}/all`;
    return this.http.get(url, httpOptionsWithCredential)
      .pipe(catchError(err => {
        console.log(err.error.message);
        return throwError(err);
      }));
  }

  getIngredients() {
    const url = `http://localhost:8080/cookbook/api/ingredients/`;
    return this.http.get(url, httpOptionsWithCredential)
      .pipe(catchError(err => {
        console.log(err.error.message);
        return throwError(err);
      }));
  }

  getRestaurants() {
    return this.http.get<Restaurant[]>('http://localhost:8080/cookbook/api/restaurants/')
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
