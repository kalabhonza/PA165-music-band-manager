import {Component, OnInit} from '@angular/core';
import {BandService} from '../../services/band.service';
import {Band} from '../../../model/band';
import {ManagerService} from '../../services/manager.service';
import {SessionService} from '../../../shared/services/session.service';
import {exhaustMap} from 'rxjs/operators';
import {EMPTY} from 'rxjs';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {AbstractControl, FormArray, FormControl, Validators} from '@angular/forms';
import {Song} from '../../../model/song';
import {Album} from '../../../model/album';
import {Concert} from '../../../model/concert';
import {Tour} from '../../../model/tour';
import {BandManageFormGroup} from './band-manage-form-group';

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
  modified: boolean;
  bandManageFormGroup: BandManageFormGroup;

  constructor(
    private bandsService: BandService,
    private managerService: ManagerService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService
  ) { }

  get bandName(): AbstractControl {
    return this.bandManageFormGroup.formGroup.get('bandName') as AbstractControl;
  }

  get bandStyle(): AbstractControl {
    return this.bandManageFormGroup.formGroup.get('bandStyle') as AbstractControl;
  }

  get albums(): FormArray {
    return this.bandManageFormGroup.formGroup.get('albums') as FormArray;
  }

  getSongs(index: number): FormArray {
    return this.albums.at(index).get('songs') as FormArray;
  }

  get tours(): FormArray {
    return this.bandManageFormGroup.formGroup.get('tours') as FormArray;
  }

  getConcerts(index: number): FormArray {
    return this.tours.at(index).get('concerts') as FormArray;
  }

  ngOnInit(): void {
    this.modified = false;
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
            if (manager.bandId !== undefined) {
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
        this.bandManageFormGroup = new BandManageFormGroup(this.band);
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
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  removeAlbum(albumIndex: number): void {
    this.band.albums.splice(albumIndex, 1);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  removeTour(tourIndex: number): void {
    this.band.tours.splice(tourIndex, 1);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  removeConcert(tourIndex: number, concertIndex: number): void {
    this.band.tours[tourIndex].concerts.splice(concertIndex, 1);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  addConcert(tourIndex: number): void {
    const concert = new Concert();
    concert.name = 'Name';
    this.band.tours[tourIndex].concerts.push(concert);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  addTour(): void {
    const tour = new Tour();
    tour.name = 'Name';
    tour.concerts = [];
    this.band.tours.push(tour);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  addSong(albumIndex: number): void {
    const song = new Song();
    song.duration = '00:00:00';
    song.name = 'Song name';
    this.band.albums[albumIndex].songs.push(song);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  addAlbum(): void {
    const album = new Album();
    album.name = 'Name';
    album.songs = [];
    this.band.albums.push(album);
    this.updateForm();
    this.bandManageFormGroup.formGroup.markAsDirty();
  }

  dateChange(tourIndex: number, concertIndex: number, event: any): void {
    this.band.tours[tourIndex].concerts[concertIndex].date = event.value;
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

  save(): void {
    this.bandManageFormGroup.setToBand(this.band);

    this.bandsService.updateBand((this.band)).subscribe(
      (_) => {
        // this.band = band;
        // this.bandManageFormGroup = new BandManageFormGroup(this.band);
        // this.setBandForms();
        this.bandManageFormGroup.formGroup.markAsPristine();
        this.isLoading = false;
      },
      () =>  this.isLoading = false
    );
  }

  private updateForm(): void {
    this.bandManageFormGroup = new BandManageFormGroup(this.band);
    this.bandManageFormGroup.formGroup.valueChanges.subscribe(() => {
      this.bandManageFormGroup.setToBand(this.band);
    });
  }
}
