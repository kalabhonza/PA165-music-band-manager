import {Band} from './band';

export class Musician {
  id: number;
  name: string;
  username: string;
  password: string;
  offers: Band[];
  band?: number;
}
