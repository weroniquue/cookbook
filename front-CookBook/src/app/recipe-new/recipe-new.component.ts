import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../recipe.service';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { Location } from '@angular/common';
import { Recipe } from '../models/recipe';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-recipe-new',
  templateUrl: './recipe-new.component.html',
  styleUrls: ['./recipe-new.component.css']
})
export class RecipeNewComponent implements OnInit {

  constructor(
    private recipeService: RecipeService,
    private userService: UserService,
    private messageService: MessageService,
    private location: Location
  ) { }

  loggedIn: boolean;
  categories: any;
  cuisine: any;
  newRecipe: Recipe;
  message: string;
  createRecipeForm: FormGroup;

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();

    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);

    this.createRecipeForm = new FormGroup({
      title: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      description: new FormControl('', [Validators.required, Validators.maxLength(150)])
    });
  }

  public hasError(controlName: string, errorName: string) {
    return this.createRecipeForm.controls[controlName].hasError(errorName);
  }

  createRecipe() {

    // utworzenie obiektu do przesłania na serwer:
    this.newRecipe = new Recipe(
      this.createRecipeForm.controls['title'].value,
      this.createRecipeForm.controls['description'].value,
      this.createRecipeForm.controls['cuisineName'].value,
      this.createRecipeForm.controls['category'].value,
      this.createRecipeForm.controls['ingredients'].value,
      this.createRecipeForm.controls['photos'].value,
      this.createRecipeForm.controls['steps'].value
    );

    // utworzenie przepisu:
    this.recipeService.createRecipe(this.newRecipe).subscribe(
      data => {
        this.message = data['message'];
        this.messageService.openSnackBar(this.message);
      }, error => {
        console.log(error);
      }
    );
  }

  goBack() {
    this.location.back();
  }

}
