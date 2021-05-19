import {Concert} from '../../model/concert';
import {ConcertDTO} from '../dtos/concert-dto';

export class ConcertMapper {
  static fromDTO(dto: ConcertDTO): Concert {
    const concert = new Concert();
    concert.id = dto.id;
    concert.name = dto.name;
    concert.date = dto.date;
    return concert;
  }

  static fromDTOs(dtos: ConcertDTO[]): Concert[] {
    return dtos.map((dto) => ConcertMapper.fromDTO(dto));
  }

  static toDTO(concert: Concert): ConcertDTO {
    const result = new ConcertDTO();
    result.id = concert.id;
    result.name = concert.name;
    result.date = concert.date;
    return result;
  }

  static toDTOs(concerts: Concert[]): ConcertDTO[] {
    return concerts.map((concert) => ConcertMapper.toDTO(concert));
  }
}
