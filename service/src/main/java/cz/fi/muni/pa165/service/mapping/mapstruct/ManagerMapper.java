package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.entities.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO mapToManagerDTO(Manager entity);

    Manager mapToEntity(ManagerDTO dto);

    ManagerCreateDTO mapToManagerCreateDTO(Manager entity);

    Manager mapToEntity(ManagerCreateDTO dto);

    ManagerUpdateDTO mapToManagerUpdateDTO(Manager entity);

    Manager mapToEntity(ManagerUpdateDTO dto);
}
