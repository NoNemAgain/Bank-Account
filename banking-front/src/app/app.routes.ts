import { Routes } from '@angular/router';
import { AccountDetailsPage } from './features/account/account-page/account-details-page/account-details-page/account-details-page';
import { AccountPage } from './features/account/account-page/main-account-page/account-page';

export const routes: Routes = [
  {
    path: 'accounts',
    component: AccountPage,
  },
  { path: 'account/:id', component: AccountDetailsPage },
];
