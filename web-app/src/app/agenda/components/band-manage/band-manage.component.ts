import { Component, OnInit } from '@angular/core';
import {BandService} from '../../services/band.service';
import {Band} from '../../../model/band';
import {ManagerService} from '../../services/manager.service';
import {SessionService} from '../../../shared/services/session.service';
import {exhaustMap} from 'rxjs/operators';
import {EMPTY} from 'rxjs';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-bands',
  templateUrl: './band-manage.component.html',
  styleUrls: ['./band-manage.component.css']
})
export class BandManageComponent implements OnInit {

  isLoading: boolean;
  managerId: number;
  band: Band;

  constructor(private bandsService: BandService, private managerService: ManagerService, private sessionService: SessionService, private alertMessageService: AlertMessageService) { }

  ngOnInit(): void {
    this.managerId = this.sessionService.getUserId();
    this.loadBand();
  }

  loadBand(): void {
    this.isLoading = true;
    this.managerService.getManagerById(this.managerId)
      .pipe(
        exhaustMap(
          (manager) => {
            if (manager.bandId) {
              return this.managerService.getManagerBand(manager.bandId);
            } else {
              this.alertMessageService.display('You are not managing any band currently');
              this.isLoading = false;
              return EMPTY;
            }
          }
        )
      ).subscribe(
      (band) => {
        this.band = band;
        this.isLoading = false;
      },
      () =>  this.isLoading = false
    );
  }
}
