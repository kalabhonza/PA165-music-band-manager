import {Component, OnDestroy, OnInit} from '@angular/core';
import {BandService} from '../../services/band.service';
import {Band} from '../../../model/band';
import {SessionService} from '../../../shared/services/session.service';
import {concat, EMPTY, ReplaySubject} from 'rxjs';
import {MusicianService} from '../../services/musician.service';
import {exhaustMap, takeUntil} from 'rxjs/operators';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-band',
  templateUrl: './band.component.html',
  styleUrls: ['./band.component.css']
})
export class BandComponent implements OnInit, OnDestroy {

  isLoading: boolean;
  band: Band;
  musicianId: number;

  private destroyed$: ReplaySubject<boolean> = new ReplaySubject(1);

  constructor(
    private bandService: BandService,
    private musicianService: MusicianService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService
  ) { }

  ngOnInit(): void {
    this.musicianId = this.sessionService.getUserId();
    this.getBand();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }

  private getBand(): void {
    this.isLoading = true;
    this.musicianService.getMusicianById(this.musicianId)
      .pipe(
        exhaustMap(
          (musician) => {
            if (musician.band) {
              return this.bandService.getBandById(musician.band);
            } else {
              this.alertMessageService.display('You are not member of any band currently');
              this.isLoading = false;
              return EMPTY;
            }
          }
        ),
        takeUntil(this.destroyed$)
    ).subscribe(
      (band) => {
        this.band = band;
        this.isLoading = false;
      },
      () =>  this.isLoading = false
    );
  }
}
