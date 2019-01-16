<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models/recipe';
import { UserService } from '../user.service';
import { LoginComponent } from '../login/login.component';
 
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [ './header.component.css' ]
})
export class HeaderComponent implements OnInit {
 
  constructor(
    private userService: UserService
  ) { }
 
  //loggedIn: boolean;

  ngOnInit() {
    //this.loggedIn = this.userService.amILoggedIn();
  }

  /*logOut() {
    this.userService.logout();
  }*/
 
  /*getRecipes(): void {
    this.recipeService.getRecipes()
      .subscribe(recipe_list => this.recipe_list = recipe_list.slice(1, 5));
  }*/
}
=======
import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models/recipe';
import { UserService } from '../user.service';
import { LoginComponent } from '../login/login.component';
import {RecipeService} from '../recipe.service';
 
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

  /*getRecipes(): void {
    this.recipeService.getRecipes()
      .subscribe(recipe_list => this.recipe_list = recipe_list.slice(1, 5));
  }*/
}
>>>>>>> 82e9836d9b5b73c9eacfc0abccce6a9ed3d33d39
