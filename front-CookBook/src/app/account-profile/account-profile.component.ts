import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserProfileData } from '../models/user-profile-data';
import { UserService } from '../user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit {

  userProfileData: UserProfileData;
  

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location
  ) { }

  ngOnInit() {
    const username = this.route.snapshot.paramMap.get('username');
    this.userService.getUserDetails(username)
      .subscribe((data: UserProfileData) => this.userProfileData = { ...data },
      err => {
        console.log(err);
      });
  }

  goBack() {
    this.location.back();
  }

}
