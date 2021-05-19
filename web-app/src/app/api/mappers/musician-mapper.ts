import {Musician} from '../../model/musician';
import {MusicianDTO} from '../dtos/musician-dto';

export class MusicianMapper {
  static fromDTO(dto: MusicianDTO): Musician {
    const musician = new Musician();
    musician.id = dto.id;
    musician.name = dto.name;
    musician.username = dto.username;
    musician.password = dto.password;
    musician.offers = dto.offers;
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
    result.password = musician.name;
    result.offers = musician.offers;
    result.band = musician.band;
    return result;
  }

  static toDTOs(musicians: Musician[]): MusicianDTO[] {
    return musicians.map((musician) => MusicianMapper.toDTO(musician));
  }
}
