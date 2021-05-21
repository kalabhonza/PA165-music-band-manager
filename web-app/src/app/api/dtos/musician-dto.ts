import {BandDTO} from './band-dto';

export class MusicianDTO {
  id: number;
  name: string;
  userName: string;
  password: string;
  instruments: string[];
  offers: BandDTO[];
  band?: number;
}
