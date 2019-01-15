import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { UserProfileCreate } from '../models/user-profile-create';

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit {

  constructor(
    private userService: UserService,
    private messageService: MessageService
  ) { }

  loggedIn = false;
  newAccount: UserProfileCreate;
  message: string;

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
  }

  logOut(){
    this.userService.logout();
    this.loggedIn = this.userService.amILoggedIn();
  }

  createAccount(
    firstName: string,
    secondName: string,
    username: string,
    email: string,
    password: string
  ) {
    if (firstName.length > 0 && secondName.length > 0 && username.length > 0 && email.length > 0 && password.length >= 5){
      // utworzenie obiektu do przesłania na serwer:
      this.newAccount = new UserProfileCreate(firstName, secondName, username, email, password);
      // założenie konta:
      this.userService.createAccount(this.newAccount).subscribe(data => this.message = data['message']);
      // komunikat:
      this.messageService.add(this.message);
    } else this.messageService.add("Wszystkie pola muszą być wypełnione, spróbuj ponownie.");
  }

}
