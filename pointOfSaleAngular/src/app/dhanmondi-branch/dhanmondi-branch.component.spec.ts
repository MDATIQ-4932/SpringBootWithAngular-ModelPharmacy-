import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DhanmondiBranchComponent } from './dhanmondi-branch.component';

describe('DhanmondiBranchComponent', () => {
  let component: DhanmondiBranchComponent;
  let fixture: ComponentFixture<DhanmondiBranchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DhanmondiBranchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DhanmondiBranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
