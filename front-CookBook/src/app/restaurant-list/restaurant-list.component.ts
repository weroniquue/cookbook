import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { RecipeService } from '../recipe.service';
import { Restaurant } from '../models/restaurant';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  constructor(
    private userService: UserService,
    private recipeService: RecipeService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.getRestaurantList();
  }

  restaurant_list: Restaurant[];

  getRestaurantList() {
    this.recipeService.getRestaurants().subscribe(data => {
      console.log(data);
      this.restaurant_list = data.content;
    });
  }

}