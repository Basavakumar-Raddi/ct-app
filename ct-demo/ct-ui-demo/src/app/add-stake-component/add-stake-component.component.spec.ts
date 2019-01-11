import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStakeComponentComponent } from './add-stake-component.component';

describe('AddStakeComponentComponent', () => {
  let component: AddStakeComponentComponent;
  let fixture: ComponentFixture<AddStakeComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStakeComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStakeComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
