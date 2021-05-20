import {ManagerDTO} from '../dtos/manager-dto';
import {Manager} from '../../model/manager';
import {MusicianDTO} from '../dtos/musician-dto';
import {BandMapper} from './band-mapper';


export class ManagerMapper {
  static fromDTO(dto: ManagerDTO): Manager {
    const manager = new Manager();
    manager.id = dto.id;
    manager.name = dto.name;
    manager.username = dto.username;
    manager.password = dto.password;
    manager.bandId = dto.band;
    return manager;
  }

  static fromDTOs(dtos: ManagerDTO[]): Manager[] {
    return dtos.map((dto) => ManagerMapper.fromDTO(dto));
  }

  static toDTO(manager: Manager): ManagerDTO {
    const result = new ManagerDTO();
    result.id = manager.id;
    result.name = manager.name;
    result.username = manager.username;
    result.password = manager.password;
    result.band = manager.bandId;
    return result;
  }

  static toDTOs(managers: Manager[]): ManagerDTO[] {
    return managers.map((manager) => ManagerMapper.toDTO(manager));
  }

  }
