package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.rest.controllers.ManagerController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Igor Ignác
 */
@Component
public class ManagerResourceAssembler implements ResourceAssembler<ManagerDTO, Resource<ManagerDTO>> {
    @Override
    public Resource<ManagerDTO> toResource(ManagerDTO managerDTO) {
        Resource<ManagerDTO> managerResource = new Resource<>(managerDTO);
        try {
            managerResource.add(linkTo(ManagerController.class).slash(managerDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return managerResource;
    }
}