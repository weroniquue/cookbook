import { Component, OnInit } from '@angular/core';

import { mock_recipes } from '../mock-recipes';
import { Recipe } from '../models/recipe';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipe_list = mock_recipes;
  selectedRecipe: Recipe;

  constructor() { }

  ngOnInit() {
  }

  onSelect(recipe: Recipe): void {
    this.selectedRecipe = recipe;
  }

}
