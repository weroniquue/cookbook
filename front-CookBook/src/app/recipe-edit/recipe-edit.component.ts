import {Component, OnInit} from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Recipe} from '../models/recipe';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  loggedIn: boolean;
  categories: any;
  cuisine: any;
  ingredientList:any;
  editRecipe: Recipe;
  message: string;
  editRecipeForm: FormGroup;

  constructor(private recipeService: RecipeService,
              private userService: UserService,
              private messageService: MessageService,
              private location: Location,
              private fb: FormBuilder) {
    this.editRecipeForm = this.fb.group({
      title: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      description: new FormControl('', [Validators.required, Validators.maxLength(150)]),
      cuisineName: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required]),
      ingredients: this.fb.array([this.initIngredientsFields()]),
      photos: null,
      steps: this.fb.array([this.initStepFields()])
    });


  }

  ngOnInit() {

    this.loggedIn = this.userService.amILoggedIn();

    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);

    this.recipeService.getIngredients()
      .subscribe(data => this.ingredientList = data);

    this.recipeService.getRecipeById(10)
      .subscribe(data => this.editRecipeForm = data);

  }


  initIngredientsFields(): FormGroup{
    return this.fb.group({
      name: ['', Validators.required],
      amount: ['', Validators.required]
    });
  }

  addNewIngredientField(){
    const control = <FormArray>this.editRecipeForm.controls.ingredients;
    control.push(this.initIngredientsFields());
  }

  removeIngredientField(i: number) : void
  {
    const control = <FormArray>this.editRecipeForm.controls.ingredients;
    control.removeAt(i);
  }
  initStepFields(id:number): FormGroup
  {
    return this.fb.group({
      id: [id],
      description : ['', Validators.required]
    });
  }

  addNewInputField(): void
  {
    const control = <FormArray>this.editRecipeForm.controls.steps;
    control.push(this.initStepFields(this.editRecipeForm.value['steps'].length + 1));
  }

  removeInputField(i : number) : void
  {
    const control = <FormArray>this.editRecipeForm.controls.steps;
    control.removeAt(i);
  }


}
