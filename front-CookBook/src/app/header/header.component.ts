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
  recipe_list: Recipe[] = [];
 
  constructor(
    private userService: UserService
  ) { }
 
  loggedIn: boolean;

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
  }

  logOut() {
    this.userService.logout();
  }
 
  /*getRecipes(): void {
    this.recipeService.getRecipes()
      .subscribe(recipe_list => this.recipe_list = recipe_list.slice(1, 5));
  }*/
}