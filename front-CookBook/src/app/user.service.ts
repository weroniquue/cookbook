import {Injectable, OnInit} from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from './models/user';
import { ExportUser } from './models/export-user';
import { MessageService } from './message.service';
import { AccountCreation } from './models/account-creation';
import { AccountEdit } from './models/account-edit';

import { CookieService } from 'ngx-cookie-service';
import { Cookie } from 'ng2-cookies/ng2-cookies';
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
  user: ExportUser;
  error = '';

  private loginUrl = 'http://localhost:8080/cookbook/api/auth/signin';
  private recipesUrl = 'http://localhost:8080/cookbook/api/user';
  private accountCreationUrl = 'http://localhost:8080/cookbook/api/auth/signup';
  private updateAccountUrl = 'http://localhost:8080/cookbook/api/user';
  private whoAmIUrl = 'http://localhost:8080/cookbook/api/user/myProfile';

  accessToken = '';

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('currentUser'));
  }



  addAuthenticationToken(token: string) {
    // this.cookieService.set('jwt', token);
    localStorage.setItem('jwt', token);
  }

  getUserDetails(username: string): Observable<User> {
    const url = `${this.recipesUrl}/${username}`;
    return this.http.get<User>(url);
  }

  login(user: ExportUser): Observable<ExportUser> {
    return this.http.post<ExportUser>(this.loginUrl, JSON.stringify(user), httpOptionsWithCredential)
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

  logout() {
    // this.cookieService.set('jwt', '');
    // localStorage.setItem('jwt', '');
    // odbiór - localStorage.getItem(key);

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
        }),
        catchError(err => {
          this.error = err.error.message;
          console.log(this.error);
          return throwError(err);
        })
      );
  }

  amILoggedIn() {
    /*if (this.cookieService.check('jwt')) {
      if (this.cookieService.get('jwt').length > 0) return true;
    } else return false*/
    if (localStorage.getItem('jwt') != null && localStorage.getItem('jwt').length > 0) {
      return true;
    }
    return false;
  }

  whoAmI(): string {
    const username = localStorage.getItem('cookbook_username');
    if (username.length > 0) { return username; } else { return 'undefined'; }
  }

}
