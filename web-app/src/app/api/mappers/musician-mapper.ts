import {Musician} from '../../model/musician';
import {MusicianDTO} from '../dtos/musician-dto';

export class MusicianMapper {
  static fromDTO(dto: MusicianDTO): Musician {
    const musician = new Musician();
    musician.name = dto.name;
    return musician;
  }

  static fromDTOs(dtos: MusicianDTO[]): Musician[] {
    return dtos.map((dto) => MusicianMapper.fromDTO(dto));
  }

  static toDTO(musician: Musician): MusicianDTO {
    const result = new MusicianDTO();
    result.name = musician.name;
    return result;
  }

  static toDTOs(musicians: Musician[]): MusicianDTO[] {
    return musicians.map((musician) => MusicianMapper.toDTO(musician));
  }
}
