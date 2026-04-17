import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountTransactionComponent } from '../../../component/account-transaction/account-transaction.component/account-transaction.component';
import { Account } from '../../../models/account.model';
import { AccountService } from '../../../service/AccountService/account.service';

@Component({
  selector: 'app-account-details',
  imports: [AsyncPipe, NgIf, AccountTransactionComponent],
  templateUrl: './account-details-page.html',
  styleUrl: './account-details-page.css',
})
export class AccountDetailsPage {
  account$!: Observable<Account>;
  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
    private cdr: ChangeDetectorRef,
  ) {
    const id = this.route.snapshot.paramMap.get('id');

    if (!id) return;

    this.account$ = this.accountService.getAccountId(id);

    console.log('account', this.account$);
  }
  handleWithdraw(amount: number) {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.withdraw(id, amount);
  }

  withdraw(idAccount: string, amount: number) {
    this.accountService.withdraw(idAccount, amount).subscribe(() => {
      this.refreshAccount(idAccount);
    });
  }

  refreshAccount(idAccount: string) {
    this.account$ = this.accountService.getAccountId(idAccount);
    this.cdr.detectChanges();
  }

  handleDeposit(amount: number) {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;
    this.deposit(id, amount);
  }

  deposit(idAccount: string, amount: number) {
    this.accountService.deposit(idAccount, amount).subscribe(() => {
      this.refreshAccount(idAccount);
    });
  }
}
