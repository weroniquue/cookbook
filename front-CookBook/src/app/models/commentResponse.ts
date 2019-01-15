import {ReceivedRecipe} from './received-recipe';
import {UserProfileData} from './user-profile-data';

export class CommentResponse {
  id: number;
  idRecipe: number;
  users: UserProfileData;
  comment: string;
  date: string;

}
