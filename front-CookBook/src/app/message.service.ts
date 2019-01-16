import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { MessagesComponent } from './messages/messages.component';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(
    public snackBar: MatSnackBar,
    //public messagesComponent: MessagesComponent
  ){ }

  messages: string[] = [];

  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  }

  openSnackBar(message: string) {
    const snackBarRef = this.snackBar.open(message, 'close', {
      verticalPosition: 'top',
      horizontalPosition: 'center',
      duration : 10000
    });

    snackBarRef.onAction()
      .subscribe( () => snackBarRef.dismiss());

  }

}
