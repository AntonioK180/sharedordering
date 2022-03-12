import { TestBed } from '@angular/core/testing';

import { RevolutService } from './revolut.service';

describe('RevolutService', () => {
  let service: RevolutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RevolutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
