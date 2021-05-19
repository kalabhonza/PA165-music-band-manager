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
    path: 'musicians',
    // canActivate: [AuthGuard],
    loadChildren: () => import('./agenda/components/musicians/musicians.module').then(m => m.MusiciansModule),
    data: {breadcrumb: 'Musicians'}
  },
  {
    path: 'band',
    // canActivate: [AuthGuard],
    loadChildren: () => import('./agenda/components/band/band.module').then(m => m.BandModule),
    data: {breadcrumb: 'Band'}
  },
  {
    path: 'profile',
    // canActivate: [AuthGuard],
    loadChildren: () => import('./agenda/components/profile/profile.module').then(m => m.ProfileModule),
    data: {breadcrumb: 'Profile'}
  },
  {
    path: 'offers',
    // canActivate: [AuthGuard],
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
