import { AsyncPipe, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Account } from '../../../models/account.model';
import { AccountService } from '../../../service/AccountService/account.service';

@Component({
  selector: 'app-account-details',
  imports: [AsyncPipe, NgIf],
  templateUrl: './account-details-page.html',
  styleUrl: './account-details-page.css',
})
export class AccountDetailsPage {
  account$!: Observable<Account>;
  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService,
  ) {
    const id = this.route.snapshot.paramMap.get('id');

    if (!id) return;

    this.account$ = this.accountService.getAccountId(id);

    console.log('account', this.account$);
  }
}
