import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountTransactionComponent } from './account-transaction.component';

describe('AccountTransactionComponent', () => {
  let component: AccountTransactionComponent;
  let fixture: ComponentFixture<AccountTransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountTransactionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountTransactionComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
