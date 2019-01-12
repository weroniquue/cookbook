import { Component, OnInit } from '@angular/core';

//import { mock_recipes } from '../mock-recipes';
import { Recipe } from '../models/recipe';

import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipe_list: Recipe[];
  //selectedRecipe: Recipe;

  constructor(private recipeService: RecipeService) { }

  ngOnInit() {
    this.getRecipes();
  }

  /*onSelect(recipe: Recipe): void {
    this.selectedRecipe = recipe;
  }*/

  getRecipes(): void {
    //this.recipe_list = this.recipeService.getRecipes();
    this.recipeService.getRecipes().subscribe(recipe_list => this.recipe_list = recipe_list);
  }

}
