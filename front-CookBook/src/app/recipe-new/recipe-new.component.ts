import {Component, OnInit} from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {Recipe} from '../models/recipe';
import {FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {AddCategoryDialogComponent} from '../add-category-dialog/add-category-dialog.component';


export interface Dialogdata {
  typeName: string;
  categoryOrCuisine: string;
}

@Component({
  selector: 'app-recipe-new',
  templateUrl: './recipe-new.component.html',
  styleUrls: ['./recipe-new.component.css']
})
export class RecipeNewComponent implements OnInit {

  categoryOrCuisine: string;
  typeName: string;

  constructor(
    private recipeService: RecipeService,
    private userService: UserService,
    private messageService: MessageService,
    private location: Location,
    private fb: FormBuilder,
    private router: Router,
    private categoryDialog: MatDialog,
  ) {
    this.createRecipeForm = this.fb.group({
      title: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      description: new FormControl('', [Validators.required, Validators.maxLength(150)]),
      cuisineName: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required]),
      ingredients: this.fb.array([this.initIngredientsFields()]),
      photos: null,
      steps: this.fb.array([this.initStepFields(1)])
    });

  }

  loggedIn: boolean;
  categories: any;
  cuisine: any;
  ingredientList: any;
  newRecipe: Recipe;
  message: string;
  createRecipeForm: FormGroup;

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();

    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe(cuisineList => this.cuisine = cuisineList);

    this.recipeService.getIngredients()
      .subscribe(data => this.ingredientList = data);

  }


  public hasError(controlName: string, errorName: string) {
    return this.createRecipeForm.controls[controlName].hasError(errorName);
  }

  initIngredientsFields(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      amount: ['', Validators.compose([Validators.required, Validators.pattern(/[0-9]*/)])]
    });
  }

  addNewIngredientField() {
    const control = <FormArray>this.createRecipeForm.controls.ingredients;
    control.push(this.initIngredientsFields());
  }

  removeIngredientField(i: number): void {
    const control = <FormArray>this.createRecipeForm.controls.ingredients;
    control.removeAt(i);
  }

  initStepFields(id: number): FormGroup {
    return this.fb.group({
      id: [id],
      description: ['', Validators.required]
    });
  }

  addNewInputField(): void {
    const control = <FormArray>this.createRecipeForm.controls.steps;
    control.push(this.initStepFields(this.createRecipeForm.value['steps'].length + 1));
  }

  removeInputField(i: number): void {
    const control = <FormArray>this.createRecipeForm.controls.steps;
    control.removeAt(i);
  }


  createRecipe(val: any) {

    if (val === 'newCategory') {
      console.log('Utworz nowa kategorie');
      this.openCategoryDialog();
    }

    if (val === 'create') {
      console.log('Przepis');
      // utworzenie przepisu:
      this.recipeService.createRecipe(this.createRecipeForm.value).subscribe(
        data => {
          this.message = data['message'];
          const id = data['message'].substring(data['message'].indexOf(':') + 2);
          this.messageService.openSnackBar(this.message);
          setTimeout(() => {
            this.router.navigate(['recipes/' + id]);
          }, 1500);  //1s
        }, error => {
          console.log(error);
        }
      );
    }

  }

  openCategoryDialog(): void {
    const dialogRef = this.categoryDialog.open(AddCategoryDialogComponent, {
      width: '250px',
      data: {
        typeName: 'kategorię:',
        categoryOrCuisine: this.categoryOrCuisine,
      }
    }).afterClosed()
      .subscribe(data => {
        console.log(data);
        this.recipeService.createCategory(data)
          .subscribe(result => {
            this.messageService.openSnackBar(result['message']);
            this.recipeService.getCategories()
              .subscribe(categoryList => this.categories = categoryList);
          }, err=> {
            console.log(err);
          });
      });

  }

  goBack() {
    this.location.back();
  }

}
