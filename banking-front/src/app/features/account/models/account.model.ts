import { bankStatement } from '../../../models/bank.statement';
import { operation } from '../../../models/operation.model';

export interface Account {
  id: number;
  owner: string;
  balance: number;
  accountType: string;
  operationsHistory: operation[];
  bankStatement: bankStatement[];
}
