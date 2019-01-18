import {Component, OnInit} from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import { ReceivedRecipe } from '../models/received-recipe';
import {RestaurantService} from '../restaurant.service';


interface addRecipe {
  recipes: any;
}

@Component({
  selector: 'app-restaurant-edit',
  templateUrl: './restaurant-edit.component.html',
  styleUrls: ['./restaurant-edit.component.css']
})
export class RestaurantEditComponent implements OnInit {

  cityR: string;
  nameR: string;
  editRestaurantForm: FormGroup;
  recipe_list: ReceivedRecipe[];
  list_id = [];
  newList: ReceivedRecipe[];

  constructor(private recipeService: RecipeService,
              private userService: UserService,
              private restaurantService: RestaurantService,
              private messageService: MessageService,
              private location: Location,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
    ) { }

  ngOnInit() {
    this.cityR = this.route.snapshot.paramMap.get('city');
    this.nameR = this.route.snapshot.paramMap.get('name');

    this.editRestaurantForm = this.fb.group({
      recipes: this.fb.array([])
    });

    this.recipeService.getRecipes()
      .subscribe(data => {
        console.log(data);
        this.recipe_list = data.content;


        const formArray = this.editRestaurantForm.get('recipes') as FormArray;
        this.recipe_list.forEach(x => formArray.push(new FormControl(false)));
        this.getRecipeListNoRepeat();
    });   
  }

  goBack(){
    this.location.back();
  }

  check: boolean;

  getRecipeListNoRepeat() {
    this.newList = [];
    for (let recipe of this.recipe_list) {
      this.check = false;
      for (let restaurant of recipe.restaurants) {
        if (restaurant.name == this.nameR) {
          if (restaurant.city == this.cityR) {
            this.check = true;
          }
        }
      }
      if (!this.check) this.newList.push(recipe);
    }
  }

  editRestaurant(data: any){
    const result = Object.assign({},
      this.editRestaurantForm.value, {
        recipes: this.recipe_list
          .filter((x, i) => !!this.editRestaurantForm.value.recipes[i])});

    for (let obj of result.recipes){
      this.list_id.push(obj.id);
    }

    this.restaurantService.addRecipesToRestaurant(JSON.stringify({ recipes: this.list_id}),
      this.nameR, this.cityR)
      .subscribe(da => {
        this.list_id=[];
        this.messageService.openSnackBar(da['message']);
        setTimeout(() => {
          this.router.navigate(['restaurants']);
        }, 1000);
      });

  }

}
