import { Component, OnInit } from '@angular/core';
import { Recipe } from '../models/recipe';
import { UserService } from '../user.service';
import { RecipeService } from '../recipe.service';
import { MessageService } from '../message.service'; 

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [ './header.component.css' ]
})
export class HeaderComponent implements OnInit {

  recipe_list: any;
  loggedIn: boolean;
  
  categories: any;
  cuisine: any;

  categories_taken: string[];
  cuisine_taken: string[];

  constructor(
    private userService: UserService,
    private recipeService: RecipeService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
    this.recipeService.getRecipes().subscribe(data => {
      this.recipe_list = data.content;

      this.recipeService.getCategories().subscribe(categoryList => {
        this.categories = categoryList;

        this.recipeService.getCuisine().subscribe(cuisineList => {
          this.cuisine = cuisineList;

          this.categories_taken = [];
          this.cuisine_taken = [];
          this.updateCatAndCui();

        });
      });
    });
  }

  // only displays categories that have recipes in them in the menu
  updateCatAndCui() {
    for (let cat of this.categories) {
      for (let recipe of this.recipe_list) {
        if (recipe.category == cat) {
          this.categories_taken.push(cat);
          break;
        }
      }
    }
    for (let cui of this.cuisine) {
      for (let recipe of this.recipe_list) {
        if (recipe.cuisine == cui) {
          this.cuisine_taken.push(cui);
          break;
        }
      }
    }
  }

  logOut() {
    this.userService.logout();
  }      

}
