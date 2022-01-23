import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableStoresComponent } from './available-stores.component';

describe('AvailableStoresComponent', () => {
  let component: AvailableStoresComponent;
  let fixture: ComponentFixture<AvailableStoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableStoresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableStoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
