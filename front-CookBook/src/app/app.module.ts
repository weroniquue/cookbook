import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RecipeComponent } from './recipe/recipe.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';

import {FormsModule, ReactiveFormsModule} from '@angular/forms'; // <-- NgModel here

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { CookieService } from 'ngx-cookie-service';
import { AccountCreateComponent } from './account-create/account-create.component';
import { AccountEditComponent } from './account-edit/account-edit.component';
import {
  MAT_DIALOG_DEFAULT_OPTIONS,
  MatDialog,
  MatDialogModule,
  MatExpansionModule,
  MatIconModule,
  MatListModule,
  MatMenuModule,
  MatTreeModule
} from '@angular/material';
import { FilterTitlePipe } from './recipe-list/filter-title.pipe';
import { SortPipe } from './recipe-list/sort.pipe';
import { RecipeByCategoryComponent } from './recipe-by-category/recipe-by-category.component';
import { RecipeNewComponent } from './recipe-new/recipe-new.component';
import { AccountProfileComponent } from './account-profile/account-profile.component';
import { DeleteConfirmDialogComponent } from './delete-confirm-dialog/delete-confirm-dialog.component';
import { MatSelectModule } from '@angular/material/select';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';
import {RecipeEditComponent} from './recipe-edit/recipe-edit.component';
import { RestaurantAddComponent } from './restaurant-add/restaurant-add.component';
import { AddCategoryDialogComponent } from './add-category-dialog/add-category-dialog.component';
import { AddIngredientComponent } from './add-ingredient/add-ingredient.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeComponent,
    HeaderComponent,
    LoginComponent,
    RecipeListComponent,
    MessagesComponent,
    AccountCreateComponent,
    AccountEditComponent,
    FilterTitlePipe,
    SortPipe,
    RecipeByCategoryComponent,
    RecipeNewComponent,
    AccountProfileComponent,
    DeleteConfirmDialogComponent,
    RestaurantListComponent,
    RecipeEditComponent,
    RestaurantAddComponent,
    AddCategoryDialogComponent,
    AddIngredientComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatExpansionModule,
    MatTreeModule,
    MatIconModule,
    MatListModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatSnackBarModule,
    MatDialogModule,
    MatSelectModule
  ],
  providers: [CookieService,
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: true}}
    ],
  bootstrap: [AppComponent],
  entryComponents: [DeleteConfirmDialogComponent,
  AddCategoryDialogComponent,
  AddIngredientComponent],
})
export class AppModule { }
