import { operation } from './operation.model';

export interface bankStatement {
  id: string;
  accountType: string;
  balance: number;
  date: Date;
  operations: operation[];
}
