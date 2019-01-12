import { Component, OnInit } from '@angular/core';

import { User } from '../models/user';

import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = {
    name: "",
    password: ""
  };

  constructor () {}

  ngOnInit() {
  }

}
