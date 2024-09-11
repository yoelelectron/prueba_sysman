import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ByTypeDatePageComponent } from './by-type-date-page.component';

describe('ByTypeDatePageComponent', () => {
  let component: ByTypeDatePageComponent;
  let fixture: ComponentFixture<ByTypeDatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ByTypeDatePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ByTypeDatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
