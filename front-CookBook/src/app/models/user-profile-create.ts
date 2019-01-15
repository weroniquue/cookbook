export class UserProfileCreate {

    constructor(
        firstName: string,
        secondName: string,
        username: string,
        email: string,
        password: string
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    firstName: string;
    secondName: string;
    username: string;
    email: string;
    password: string;
}