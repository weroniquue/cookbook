export class UserProfileEdit {

    constructor (
        firstName: string,
        secondName: string,
        email: string
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    firstName: string;
    secondName: string;
    email: string;
}