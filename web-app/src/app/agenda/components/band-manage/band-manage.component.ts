import { Component, OnInit } from '@angular/core';
import {BandService} from '../../services/band.service';
import {Band} from '../../../model/band';
import {ManagerService} from '../../services/manager.service';
import {SessionService} from '../../../shared/services/session.service';
import {exhaustMap} from 'rxjs/operators';
import {EMPTY} from 'rxjs';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {FormControl, Validators} from '@angular/forms';
import {Song} from '../../../model/song';
import {Album} from '../../../model/album';
import {A} from '@angular/cdk/keycodes';

@Component({
  selector: 'app-bands',
  templateUrl: './band-manage.component.html',
  styleUrls: ['./band-manage.component.css']
})
export class BandManageComponent implements OnInit {

  isLoading: boolean;
  managerId: number;
  band: Band;
  availableStyles: string[];
  managerName: string;

  bandName = new FormControl(
    '',
    [Validators.required]);

  albumName = new FormControl(
    '',
    [Validators.required]);

  constructor(
    private bandsService: BandService,
    private managerService: ManagerService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService
  ) { }

  ngOnInit(): void {
    this.managerId = this.sessionService.getUserId();
    this.availableStyles = ['ROCK', 'ALTERNATIVE', 'POP', 'METAL', 'JAZZ', 'CLASSIC'];
    this.loadBand();
  }

  /**
   *
   * @param event
   */
  selected(event: string): void {
    console.log(event);
  }

  loadBand(): void {
    this.isLoading = true;
    this.managerService.getManagerById(this.managerId)
      .pipe(
        exhaustMap(
          (manager) => {
            if (manager.bandId) {
              this.managerName = manager.name;
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
        this.setBandForms();
        this.isLoading = false;
      },
      () =>  this.isLoading = false
    );
  }

  setBandForms(): void {
    this.bandName.setValue(this.band.name);
  }

  removeSong(bandIndex: number, songIndex: number): void {
    this.band.albums[bandIndex].songs.splice(songIndex, 1);
  }

  removeAlbum(albumIndex: number): void {
    this.band.albums.splice(albumIndex, 1);
  }

  addSong(albumIndex: number): void {
    const song = new Song();
    song.duration = '00:00:00';
    song.name = 'Song name';
    this.band.albums[albumIndex].songs.push(song);
  }

  addAlbum(): void {
    const album = new Album();
    album.name = 'Name';
    this.band.albums.push(album);
  }

  onSelectFile(imgEvent: any): void {
    if (imgEvent.target.files && imgEvent.target.files[0]) {
      const reader = new FileReader();

      reader.onload = (event: any) => {
        this.band.logo = event.target.result;
      };
      reader.readAsDataURL(imgEvent.target.files[0]);
    }
  }
}
