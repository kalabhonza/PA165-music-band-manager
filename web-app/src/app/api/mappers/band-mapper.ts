import {BandDTO} from '../dtos/band-dto';
import {AlbumMapper} from './album-mapper';
import {ManagerMapper} from './manager-mapper';
import {MusicianMapper} from './musician-mapper';
import {Band} from '../../model/band';
import {TourMapper} from './tour-mapper';

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
    band.tours = TourMapper.fromDTOs(dto.tours);
    return band;
  }

  static fromDTOs(dtos: BandDTO[]): Band[] {
    return dtos.map((dto) => BandMapper.fromDTO(dto));
  }

  static toDTO(band: Band): BandDTO {
    const result = new BandDTO();
    result.id = band.id;
    result.name = band.name;
    result.logo = band.logo;
    result.style = band.style;
    result.tours = TourMapper.toDTOs(band.tours);
    result.manager = ManagerMapper.toDTO(band.manager);
    result.members = MusicianMapper.toDTOs(band.members);
    result.albums = AlbumMapper.toDTOs(band.albums);
    return result;
  }

  static toDTOs(bands: Band[]): BandDTO[] {
    return bands.map((band) => BandMapper.toDTO(band));
  }
}
