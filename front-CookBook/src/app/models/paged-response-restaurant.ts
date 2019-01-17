import { Restaurant } from './restaurant';

export class PagedResponseRestaurant {
  content: Restaurant[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}
