import { bankStatement } from './bank.statement';
import { operation } from './operation.model';

export interface Account {
  id: number;
  owner: string;
  balance: number;
  accountType: string;
  operationsHistory: operation[];
  bankStatement: bankStatement[];
}
