export class UserLoginData {

    constructor (
        usernameOrEmail: string,
        password: string
    ) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    usernameOrEmail: string;
    password: string;
}