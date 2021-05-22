import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Band} from '../../../model/band';
import {Manager} from '../../../model/manager';

export class CreateBandFormGroup {
  formGroup: FormGroup;

  constructor(band: Band) {
    this.formGroup = new FormGroup({
      name: new FormControl(band.name, Validators.required),
      bandStyle: new FormControl(band.style, Validators.required),
    });
  }

  setToBand(band: Band, manager: Manager): void {
    band.name = this.formGroup.get('bandName')?.value;
    band.style = this.formGroup.get('bandStyle')?.value;
    band.manager = manager;
    band.members = [];
    band.albums = [];
    band.tours = [];
  }
}
