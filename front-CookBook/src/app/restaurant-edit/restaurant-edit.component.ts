import {Component, OnInit} from '@angular/core';
import {RecipeService} from '../recipe.service';
import {UserService} from '../user.service';
import {MessageService} from '../message.service';
import {Location} from '@angular/common';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ReceivedRecipe } from '../models/received-recipe';

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

  constructor(private recipeService: RecipeService,
              private userService: UserService,
              private messageService: MessageService,
              private location: Location,
              private fb: FormBuilder,
              private route: ActivatedRoute
    ) {
  }

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
      });


  }

  goBack(){
    this.location.back();
  }

  editRestaurant(data:any){
    const result = Object.assign({},
      this.editRestaurantForm.value, {
        recipes: this.recipe_list
          .filter((x, i) => !!this.editRestaurantForm.value.recipes[i])});

    console.log(result);
  }

}
