import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from './profile.component';
import {SessionService} from '../../../shared/services/session.service';



@NgModule({
  declarations: [ProfileComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    ProfileRoutingModule
  ],
  providers: [SessionService]
})
export class ProfileModule { }
