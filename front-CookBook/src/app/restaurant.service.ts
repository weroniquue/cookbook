import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessageService} from './message.service';
import {CookieService} from 'ngx-cookie-service';
import {UserLoginData} from './models/user-login-data';
import {catchError, tap} from 'rxjs/operators';
import {throwError} from 'rxjs';

const httpOptionsWithCredential = {
  headers: new HttpHeaders({'Content-type': 'application/json'}),
  withCredentials: true
};


@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private urlRestaurant = 'http://localhost:8080/cookbook/api/restaurants';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  createRestaurant(restaurant: any){

    return this.http.post(this.urlRestaurant + '/add', restaurant , httpOptionsWithCredential)
      .pipe(
        tap(data => {
          console.log(data);
        }),
        catchError(err => {
          this.messageService.openSnackBar(err);
          return throwError(err);
        })
      );
  }
}