import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../recipe.service';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-recipe-new',
  templateUrl: './recipe-new.component.html',
  styleUrls: ['./recipe-new.component.css']
})
export class RecipeNewComponent implements OnInit {

  constructor(
    private recipeService: RecipeService,
    private userService: UserService,
    private messageService: MessageService,
    private location: Location
  ) { }

  loggedIn: boolean;
  categories: any;
  cuisine: any;
  
  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);
  }

  goBack() {
    this.location.back();
  }

}
