import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Band} from '../../../model/band';

export class CreateBandFormGroup {
  formGroup: FormGroup;

  constructor(band: Band) {
    this.formGroup = new FormGroup({
      bandName: new FormControl(band.name, Validators.required),
      bandStyle: new FormControl(band.style, Validators.required),
    });
  }

  setToBand(band: Band): void {
    band.name = this.formGroup.get('bandName')?.value;
    band.style = this.formGroup.get('bandStyle')?.value;
    band.albums = [];
    band.tours = [];
  }
}
