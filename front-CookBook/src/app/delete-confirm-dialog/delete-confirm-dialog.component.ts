import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {UserService} from '../user.service';

@Component({
  selector: 'app-delete-confirm-dialog',
  templateUrl: './delete-confirm-dialog.component.html',
  styleUrls: ['./delete-confirm-dialog.component.css']
})
export class DeleteConfirmDialogComponent implements OnInit {

  login:boolean;

  constructor(
    private userService: UserService,
    private dialog: MatDialogRef<DeleteConfirmDialogComponent>,
     @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.login = this.userService.amILoggedIn();
  }


  public cancel() {
    this.dialog.close('no');
  }

  public delete() {
    this.dialog.close('yes');
  }
}
