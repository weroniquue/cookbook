import {ReceivedRecipe} from './received-recipe';

export class PagedResponse {
  content: ReceivedRecipe[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;

}
