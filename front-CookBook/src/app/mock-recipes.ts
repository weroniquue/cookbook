import { Recipe } from './recipe';
import { markParentViewsForCheckProjectedViews } from '@angular/core/src/view/util';

export const mock_recipes: Recipe[] = [
  {   
      title: "Przepis1",
      description: "opis1",
      cuisineName: "włoska",
      category: "śniadanie",
      ingredients: [{
          name: "marchew",
          amount: 1
      }],
      photos: [],
      steps: [
      {
          id: 1,
          description: "krok1przepisu1"
      },
      {
          id: 2,
          description: "krok2przepisu1"
      }]
 },
 {   
    title: "Przepis2",
    description: "opis2",
    cuisineName: "polska",
    category: "obiad",
    ingredients: [{
        name: "smalec",
        amount: 2
    }],
    photos: [],
    steps: [
    {
        id: 1,
        description: "krok1przepisu2"
    },
    {
        id: 2,
        description: "krok2przepisu2"
    }]
},
{   
    title: "Przepis3",
    description: "opis3",
    cuisineName: "szkocka",
    category: "deser",
    ingredients: [{
        name: "jelito owcy",
        amount: 1
    }],
    photos: [],
    steps: [
    {
        id: 1,
        description: "krok1przepisu3"
    },
    {
        id: 2,
        description: "krok2przepisu3"
    }]
}
]