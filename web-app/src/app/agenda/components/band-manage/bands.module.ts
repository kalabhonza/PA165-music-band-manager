import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BandManageComponent} from './band-manage.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {BandsRoutingModule} from './bands-routing.module';
import {BandService} from '../../services/band.service';
import {BandApiService} from '../../../api/services/band-api.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';
import {ManagerService} from '../../services/manager.service';
import {ManagerApiService} from '../../../api/services/manager-api.service';

@NgModule({
  declarations: [BandManageComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    BandsRoutingModule
  ],
  providers: [BandService, BandApiService, ErrorAlertService, ManagerService, ManagerApiService]
})
export class BandsModule { }
