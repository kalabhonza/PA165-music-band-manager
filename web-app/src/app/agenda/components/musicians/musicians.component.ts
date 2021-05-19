import { Component, OnInit } from '@angular/core';
import {MusicianService} from '../../services/musician.service';
import {Musician} from '../../../model/musician';

@Component({
  selector: 'app-musicians',
  templateUrl: './musicians.component.html',
  styleUrls: ['./musicians.component.css']
})
export class MusiciansComponent implements OnInit {

  musicians: Musician[];

  constructor(private musicianService: MusicianService) { }

  ngOnInit(): void {
    this.getMusicians();
  }

  private getMusicians(): void {
    this.musicianService.getAllMusicians().subscribe(
      (musicians) => this.musicians = musicians
    );
  }

}
