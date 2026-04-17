
import { Account } from '../features/account/models/account.model';
import { bankStatement } from './bank.statement';
import { operation } from './operation.model';

export interface SavingAcc extends Account{
 balanceLimit : number;
}
