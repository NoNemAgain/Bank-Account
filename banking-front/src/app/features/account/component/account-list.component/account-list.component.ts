import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Account } from '../../models/account.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-account-list',
  imports: [NgFor,RouterLink],
  templateUrl: './account-list.component.html',
  styleUrl: './account-list.component.css',
})
export class AccountListComponent {
  @Input() accounts: Account[] | null = [];

  ngOnChanges() {
    console.log('CHILD INPUT =', this.accounts);
  }
}
