import { Component, OnInit } from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Recipe} from '../models/recipe';
import {Restaurant} from '../models/restaurant';
import {RestaurantService} from '../restaurant.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-restaurant-add',
  templateUrl: './restaurant-add.component.html',
  styleUrls: ['./restaurant-add.component.css']
})
export class RestaurantAddComponent implements OnInit {

  loggedIn: boolean;
  newRestaurant: Restaurant;
  message: string;
  createRestaurantForm: FormGroup;

  constructor(
    private recipeService: RecipeService,
    private userService: UserService,
    private messageService: MessageService,
    private location: Location,
    private fb: FormBuilder,
    private restaurantService: RestaurantService,
    private router: Router,
  ) {

    this.createRestaurantForm = this.fb.group({
      name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      city: new FormControl('', [Validators.required, Validators.maxLength(30)]),
      code: new FormControl('', [Validators.required, Validators.maxLength(6), Validators.pattern(/\d{2}(?:-?\d{3})?/)]),
      address: new FormControl('', [Validators.required]),
      recipes: null//this.fb.array([this.initIngredientsFields()]),
    });
  }

  ngOnInit() {
    this.loggedIn = this.userService.amILoggedIn();
  }

  public hasError(controlName: string, errorName: string) {
    return this.createRestaurantForm.controls[controlName].hasError(errorName);
  }

  createRestaurant(val : any){
    console.dir(val);

    // utworzenie przepisu:
    this.restaurantService.createRestaurant(val).subscribe(
      data => {
        this.message = data['message'];
        this.messageService.openSnackBar(this.message);
        setTimeout(() => {
          this.router.navigate(['restaurants']);
        }, 2000);  //1s

      }, error => {
        this.messageService.openSnackBar(error);
        console.log(error);
      }
    );
  }

}
