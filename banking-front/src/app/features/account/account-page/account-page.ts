import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountService } from '../../../core/service/AccountService/account.service';
import { Account } from '../../../models/account.model';
import { AccountListComponent } from '../component/account-list.component/account-list.component';

@Component({
  selector: 'app-account-page',
  imports: [AccountListComponent, AsyncPipe],
  templateUrl: './account-page.html',
  styleUrl: './account-page.css',
})
export class AccountPage {
  accounts$: Observable<Account[]>;
  loading = true;

  constructor(private accountService: AccountService) {
    console.log('COMPONENT CHARGÉ');
    this.accounts$ = this.accountService.getAccounts();
    console.log('data', this.accounts$);
  }
}
