import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileComponent} from './profile.component';
import {SessionService} from '../../../shared/services/session.service';
import {ManagerApiService} from '../../../api/services/manager-api.service';
import {MusicianService} from '../../services/musician.service';
import {MusicianApiService} from '../../../api/services/musician-api.service';
import {ManagerService} from '../../services/manager.service';



@NgModule({
  declarations: [ProfileComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    ProfileRoutingModule
  ],
  providers: [SessionService, ManagerService, ManagerApiService, MusicianService, MusicianApiService]
})
export class ProfileModule { }
