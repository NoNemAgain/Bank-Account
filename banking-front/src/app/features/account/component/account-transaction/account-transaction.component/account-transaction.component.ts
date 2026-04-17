import { NgClass } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-account-transaction',
  imports: [NgClass, FormsModule],
  templateUrl: './account-transaction.component.html',
  styleUrl: './account-transaction.component.css',
})
export class AccountTransactionComponent {
  amount: number = 0;
  constructor() {}
  @Input() type!: 'withdraw' | 'deposit';
  @Output() action = new EventEmitter<number>();
  submit() {
    this.action.emit(this.amount);
  }
}
