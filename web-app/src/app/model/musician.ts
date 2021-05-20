import {Band} from './band';

export class Musician {
  id: number;
  name: string;
  username: string;
  password: string;
  // TO DO: instruments
  offers: Band[];
  band?: number;
}
