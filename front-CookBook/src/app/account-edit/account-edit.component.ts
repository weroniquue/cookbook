import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { UserProfileEdit } from '../models/user-profile-edit';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  constructor(
    private messageService: MessageService,
    private userService: UserService
  ) { }

  loggedIn = false;

  updatedAccount: UserProfileEdit;

  message: string;
  currentUsername: string;

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
  }

  modifyAccount(
    firstName: string,
    secondName: string,
    email: string
  ) {
    if (firstName.length > 0 && secondName.length > 0 && email.length > 0){
      this.updatedAccount = new UserProfileEdit(firstName, secondName, email);
      // ustalenie tożsamości:
      this.currentUsername = this.userService.whoAmI();
      // edycja konta:
      this.userService.updateAccount(this.updatedAccount, this.currentUsername).subscribe(data => this.message = data['message']);
      this.messageService.add(this.message);
    } else this.messageService.add("Wszystkie pola muszą być wypełnione, spróbuj ponownie.");
  }

}
