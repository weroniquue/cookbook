import { Component, OnInit } from '@angular/core';
import { UserLoginData } from '../models/user-login-data';
import { UserProfileData } from '../models/user-profile-data';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserLoginData;
  userProfileData: UserProfileData;
  loggedIn = false;
  //username: string;

  constructor (
    private userService: UserService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
    if (this.loggedIn) {
      //this.username = localStorage.getItem('cookbook_username');
      this.getProfileInfo();
    } 
  }

  logIn(username: string, password: string): void {
    this.user = new UserLoginData(username, password);
    // login:
    this.userService.login(this.user).subscribe(() =>{
      this.messageService.add(`Zalogowano, token dostępu to ${localStorage.getItem('jwt')}`);
      this.loggedIn = true;
      localStorage.setItem('cookbook_username', this.user.usernameOrEmail);
      },
      err => {
        console.log(err);
        this.messageService.add('Nie udało się zalogować.');
      }
    );
    if (this.loggedIn) this.getProfileInfo();
  }

  getProfileInfo(){
    this.userService.getUserDetails(localStorage.getItem('cookbook_username'))
      .subscribe((data: UserProfileData) => this.userProfileData = { ...data },
      err => {
        console.log(err);
      });
  }

  logOut(){
    this.userService.logout();
    this.loggedIn = false;
  }

}
