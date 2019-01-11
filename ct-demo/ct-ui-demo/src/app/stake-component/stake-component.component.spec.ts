import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StakeComponentComponent } from './stake-component.component';

describe('StakeComponentComponent', () => {
  let component: StakeComponentComponent;
  let fixture: ComponentFixture<StakeComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StakeComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StakeComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
