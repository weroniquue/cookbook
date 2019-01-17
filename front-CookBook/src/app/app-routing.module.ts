import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { RecipeComponent } from './recipe/recipe.component';
import { AccountCreateComponent } from './account-create/account-create.component';
import { AccountEditComponent } from './account-edit/account-edit.component';
import { RecipeByCategoryComponent } from './recipe-by-category/recipe-by-category.component';
import { AccountProfileComponent } from './account-profile/account-profile.component';
import { RecipeNewComponent } from './recipe-new/recipe-new.component';
import { RestaurantListComponent } from './restaurant-list/restaurant-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'recipes', pathMatch: 'full' },
  { path: 'account/profile', component: LoginComponent },
  { path: 'recipes', component: RecipeListComponent },
  { path: 'recipes/:id', component: RecipeComponent },
  { path: 'account/create', component: AccountCreateComponent },
  { path: 'account/edit', component: AccountEditComponent },
  { path: 'recipes/:categoryOrCuisine/:categoryOrCuisineName', component: RecipeByCategoryComponent },
  { path: 'profiles/:username', component: AccountProfileComponent },
  { path: 'recipe/new', component: RecipeNewComponent },
  { path: 'restaurants', component: RestaurantListComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ],
})

export class AppRoutingModule { }
