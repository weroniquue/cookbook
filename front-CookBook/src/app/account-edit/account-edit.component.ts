import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';

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

  updatedAccount = {
    firstName: '',
    secondName: '',
    email: ''
  }

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
      this.updatedAccount.firstName = firstName;
      this.updatedAccount.secondName = secondName;
      this.updatedAccount.email = email;
      // ustalenie tożsamości:
      this.currentUsername = this.userService.whoAmI();
      // edycja konta:
      this.userService.updateAccount(this.updatedAccount, this.currentUsername).subscribe(data => this.message = data['message']);
      this.messageService.add(this.message);
    } else this.messageService.add("Wszystkie pola muszą być wypełnione, spróbuj ponownie.");
  }

}
