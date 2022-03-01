import { TestBed } from '@angular/core/testing';

import { SeleniumServiceService } from './selenium-service.service';

describe('SeleniumServiceService', () => {
  let service: SeleniumServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeleniumServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
