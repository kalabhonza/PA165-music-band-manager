import {Musician} from '../../model/musician';
import {MusicianDTO} from '../dtos/musician-dto';
import {BandDTO} from '../dtos/band-dto';
import {Band} from '../../model/band';
import {AlbumMapper} from './album-mapper';
import {ManagerMapper} from './manager-mapper';
import {TourMapper} from './tour-mapper';

export class MusicianMapper {
  static fromDTO(dto: MusicianDTO): Musician {
    const musician = new Musician();
    musician.id = dto.id;
    musician.name = dto.name;
    musician.username = dto.username;
    musician.password = dto.password;
    musician.offers = MusicianMapper.fromBandDTOs(dto.offers);
    musician.instruments = dto.instruments;
    musician.band = dto.band;
    return musician;
  }

  static fromDTOs(dtos: MusicianDTO[]): Musician[] {
    return dtos.map((dto) => MusicianMapper.fromDTO(dto));
  }

  static toDTO(musician: Musician): MusicianDTO {
    const result = new MusicianDTO();
    result.id = musician.id;
    result.name = musician.name;
    result.username = musician.username;
    result.instruments = musician.instruments;
    result.password = musician.name;
    result.offers = MusicianMapper.toBandDTOs(musician.offers);
    result.band = musician.band;
    return result;
  }

  static toDTOs(musicians: Musician[]): MusicianDTO[] {
    return musicians.map((musician) => MusicianMapper.toDTO(musician));
  }

  static fromBandDTO(dto: BandDTO): Band {
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

  static fromBandDTOs(dtos: BandDTO[]): Band[] {
    return dtos.map((dto) => MusicianMapper.fromBandDTO(dto));
  }

  static toBandDTO(band: Band): BandDTO {
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

  static toBandDTOs(bands: Band[]): BandDTO[] {
    return bands.map((band) => MusicianMapper.toBandDTO(band));
  }
}
