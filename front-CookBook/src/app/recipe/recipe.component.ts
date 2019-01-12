import { Component, OnInit, Input } from '@angular/core';

import { Recipe } from '../models/recipe';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input() recipe: Recipe;

}
