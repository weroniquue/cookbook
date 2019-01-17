import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteElementsComponent } from './delete-elements.component';

describe('DeleteElementsComponent', () => {
  let component: DeleteElementsComponent;
  let fixture: ComponentFixture<DeleteElementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteElementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteElementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
