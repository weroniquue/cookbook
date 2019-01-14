import {Injectable, OnInit} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserProfileData } from './models/user-profile-data';
import { UserLoginData } from './models/user-login-data';
import { MessageService } from './message.service';
import { AccountCreation } from './models/account-creation';
import { AccountEdit } from './models/account-edit';

import { CookieService } from 'ngx-cookie-service';
//import { Cookie } from 'ng2-cookies/ng2-cookies';
import {catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

const httpOptionsWithCredential = {
  headers: new HttpHeaders({'Content-type': 'application/json'}),
  withCredentials: true
};

@Injectable({ providedIn: 'root' })
export class UserService implements OnInit {

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private cookieService: CookieService
  ) {
    // this.user = JSON.parse(sessionStorage.getItem('currentUser'));
    // this.loginStatus = JSON.parse(sessionStorage.getItem('login'));
  }

  loginStatus: boolean;
  user: UserLoginData;
  accessToken = '';
  error = '';

  private loginUrl = 'http://localhost:8080/cookbook/api/auth/signin';
  private recipesUrl = 'http://localhost:8080/cookbook/api/user';
  private accountCreationUrl = 'http://localhost:8080/cookbook/api/auth/signup';
  private updateAccountUrl = 'http://localhost:8080/cookbook/api/user';

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('currentUser'));
  }

  addAuthenticationToken(token: string): void {
    localStorage.setItem('jwt', token);
  }

  getUserDetails(username: string): Observable<UserProfileData> {
    const url = `${this.recipesUrl}/${username}`;
    return this.http.get<UserProfileData>(url);
  }

  login(user: UserLoginData): Observable<UserLoginData> {
    return this.http.post<UserLoginData>(this.loginUrl, JSON.stringify(user), httpOptionsWithCredential)
      .pipe(
        tap(data => {
          localStorage.setItem('jwt', data['accessToken']);
        }),
        catchError(err => {
          this.error = err.error.message;
          console.log(this.error);
          return throwError(err);
        })
      );
  }

  logout(): void {
    localStorage.removeItem('jwt');
    localStorage.removeItem('cookbook_username');
  }

  createAccount(newAccount: AccountCreation) {
    return this.http.post<AccountCreation>(this.accountCreationUrl, newAccount, httpOptions);
  }

  updateAccount(newAccount: AccountEdit, username: string) {
    const url = `${this.updateAccountUrl}/${username}`;
    return this.http.post<AccountEdit>(url, newAccount, httpOptionsWithCredential)
      .pipe(
        tap(data => {
          console.log(data);
        }
      ),
      catchError(err => {
        this.error = err.error.message;
        console.log(this.error);
        return throwError(err);
      })
    );
  }

  amILoggedIn(): boolean {
    if (localStorage.getItem('jwt') != null && localStorage.getItem('jwt').length > 0) return true;
    return false;
  }

  whoAmI(): string {
    const username = localStorage.getItem('cookbook_username');
    if (username.length > 0) { return username; } else { return 'undefined'; }
  }

}
