import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from './models/user';
import { ExportUser } from './models/export-user';
import { MessageService } from './message.service';
import { AccountCreation } from './models/account-creation';

import { CookieService } from 'ngx-cookie-service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({ providedIn: 'root' })
export class UserService {

  cookieValue = 'UNKNOWN';

  constructor(
    private http: HttpClient,
    private messageService: MessageService,
    private cookieService: CookieService
  ) {}

  private loginUrl = 'http://localhost:8080/cookbook/api/auth/signin';
  private recipesUrl = 'http://localhost:8080/cookbook/api/user';
  private accountCreationUrl = 'http://localhost:8080/cookbook/api/auth/signup';
  private updateAccountUrl = 'http://localhost:8080/cookbook/api/user';

  private log(message: string) {
    this.messageService.add(`RecipeService: ${message}`);
  }

  addAuthenticationToken(token: string){
    this.cookieService.set('fwt', token);
  }

  getUser(username: string): Observable<User> {
    const url = `${this.recipesUrl}/${username}`;
    return this.http.get<User>(url);
  }

  login(user: ExportUser): Observable<ExportUser> {
    return this.http.post<ExportUser>(this.loginUrl, user, httpOptions);
  }

  logout(){
    this.cookieService.set('fwt', '');
  }

  createAccount(newAccount: AccountCreation) {
    return this.http.post<AccountCreation>(this.accountCreationUrl, newAccount, httpOptions);
  }

  updateAccount(newAccount: AccountCreation, username: string) {
    const url = `${this.updateAccountUrl}/${username}`;
    return this.http.post<AccountCreation>(url, newAccount, httpOptions);
  }

  amILoggedIn() {
    if (this.cookieService.check('fwt')) {
      if (this.cookieService.get('fwt').length > 0) return true;
    } else return false
  }

}
