import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {BandService} from '../../services/band.service';
import {AbstractControl} from '@angular/forms';
import {Band} from '../../../model/band';
import {CreateBandFormGroup} from './create-band-form-group';
import {Manager} from '../../../model/manager';
import {ManagerService} from '../../services/manager.service';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@Component({
  selector: 'app-create-band',
  templateUrl: './create-band.component.html',
  styleUrls: ['./create-band.component.css']
})
export class CreateBandComponent implements OnInit {

  isLoading: boolean;
  createBandFormGroup: CreateBandFormGroup;
  availableStyles: string[];
  manager: Manager;
  band: Band;

  constructor(
    private bandService: BandService,
    private managerService: ManagerService,
    private sessionService: SessionService,
    private alertService: AlertMessageService
  ) {
    this.band = new Band();
    this.createBandFormGroup = new CreateBandFormGroup(this.band);
    this.availableStyles = ['ROCK', 'ALTERNATIVE', 'POP', 'METAL', 'JAZZ', 'CLASSIC'];
  }

  get name(): AbstractControl {
    return this.createBandFormGroup.formGroup.get('name') as AbstractControl;
  }

  get style(): AbstractControl {
    return this.createBandFormGroup.formGroup.get('style') as AbstractControl;
  }

  ngOnInit(): void {
    this.loadManager();
  }

  create(): void {
    this.isLoading = true;
    this.createBandFormGroup.setToBand(this.band, this.manager);
    this.bandService.createBand(this.band).subscribe(
      _ => {
        this.alertService.display('Band was created an registered to you');
        this.createBandFormGroup = new CreateBandFormGroup(new Band());
        this.isLoading = false;
      },
      _ => this.isLoading = false
    );
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

  private loadManager(): void {
    this.isLoading = true;
    this.managerService.getManagerById(this.sessionService.getUserId()).subscribe(
      (user) => {
        this.manager = user;
        this.isLoading = false;
      },
      _ => this.isLoading = false
    );
  }
}
