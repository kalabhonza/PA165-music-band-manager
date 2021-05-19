import {ConcertDTO} from './concert-dto';

export class TourDTO {
  id: number;
  name: string;
  concerts: ConcertDTO[];
}
