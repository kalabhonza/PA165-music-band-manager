import { Component, OnInit } from '@angular/core';
import {MusicianService} from '../../services/musician.service';
import {SessionService} from '../../../shared/services/session.service';
import {Band} from '../../../model/band';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.scss']
})
export class OffersComponent implements OnInit {

  isLoading: boolean;
  musicianId: number;
  offers: Band[];

  constructor(private musicianService: MusicianService, private sessionService: SessionService) { }

  ngOnInit(): void {
    this.musicianId = this.sessionService.getUserId();
    this.getCurrentOffers();
  }

  decline(offerIndex: number): void {
    this.isLoading = true;
    this.musicianService.declineOffer(this.musicianId, this.offers[offerIndex].id).subscribe(
      () => {
        this.isLoading = false;
        this.ngOnInit();
      },
      () => this.isLoading = false
    );
  }

  accept(offerIndex: number): void {
    this.isLoading = true;
    this.musicianService.acceptOffer(this.musicianId, this.offers[offerIndex].id).subscribe(
      () => {
        this.isLoading = false;
        this.ngOnInit();
      },
      () => this.isLoading = false
    );
  }

  private getCurrentOffers(): void {
    this.isLoading = true;
    this.musicianService.getOffers(this.musicianId).subscribe(
      (offers) => {
        this.offers = offers;
      }
    );
  }
}
