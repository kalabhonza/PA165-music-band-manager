package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.manager.ManagerDTO;
import cz.fi.muni.pa165.rest.controllers.ManagerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Igor Ignác
 */
@Component
public class ManagerResourceAssembler implements RepresentationModelAssembler<ManagerDTO, EntityModel<ManagerDTO>> {
    @Override
    public EntityModel<ManagerDTO> toModel(ManagerDTO managerDTO) {
        EntityModel<ManagerDTO> managerResource = new EntityModel<>(managerDTO);
        try {
            managerResource.add(linkTo(ManagerController.class).slash(managerDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return managerResource;
    }
}