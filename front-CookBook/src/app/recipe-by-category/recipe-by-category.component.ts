import { Component, OnInit } from '@angular/core';
import { ReceivedRecipe } from '../models/received-recipe';
import { RecipeService } from '../recipe.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-recipe-by-category',
  templateUrl: './recipe-by-category.component.html',
  styleUrls: ['./recipe-by-category.component.css']
})
export class RecipeByCategoryComponent implements OnInit {

  recipe_list: ReceivedRecipe[];
  order: string;
  ascending: boolean;


  constructor(
    private recipeService: RecipeService,
    private route: ActivatedRoute,
    private location: Location
  ) {
    route.params.subscribe(val => {
      const categoryOrCuisine = this.route.snapshot.paramMap.get('categoryOrCuisine');
      const name = this.route.snapshot.paramMap.get('categoryOrCuisineName');
      if (categoryOrCuisine == 'category') {
      this.recipeService.getRecipeByCategory(name)
      .subscribe(data => {
        this.recipe_list = data.content;
      });
      }

      if (categoryOrCuisine == 'cuisine') {
        this.recipeService.getRecipeByCuisine(name)
        .subscribe(data => {
          this.recipe_list = data.content;
        });
      }
    });          
  }

  ngOnInit() {
    const categoryOrCuisine = this.route.snapshot.paramMap.get('categoryOrCuisine');
    const name = this.route.snapshot.paramMap.get('categoryOrCuisineName');
    if (categoryOrCuisine == 'category') {
      this.recipeService.getRecipeByCategory(name)
        .subscribe(data => {
          this.recipe_list = data.content;
        });
    }

    if (categoryOrCuisine == 'cuisine') {
      this.recipeService.getRecipeByCuisine(name)
        .subscribe(data => {
          this.recipe_list = data.content;
        });
    }


  }

}
