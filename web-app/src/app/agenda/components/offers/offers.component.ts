import {Component, OnDestroy, OnInit} from '@angular/core';
import {MusicianService} from '../../services/musician.service';
import {SessionService} from '../../../shared/services/session.service';
import {Band} from '../../../model/band';
import {ReplaySubject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.scss']
})
export class OffersComponent implements OnInit, OnDestroy {

  isLoading: boolean;
  musicianId: number;
  offers: Band[];

  private destroyed$: ReplaySubject<boolean> = new ReplaySubject(1);

  constructor(private musicianService: MusicianService, private sessionService: SessionService) { }

  ngOnInit(): void {
    this.musicianId = this.sessionService.getUserId();
    this.getCurrentOffers();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }

  decline(offerIndex: number): void {
    this.isLoading = true;
    this.musicianService.declineOffer(this.musicianId, this.offers[offerIndex].id)
      .pipe(takeUntil(this.destroyed$)).subscribe(
      () => {
        this.isLoading = false;
        this.ngOnInit();
      },
      () => this.isLoading = false
    );
  }

  accept(offerIndex: number): void {
    this.isLoading = true;
    this.musicianService.acceptOffer(this.musicianId, this.offers[offerIndex].id)
      .pipe(takeUntil(this.destroyed$)).subscribe(
      () => {
        this.isLoading = false;
        this.ngOnInit();
      },
      () => this.isLoading = false
    );
  }

  private getCurrentOffers(): void {
    this.isLoading = true;
    this.musicianService.getOffers(this.musicianId)
      .pipe(takeUntil(this.destroyed$)).subscribe(
      (offers) => {
        this.offers = offers;
      }
    );
  }
}
