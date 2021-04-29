package cz.fi.muni.pa165.service.mapping.mapstruct;

import cz.fi.muni.pa165.api.dto.ManagerCreateDTO;
import cz.fi.muni.pa165.api.dto.ManagerDTO;
import cz.fi.muni.pa165.api.dto.ManagerUpdateDTO;
import cz.fi.muni.pa165.entities.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper used for mapping of entities to DTO
 *
 * @author Igor Ignác
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ManagerMapper {
    ManagerDTO mapToManagerDTO(Manager entity);

    Manager mapToEntity(ManagerDTO dto);

    ManagerCreateDTO mapToManagerCreateDTO(Manager entity);

    Manager mapToEntity(ManagerCreateDTO dto);

    ManagerUpdateDTO mapToManagerUpdateDTO(Manager entity);

    Manager mapToEntity(ManagerUpdateDTO dto);

    List<ManagerDTO> mapToListDTO(List<Manager> dto);

}
