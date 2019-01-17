import {Component, OnInit} from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Recipe} from '../models/recipe';
import { ActivatedRoute } from '@angular/router';

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
              private fb: FormBuilder,
              private route: ActivatedRoute
    ) {
    this.editRecipeForm = this.fb.group({
      title: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      description: new FormControl('', [Validators.required, Validators.maxLength(150)]),
      cuisineName: new FormControl('', [Validators.required]),
      category: new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {

    const id = this.route.snapshot.paramMap.get('id');

    this.loggedIn = this.userService.amILoggedIn();

    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);

    this.recipeService.getIngredients()
      .subscribe(data => this.ingredientList = data);
  }

  editRecipee(data:any){
    const newData = {
      title: this.editRecipeForm.controls['title'].value,
      description: this.editRecipeForm.controls['description'].value
    }

    this.recipeService.createRecipe(newData);

    console.log(data);
  }

  goBack(){
    this.location.back();
  }

}
