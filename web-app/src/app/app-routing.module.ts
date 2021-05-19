import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './shared/components/home/home.component';
import {AuthUserGuard} from './shared/services/auth-user-guard.service';
import {AuthManagerGuard} from './shared/services/auth-manager-guard.service';

const routes: Routes = [
  {
    path: 'pa165',
    component: HomeComponent,
  },
  {
    path: 'bands',
    canActivate: [AuthManagerGuard],
    loadChildren: () => import('./agenda/components/bands/bands.module').then(m => m.BandsModule),
    data: {breadcrumb: 'Bands'}
  },
  {
    path: 'musicians',
    canActivate: [AuthManagerGuard],
    loadChildren: () => import('./agenda/components/musicians/musicians.module').then(m => m.MusiciansModule),
    data: {breadcrumb: 'Musicians'}
  },
  {
    path: 'band',
    canActivate: [AuthUserGuard],
    loadChildren: () => import('./agenda/components/band/band.module').then(m => m.BandModule),
    data: {breadcrumb: 'Band'}
  },
  {
    path: 'profile',
    loadChildren: () => import('./agenda/components/profile/profile.module').then(m => m.ProfileModule),
    data: {breadcrumb: 'Profile'}
  },
  {
    path: 'offers',
    canActivate: [AuthUserGuard],
    loadChildren: () => import('./agenda/components/offers/offers.module').then(m => m.OffersModule),
    data: {breadcrumb: 'Offers'}
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
