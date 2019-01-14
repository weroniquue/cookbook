import { Component, OnInit } from '@angular/core';
import { ExportUser } from '../models/export-user';
import { UserService } from '../user.service';
import { User } from '../models/user';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: ExportUser = {
    usernameOrEmail: '',
    password: ''
  };
  accessToken = '';
  loggedIn = false;

  constructor (
    private userService: UserService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
  }

  // login function:
  onClick(username: string, password: string): void {
    this.user.usernameOrEmail = username;
    this.user.password = password;
    // logowanie:
    this.userService.login(this.user).subscribe(data => this.accessToken = data['accessToken']);
    // zapisanie klucza użytkownika:
    this.userService.addAuthenticationToken(this.accessToken);
    // informacja o zalogowaniu:
    if (this.accessToken.length > 0) {
      this.messageService.add(`Zalogowano, token dostępu to ${this.accessToken}`);
      this.loggedIn = true;
      localStorage.setItem('cookbook_username', this.user.usernameOrEmail);
    } else this.loggedIn = false;
  }

  logOut(){
    this.user.usernameOrEmail = '';
    this.user.password = '';
    this.accessToken = '';
    this.userService.logout();
    this.loggedIn = false;
  }

}
