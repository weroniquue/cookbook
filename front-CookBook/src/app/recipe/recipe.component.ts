import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Comment } from '../models/comment';
import { RecipeService } from '../recipe.service';
import {ReceivedRecipe} from '../models/received-recipe';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CommentResponse} from '../models/commentResponse';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  recipe: ReceivedRecipe;
  allComments: any;//CommentResponse[];

  newComment: Comment;
  createCommentForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getRecipe();
    this.createCommentForm = new FormGroup({
      commentInput: new FormControl('', [Validators.required, Validators.maxLength(100)])
    });

    this.getComments();


  }

  getRecipe(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.recipeService.getRecipeById(id).subscribe(recipe => {
      this.recipe = recipe;
    });
  }

  getComments(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.recipeService.getComments(id)
      .subscribe(comments => {
        this.allComments = comments;
        console.log(comments);
      });
  }

  goBack(): void {
    this.location.back();
  }

  addComments() {
    this.newComment = new Comment(this.createCommentForm.controls['commentInput'].value);

    const id = +this.route.snapshot.paramMap.get('id');
    this.recipeService.createComment(id, this.newComment)
      .subscribe(data => {
        console.log(data);
      }, err =>
        console.log(err));

    this.getComments()

  }

  //@Input() recipe: Recipe;

}
