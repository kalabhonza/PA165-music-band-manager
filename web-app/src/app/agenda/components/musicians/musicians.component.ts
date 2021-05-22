import { Component, OnInit } from '@angular/core';
import {MusicianService} from '../../services/musician.service';
import {Musician} from '../../../model/musician';
import {ManagerService} from '../../services/manager.service';
import {catchError, exhaustMap} from 'rxjs/operators';
import {EMPTY} from 'rxjs';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-musicians',
  templateUrl: './musicians.component.html',
  styleUrls: ['./musicians.component.css']
})
export class MusiciansComponent implements OnInit {

  musicians: Musician[];
  isLoading: boolean;

  constructor(
    private musicianService: MusicianService,
    private managerService: ManagerService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService) { }

  ngOnInit(): void {
    this.getMusicians();
  }

  private getMusicians(): void {
    this.isLoading = true;
    this.musicianService.getAllMusicians().subscribe(
      (musicians) => {
        this.musicians = musicians;
        this.isLoading = false;
      }, error => this.isLoading = false
    );
  }

  invite(index: number): void {
    this.isLoading = true;
    this.managerService.getManagerById(this.sessionService.getUserId())
      .pipe(
        exhaustMap(
          (manager) => {
            if (manager.bandId) {
              return this.managerService.sendOffer(this.musicians[index].id, manager.bandId);
            } else {
              this.alertMessageService.display('You are not managing any band currently');
              this.isLoading = false;
              return EMPTY;
            }
          }
        )
      ).subscribe(
        () => {
          this.alertMessageService.display('Your offer was send');
          this.ngOnInit();
        }
    );
  }
}
