import {TourDTO} from '../dtos/tour-dto';
import {Tour} from '../../model/tour';

export class TourMapper {
  static fromDTO(dto: TourDTO): Tour {
    const tour = new Tour();
    tour.id = dto.id;
    tour.name = dto.name;
    tour.concerts = dto.concerts;
    return tour;
  }

  static fromDTOs(dtos: TourDTO[]): Tour[] {
    return dtos.map((dto) => TourMapper.fromDTO(dto));
  }

  static toDTO(tour: Tour): TourDTO {
    const result = new TourDTO();
    result.id = tour.id;
    result.name = tour.name;
    result.concerts = tour.concerts;
    return result;
  }

  static toDTOs(tours: Tour[]): TourDTO[] {
    return tours.map((tour) => TourMapper.toDTO(tour));
  }
}
