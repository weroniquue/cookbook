import { Component, OnInit } from '@angular/core';
import { UserLoginData } from '../models/user-login-data';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserLoginData;
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
    this.user = new UserLoginData(username, password);
    this.userService.login(this.user).subscribe();

    if (localStorage.getItem('jwt') != null && localStorage.getItem('jwt').length > 0) {
      this.messageService.add(`Zalogowano, token dostępu to ${localStorage.getItem('jwt')}`);
      this.loggedIn = true;
      localStorage.setItem('cookbook_username', this.user.usernameOrEmail);
    }
    else this.messageService.add("Nie udało się zalogować.");
  }

  logOut(){
    this.accessToken = '';
    this.userService.logout();
    this.loggedIn = false;
  }

}
