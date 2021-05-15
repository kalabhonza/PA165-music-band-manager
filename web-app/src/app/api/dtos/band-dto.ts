import {Style} from '../../model/style';
import {MusicianDTO} from './musician-dto';
import {AlbumDTO} from './album-dto';
import {TourDTO} from './tour-dto';
import {ManagerDTO} from './manager-dto';

export class BandDTO {
  id: number;
  name: string;
  logo: string;
  style: Style;
  members: MusicianDTO[];
  albums: AlbumDTO[];
  tours: TourDTO[];
  manager: ManagerDTO;
}
