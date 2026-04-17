import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Account } from '../../../../models/account.model';

@Component({
  selector: 'app-account-list',
  imports: [NgFor],
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.css',
})
export class AccountListComponent {
  @Input() accounts: Account[] | null = [];

  ngOnChanges() {
    console.log('CHILD INPUT =', this.accounts);
  }
}
