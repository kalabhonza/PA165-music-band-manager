import {BandDTO} from '../dtos/band-dto';
import {AlbumMapper} from './album-mapper';
import {ManagerMapper} from './manager-mapper';
import {MusicianMapper} from './musician-mapper';
import {Band} from '../../model/band';

export class BandMapper {
  static fromDTO(dto: BandDTO): Band {
    const band = new Band();
    band.id = dto.id;
    band.name = dto.name;
    band.logo = dto.logo;
    band.style = dto.style;
    band.albums = AlbumMapper.fromDTOs(dto.albums);
    band.manager = ManagerMapper.fromDTO(dto.manager);
    band.members = MusicianMapper.fromDTOs(dto.members);
    band.tours = MusicianMapper.fromDTOs(dto.tours);
    return band;
  }

  static fromDTOs(dtos: BandDTO[]): Band[] {
    return dtos.map((dto) => BandMapper.fromDTO(dto));
  }
}
