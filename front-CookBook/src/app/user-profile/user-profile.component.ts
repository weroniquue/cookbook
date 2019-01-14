import { Component, OnInit } from '@angular/core';

import { UserProfileData } from '../models/user-profile-data';
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

  profile: UserProfileData;

  getUser(){
    this.userService.getUserDetails("W1ncenty")
      .subscribe((data: UserProfileData) => this.profile = { ...data });
  }

}
