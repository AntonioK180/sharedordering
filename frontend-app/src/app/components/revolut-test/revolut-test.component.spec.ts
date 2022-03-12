import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevolutTestComponent } from './revolut-test.component';

describe('RevolutTestComponent', () => {
  let component: RevolutTestComponent;
  let fixture: ComponentFixture<RevolutTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevolutTestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevolutTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
