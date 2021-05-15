import {ManagerDTO} from '../dtos/manager-dto';
import {Manager} from '../../model/manager';

export class ManagerMapper {
  static fromDTO(dto: ManagerDTO): Manager {
    const manager = new Manager();
    manager.name = dto.name;
    return manager;
  }
}
