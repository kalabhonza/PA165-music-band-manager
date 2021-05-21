import {BandDTO} from './band-dto';

export class MusicianDTO {
  id: number;
  name: string;
  username: string;
  password: string;
  offers: BandDTO[];
  band?: number;
}
