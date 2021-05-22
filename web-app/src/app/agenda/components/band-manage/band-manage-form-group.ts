import {Band} from '../../../model/band';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {Album} from '../../../model/album';
import {Tour} from '../../../model/tour';
import {Song} from '../../../model/song';
import {Concert} from '../../../model/concert';

export class BandManageFormGroup {
  formGroup: FormGroup;

  constructor(band: Band) {
    this.formGroup = new FormGroup({
      bandName: new FormControl(band.name, Validators.required),
      bandStyle: new FormControl(band.style, Validators.required),
      albums: new FormArray(
        band.albums.map((album) => BandManageFormGroup.createAlbum(album))
      ),
      tours: new FormArray(
        band.tours.map((tour) => BandManageFormGroup.createTour(tour))
      ),
    });
  }

  private static createAlbum(album: Album): FormGroup {
    return new FormGroup({
      name: new FormControl(album.name, Validators.required),
      songs: new FormArray(
        album.songs.map((song) => BandManageFormGroup.createSong(song))
      ),
    });
  }

  private static createSong(song: Song): FormGroup {
    return new FormGroup({
      name: new FormControl(song.name, Validators.required),
      duration: new FormControl(song.duration),
    });
  }

  private static createTour(tour: Tour): FormGroup {
    return new FormGroup({
      name: new FormControl(tour.name, Validators.required),
      concerts: new FormArray(
        tour.concerts.map((concert) => BandManageFormGroup.createConcert(concert))
      ),
    });
  }

  private static createConcert(concert: Concert): FormGroup {
    return new FormGroup({
      name: new FormControl(concert.name, Validators.required),
      date: new FormControl(concert.date),
    });
  }

  setToBand(band: Band): void {
    band.name = this.formGroup.get('bandName')?.value;
    band.style = this.formGroup.get('bandStyle')?.value;
    band.albums = this.formGroup.get('albums')?.value;
    band.tours = this.formGroup.get('tours')?.value;
  }
}
