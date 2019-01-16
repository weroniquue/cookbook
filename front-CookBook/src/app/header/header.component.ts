import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models/recipe';
import { UserService } from '../user.service';
import { RecipeService } from '../recipe.service';
 
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [ './header.component.css' ]
})
export class HeaderComponent implements OnInit {
  recipe_list: Recipe[] = [];
  categories: any;
  cuisine:any;
  loggedIn: boolean;
 
  constructor(
    private userService: UserService,
    private recipeService: RecipeService
  ) { }

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);
  }

  logOut() {
    this.userService.logout();
  }

}
