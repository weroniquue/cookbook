import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PagedResponse} from './models/paged-response';
import {Observable, throwError} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipesListService implements OnInit {

  private allRecipesUrl = 'http://localhost:8080/cookbook/api/recipes';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  getRecipes(): Observable<PagedResponse> {
    console.log('get recipes servixe');
    return this.http.get<PagedResponse>(this.allRecipesUrl)
      .pipe(
        tap(data => {
            console.log(data);
            console.log('data');
          }
        ),
        catchError(err => {
          console.log(err.error.message);
          return throwError(err);
        })
      );
  }

}
