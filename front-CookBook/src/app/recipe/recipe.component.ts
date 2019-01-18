import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';

import {Comment} from '../models/comment';
import {RecipeService} from '../recipe.service';
import {ReceivedRecipe} from '../models/received-recipe';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog} from '@angular/material';
import {DeleteConfirmDialogComponent} from '../delete-confirm-dialog/delete-confirm-dialog.component';
import {CommentResponse} from '../models/commentResponse';
import {MessageService} from '../message.service';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  recipe: ReceivedRecipe;
  allComments: any;
  newComment: Comment;
  createCommentForm: FormGroup;
  displayedColumns: string[] = ['nazwa', 'ilość', 'jednostka'];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private location: Location,
    private dialog: MatDialog,
    private messageService: MessageService
  ) {
  }

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
      console.log(this.recipe.photos[0]);
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

  deleteRecipe() {
    const id = +this.route.snapshot.paramMap.get('id');

    this.dialog.open(DeleteConfirmDialogComponent, {
      data: {
        title: 'Usuń przepis',
        content: 'Chcesz usunąć przepis?'
      },
    }).afterClosed()
      .subscribe(data => {
        if (data === 'yes') {
          this.recipeService.deleteRecipe(id).subscribe(
            () => {
              this.messageService.openSnackBar(`Usunięto pomyślnie`);
              setTimeout(() => {
                this.router.navigate(['recipes']);
              }, 10);  //1s

            }
          );
        }
      });

  }

  editRecipe() {
    const id = +this.route.snapshot.paramMap.get('id');
    console.log('edytuj');
  }

  addComments() {
    this.newComment = new Comment(this.createCommentForm.controls['commentInput'].value);

    const id = +this.route.snapshot.paramMap.get('id');
    this.recipeService.createComment(id, this.newComment)
      .subscribe(data => {
        console.log(data);
        this.messageService.openSnackBar(`Komentarz dodano`);
        this.getComments();
        this.createCommentForm.reset();
      }, err =>
        console.log(err));
  }

  checkIfYourComment(username: string) {
    if (username === localStorage.getItem('cookbook_username')) {
      return true;
    } else {
      return false;
    }

  }

  deleteComment(comment: CommentResponse): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.dialog.open(DeleteConfirmDialogComponent, {
      data: {
        title: 'Are you sure?',
        content: 'Do you want to delete your comment?'
      },
    }).afterClosed()
      .subscribe(data => {
        if (data === 'yes') {
          this.allComments = this.allComments.filter(h => h !== comment);
          this.recipeService.deleteComment(id, comment).subscribe();

        }
      });


  }


  //@Input() recipe: Recipe;

}
