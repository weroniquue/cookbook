import { Component, OnInit } from '@angular/core';
import { ExportUser } from '../models/export-user';
import { UserService } from '../user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: ExportUser = {
    usernameOrEmail: "",
    password: ""
  };
  accessToken: string;

  constructor (private userService: UserService) {}

  ngOnInit() {
  }

  onClick(username: string, password: string): void {
    this.user.usernameOrEmail = username;
    this.user.password = password;
    // logowanie:
    this.userService.login(this.user).subscribe(data => this.accessToken = data['accessToken']);
    // zapisanie klucza użytkownika:
    this.userService.addAuthenticationToken(this.accessToken);
  }

}
