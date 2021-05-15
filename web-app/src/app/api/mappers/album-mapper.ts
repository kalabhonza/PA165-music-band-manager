import {AlbumDTO} from '../dtos/album-dto';
import {Album} from '../../model/album';

export class AlbumMapper {
  static fromDTO(dto: AlbumDTO): Album {
    const album = new Album();
    album.name = dto.name;
    return album;
  }

  static fromDTOs(dtos: AlbumDTO[]): Album[] {
    return dtos.map((dto) => AlbumMapper.fromDTO(dto));
  }
}
