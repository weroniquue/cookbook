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
    if (this.loggedIn) this.getProfileInfo();
  }

  logIn(username: string, password: string): void {
    this.user = new UserLoginData(username, password);
    // login:
    this.userService.login(this.user).subscribe(() => {
      this.messageService.openSnackBar(`Zalogowano pomyślnie`);
      this.loggedIn = true;
      localStorage.setItem('cookbook_username', this.user.usernameOrEmail);
      this.getProfileInfo();
      },
      err => {
        console.log(err);
      }
    );
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
