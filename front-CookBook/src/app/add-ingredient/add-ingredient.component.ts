import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Dialogdata} from '../recipe-new/recipe-new.component';
import {Ingredient} from '../models/ingredient';
import {IngredientRequest} from '../models/ingredientRequest';

@Component({
  selector: 'app-add-ingredient',
  templateUrl: './add-ingredient.component.html',
  styleUrls: ['./add-ingredient.component.css']
})
export class AddIngredientComponent implements OnInit {

  createForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AddIngredientComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IngredientRequest,
  ) {

  }

  ngOnInit() {
  }

  cancel() {
    this.dialogRef.close();
  }

}
