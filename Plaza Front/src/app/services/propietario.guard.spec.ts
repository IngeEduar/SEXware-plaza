import { TestBed } from '@angular/core/testing';

import { PropietarioGuard } from './propietario.guard';

describe('PropietarioGuard', () => {
  let guard: PropietarioGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(PropietarioGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
