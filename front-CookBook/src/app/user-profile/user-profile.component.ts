import { Component, OnInit } from '@angular/core';

import { User } from '../models/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUser();
  }

  profile: User;
  
  /*getUser(){
    this.userService.getUser("W1ncenty")
      .subscribe((data: User) => this.profile = {
        username: data['username'],
        firstName: data['firstName'],
        secondName: data['secondName'],
        email: data['email'],
        recipeCount: data['recipeCount'],
        commentCount: data['commentCount']
      });
  }*/

  getUser(){
    this.userService.getUser("W1ncenty")
      .subscribe((data: User) => this.profile = { ...data });
  }

}
