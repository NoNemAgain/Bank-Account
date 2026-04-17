import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../../../models/account.model';
import { CheckingAcc } from '../../../models/checkingAcc';
import { SavingAcc } from '../../../models/savingAcc';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private apiURL = 'http://localhost:8080/api/bank-accounts';

  constructor(private http: HttpClient) {}

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.apiURL);
  }
  getAccountId(id: string): Observable<Account> {
    return this.http.get<Account>('${this.apiURL}/${id}');
  }
  createCheckAcc(account: CheckingAcc): Observable<CheckingAcc> {
    return this.http.post<CheckingAcc>(`${this.apiURL}/checking`, account);
  }
  createSavingAcc(account: SavingAcc): Observable<SavingAcc> {
    return this.http.post<SavingAcc>(`${this.apiURL}/saving`, account);
  }
  getAccountByOwner(owner: string): Observable<Account> {
    let params = new HttpParams();
    params.set('owner', owner);

    return this.http.get<Account>(`${this.apiURL}/owner`, { params });
  }
}
