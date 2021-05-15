import { Component, OnInit } from '@angular/core';
import {BandService} from '../../services/band.service';
import {Observable} from 'rxjs';
import {Band} from '../../../model/band';

@Component({
  selector: 'app-bands',
  templateUrl: './bands.component.html',
  styleUrls: ['./bands.component.css']
})
export class BandsComponent implements OnInit {

  isLoading: boolean;
  bands: Band[];

  constructor(private bandsService: BandService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.bandsService.getAllBands().subscribe(
      bands => {
        this.bands = bands;
        this.isLoading = false;
      },
      error => this.isLoading = false
    );
  }
}
