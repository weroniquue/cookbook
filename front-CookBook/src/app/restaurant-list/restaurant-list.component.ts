import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { RecipeService } from '../recipe.service';
import { Restaurant } from '../models/restaurant';
import { MessageService } from '../message.service';
import {DeleteConfirmDialogComponent} from '../delete-confirm-dialog/delete-confirm-dialog.component';
import {MatDialog} from '@angular/material';
import {RestaurantService} from '../restaurant.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  restaurant_list: Restaurant[];
  loggedIn: boolean;

  constructor(
    private userService: UserService,
    private recipeService: RecipeService,
    private restuarantService: RestaurantService,
    private messageService: MessageService,
    private dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.getRestaurantList();
    this.loggedIn = this.userService.amILoggedIn();
  }

  getRestaurantList() {
    this.recipeService.getRestaurants()
      .subscribe(data => {
        console.log(data);
        this.restaurant_list = data;
      });
  }

  deleteRestaurant(city: string, name: string) {

    this.dialog.open(DeleteConfirmDialogComponent, {
      data: {
        title: 'Usuń?',
        content: 'Chcesz usunąć restaurację?'
      },
    }).afterClosed()
      .subscribe(data => {
        if (data === 'yes') {
          this.recipeService.deleteRestaurant(city, name)
            .subscribe(dat => {
              //this.messageService.openSnackBar(dat.message)
              this.getRestaurantList();
            });

        }
      });

  }

  deleteRecipe(recipe:any, restaurant:any){
    console.log(recipe, restaurant);

    this.dialog.open(DeleteConfirmDialogComponent, {
      data: {
        title: 'Usuń?',
        content: 'Chcesz usunąć przepis z restauracji?'
      },
    }).afterClosed()
      .subscribe(x => {
        if (x === 'yes') {
          this.restuarantService.removeRecipeFromRestaurant(restaurant.city, restaurant.name, recipe.id)
            .subscribe(dat => {
              //this.messageService.openSnackBar(dat.message)
              this.getRestaurantList();
            });

        }
      });
  }

}
