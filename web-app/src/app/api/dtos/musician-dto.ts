import {BandDTO} from './band-dto';

export class MusicianDTO {
  id: number;
  name: string;
  username: string;
  password: string;
  // TO DO: instruments
  offers: BandDTO[];
  band?: number;
}
