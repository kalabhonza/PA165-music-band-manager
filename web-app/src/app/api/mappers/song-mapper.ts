import {Song} from '../../model/song';
import {SongDTO} from '../dtos/song-dto';

export class SongMapper {
  static fromDTO(dto: SongDTO): Song {
    const song = new Song();
    song.id = dto.id;
    song.name = dto.name;
    song.duration = dto.duration;
    return song;
  }

  static fromDTOs(dtos: SongDTO[]): Song[] {
    return dtos.map((dto) => SongMapper.fromDTO(dto));
  }

  static toDTO(song: Song): SongDTO {
    const result = new SongDTO();
    result.id = song.id;
    result.name = song.name;
    result.duration = song.duration;
    return result;
  }

  static toDTOs(songs: Song[]): SongDTO[] {
    return songs.map((song) => SongMapper.toDTO(song));
  }
}
