import {AlbumDTO} from '../dtos/album-dto';
import {Album} from '../../model/album';

export class AlbumMapper {
  static fromDTO(dto: AlbumDTO): Album {
    const album = new Album();
    album.id = dto.id;
    album.name = dto.name;
    album.songs = dto.songs;
    return album;
  }

  static fromDTOs(dtos: AlbumDTO[]): Album[] {
    return dtos.map((dto) => AlbumMapper.fromDTO(dto));
  }

  static toDTO(album: Album): AlbumDTO {
    console.log(album)
    const result = new AlbumDTO();
    result.id = album.id;
    result.name = album.name;
    result.songs = album.songs;
    return result;
  }

  static toDTOs(albums: Album[]): AlbumDTO[] {
    return albums.map((album) => AlbumMapper.toDTO(album));
  }
}
