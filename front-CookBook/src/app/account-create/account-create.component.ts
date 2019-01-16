import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { UserProfileCreate } from '../models/user-profile-create';
import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Location } from '@angular/common';

export class MyErrorStateMatche implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }

}

@Component({
  selector: 'app-account-create',
  templateUrl: './account-create.component.html',
  styleUrls: ['./account-create.component.css']
})
export class AccountCreateComponent implements OnInit {

  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private location: Location
  ) { }

  loggedIn = false;
  newAccount: UserProfileCreate;
  message: string;

  createAccountForm: FormGroup;
   matcher = new MyErrorStateMatche();

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();

    this.createAccountForm = new FormGroup({
      firstName: new FormControl('', [Validators.required, Validators.maxLength(40)]),
      secondName: new FormControl('', [Validators.required, Validators.maxLength(40)]),
      username: new FormControl('', [Validators.required, Validators.maxLength(20)]),
      email: new FormControl('', [Validators.required, Validators.maxLength(40), Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    });
  }

  public hasError(controlName: string, errorName: string) {
    return this.createAccountForm.controls[controlName].hasError(errorName);
  }

  logOut(){
    this.userService.logout();
    this.loggedIn = this.userService.amILoggedIn();
  }

  createAccount() {

    // utworzenie obiektu do przesłania na serwer:
    this.newAccount = new UserProfileCreate(this.createAccountForm.controls['firstName'].value,
    this.createAccountForm.controls['secondName'].value,
    this.createAccountForm.controls['username'].value,
    this.createAccountForm.controls['email'].value,
    this.createAccountForm.controls['password'].value);

    // założenie konta:
    this.userService.createAccount(this.newAccount).subscribe(
      data => {
        this.message = data['message'];
        this.messageService.add(this.message);
      }, error => {
        console.log(error);
      }
    );
  }

  goBack(): void {
    this.location.back();
  }

}
