import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Recipe } from '../models/recipe';
import { RecipeService } from '../recipe.service';
import {ReceivedRecipe} from '../models/received-recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  recipe: ReceivedRecipe;

  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getRecipe();
  }

  getRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.recipeService.getRecipeById(id).subscribe(recipe => {
      this.recipe = recipe;
    });
  }

  goBack(): void {
    this.location.back();
  }

  //@Input() recipe: Recipe;

}
