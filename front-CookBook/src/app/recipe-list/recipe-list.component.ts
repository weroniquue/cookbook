import {Component, OnInit} from '@angular/core';

import {ReceivedRecipe} from '../models/received-recipe';

import {RecipesListService} from '../recipes-list.service';
import {RecipeService} from '../recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipe_list: ReceivedRecipe[];
  order:string;
  ascending: boolean;

  constructor(
    private recipeService: RecipeService
  ) { }

  ngOnInit() {
    this.getRecipe();
  }

  getRecipe() {
    this.recipeService.getRecipes()
      .subscribe(data => {
        console.log(data);
        this.recipe_list = data.content;
      });
  }

  ascendingClik() {
    this.order = 'title';
    this.ascending = true;
  }

  descendingClik() {
    this.order = 'title';
    this.ascending = false;
  }

}
