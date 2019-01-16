import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const username = this.route.snapshot.paramMap.get('username');
  }

}
