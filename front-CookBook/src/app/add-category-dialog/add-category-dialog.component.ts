import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Dialogdata} from '../recipe-new/recipe-new.component';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-category-dialog',
  templateUrl: './add-category-dialog.component.html',
  styleUrls: ['./add-category-dialog.component.css']
})
export class AddCategoryDialogComponent implements OnInit {

  createForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AddCategoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Dialogdata,
    private fb: FormBuilder) {

    this.createForm =  this.fb.group({
      name: new FormControl('', [Validators.required, Validators.maxLength(20)]),
    });
  }

  ngOnInit(): void {

  }

  cancel() {
    this.dialogRef.close();
  }




}
