import { TestBed } from '@angular/core/testing';

import { SeleniumService } from './selenium.service';

describe('SeleniumService', () => {
  let service: SeleniumService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeleniumService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
