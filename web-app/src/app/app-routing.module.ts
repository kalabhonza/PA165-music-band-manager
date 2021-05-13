import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './shared/components/home/home.component';

const routes: Routes = [
  {
    path: 'pa165',
    component: HomeComponent,
  },
  {
    path: 'bands',
    // canActivate: [AuthGuard],
    loadChildren: () => import('./agenda/components/bands/bands.module').then(m => m.BandsModule),
    data: {breadcrumb: 'Bands'}
  },
  {
    path: '',
    redirectTo: 'pa165',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
