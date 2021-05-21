import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MusiciansComponent} from './musicians.component';
import {MusiciansRoutingModule} from './musicians-routing.module';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {MusicianService} from '../../services/musician.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';
import {MusicianApiService} from '../../../api/services/musician-api.service';
import {ManagerService} from '../../services/manager.service';
import {ManagerApiService} from '../../../api/services/manager-api.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';



@NgModule({
  declarations: [MusiciansComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    MusiciansRoutingModule
  ],
  providers: [
    MusicianService,
    MusicianApiService,
    ManagerService,
    ManagerApiService,
    AlertMessageService,
    ErrorAlertService
  ]
})
export class MusiciansModule { }
