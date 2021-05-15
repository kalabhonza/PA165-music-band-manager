import {ManagerDTO} from '../dtos/manager-dto';
import {Manager} from '../../model/manager';
import {Musician} from '../../model/musician';
import {MusicianDTO} from '../dtos/musician-dto';

export class ManagerMapper {
  static fromDTO(dto: ManagerDTO): Manager {
    const manager = new Manager();
    manager.name = dto.name;
    return manager;
  }

  static toDTO(manager: Manager): ManagerDTO {
    const result = new MusicianDTO();
    result.name = manager.name;
    return result;
  }
}
