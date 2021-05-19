import { Component, OnInit } from '@angular/core';
import {BandService} from '../../services/band.service';
import {Band} from '../../../model/band';
import {SessionService} from '../../../shared/services/session.service';
import {concat, EMPTY} from 'rxjs';
import {MusicianService} from '../../services/musician.service';
import {exhaustMap} from 'rxjs/operators';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-band',
  templateUrl: './band.component.html',
  styleUrls: ['./band.component.css']
})
export class BandComponent implements OnInit {

  isLoading: boolean;
  musicianBand: Band;
  musicianId: number;

  constructor(
    private bandService: BandService,
    private musicianService: MusicianService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService
  ) { }

  ngOnInit(): void {
    this.musicianId = this.sessionService.getUserId();
  }

  private getBand(): void {
    this.isLoading = true;
    this.musicianService.getMusicianById(this.musicianId)
      .pipe(
        exhaustMap(
          (musician) => {
            if (musician.band) {
              return this.bandService.getBandById(musician.band.id);
            } else {
              this.alertMessageService.display('You are not member of any band currently');
              this.isLoading = false;
              return EMPTY;
            }
          }
        )
    ).subscribe(
      (band) => {
        this.musicianBand = band;
        this.isLoading = false;
      },
      () =>  this.isLoading = false
    );
  }
}
