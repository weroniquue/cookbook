import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {RecipeService} from '../recipe.service';
import {DeleteConfirmDialogComponent} from '../delete-confirm-dialog/delete-confirm-dialog.component';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {MatDialog} from '@angular/material';
import {MessageService} from '../message.service';

@Component({
  selector: 'app-delete-elements',
  templateUrl: './delete-elements.component.html',
  styleUrls: ['./delete-elements.component.css']
})
export class DeleteElementsComponent implements OnInit {

  categories: any;
  cuisine:any;
  loggedIn: boolean;
  cuisineStatus: false;
  categoryStatus: false;

  constructor( private userService: UserService,
               private recipeService: RecipeService,
               private router: Router,
               private route: ActivatedRoute,
               private location: Location,
               private dialog: MatDialog,
               private messageService: MessageService) {

  }

  ngOnInit() {

    this.loggedIn = this.userService.amILoggedIn();

    this.recipeService.getCategories()
      .subscribe(categoryList => this.categories = categoryList);

    this.recipeService.getCuisine()
      .subscribe( cuisineList => this.cuisine = cuisineList);
  }

  delete(data: any) {

    const id = +this.route.snapshot.paramMap.get('id');

    this.dialog.open(DeleteConfirmDialogComponent, {
      data: {
        title: 'Usuń',
        content: 'Chcesz usunąć element?'
      },
    }).afterClosed()
      .subscribe(d => {
        if (d === 'yes') {
          if (this.cuisineStatus) {
            this.recipeService.deleteCuisine(data)
              .subscribe(() => {
                this.messageService.openSnackBar(`Usunięto pomyślnie`);
                this.recipeService.getCuisine()
                  .subscribe( cuisineList => this.cuisine = cuisineList);
              }, err => {
                this.messageService.openSnackBar(err.error.message);
              });
          }
          if( this.categoryStatus ){
            this.recipeService.deleteCategory(data)
              .subscribe(() => {
                this.messageService.openSnackBar(`Usunięto pomyślnie`);
                this.recipeService.getCategories()
                  .subscribe(categoryList => this.categories = categoryList);
              }, err => {
                this.messageService.openSnackBar(err.error.message);
              });
          }

        }
      });

  }

}
