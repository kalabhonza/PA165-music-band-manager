import {Component, OnDestroy, OnInit} from '@angular/core';
import {MusicianService} from '../../services/musician.service';
import {Musician} from '../../../model/musician';
import {ManagerService} from '../../services/manager.service';
import {exhaustMap, takeUntil} from 'rxjs/operators';
import {EMPTY, ReplaySubject} from 'rxjs';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-musicians',
  templateUrl: './musicians.component.html',
  styleUrls: ['./musicians.component.css']
})
export class MusiciansComponent implements OnInit, OnDestroy {

  musicians: Musician[];
  isLoading: boolean;

  private destroyed$: ReplaySubject<boolean> = new ReplaySubject(1);

  constructor(
    private musicianService: MusicianService,
    private managerService: ManagerService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService) { }

  ngOnInit(): void {
    this.getMusicians();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }

  private getMusicians(): void {
    this.isLoading = true;
    this.musicianService.getAllMusicians().pipe(takeUntil(this.destroyed$)).subscribe(
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
        ),
        takeUntil(this.destroyed$)
      ).subscribe(
        () => {
          this.alertMessageService.display('Your offer was send');
          this.ngOnInit();
        }
    );
  }
}
