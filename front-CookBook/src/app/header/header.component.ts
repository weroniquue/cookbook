import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models/recipe';
import { RecipeService } from '../recipe.service';
 
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [ './header.component.css' ]
})
export class HeaderComponent implements OnInit {
  recipe_list: Recipe[] = [];
 
  constructor(private recipeService: RecipeService) { }
 
  ngOnInit() {
    this.getHeroes();
  }
 
  getHeroes(): void {
    this.recipeService.getRecipes()
      .subscribe(recipe_list => this.recipe_list = recipe_list.slice(1, 5));
  }
}