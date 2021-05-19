import {Musician} from './musician';
import {Album} from './album';
import {Tour} from './tour';
import {Manager} from './manager';
import {Style} from './style';

export class Band {
  id: number;
  name: string;
  logo?: string;
  style: Style;
  members: Musician[];
  albums: Album[];
  tours: Tour[];
  manager: Manager;
}
