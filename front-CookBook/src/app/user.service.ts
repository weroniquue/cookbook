import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from './models/user';
import { ExportUser } from './models/export-user';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Cookie': ''
  })
}

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  private loginUrl = 'http://localhost:8080/cookbook/api/auth/signin';
  private recipesUrl = 'http://localhost:8080/cookbook/api/user';

  private log(message: string) {
    this.messageService.add(`RecipeService: ${message}`);
  }

  addAuthenticationToken(token: string){
    httpOptions.headers.set('cookie', `fwt=${token}`);
  }

  getUser(username: string): Observable<User> {
    const url = `${this.recipesUrl}/${username}`;
    return this.http.get<User>(url);
  }

  login(user: ExportUser): Observable<ExportUser> {
    return this.http.post<ExportUser>(this.loginUrl, user, httpOptions);
  }

}
