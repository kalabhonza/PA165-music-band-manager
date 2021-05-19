import {SongDTO} from './song-dto';

export class AlbumDTO {
  id: number;
  name: string;
  songs: SongDTO[];
}
