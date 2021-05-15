import {TourDTO} from '../dtos/tour-dto';
import {Tour} from '../../model/tour';

export class TourMapper {
  static fromDTO(dto: TourDTO): Tour {
    const tour = new Tour();
    tour.name = dto.name;
    return tour;
  }

  static fromDTOs(dtos: TourDTO[]): Tour[] {
    return dtos.map((dto) => TourMapper.fromDTO(dto));
  }

  static toDTO(tour: Tour): TourDTO {
    const result = new TourDTO();
    result.name = tour.name;
    return result;
  }

  static toDTOs(tours: Tour[]): TourDTO[] {
    return tours.map((tour) => TourMapper.toDTO(tour));
  }
}
