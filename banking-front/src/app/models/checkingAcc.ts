
import { Account } from './account.model';
import { bankStatement } from './bank.statement';
import { operation } from './operation.model';

export interface CheckingAcc extends Account{
 overdraftAllowed : boolean;
 overdraftLimit : number;
}
