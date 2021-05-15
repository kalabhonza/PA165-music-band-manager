package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.rest.controllers.BandController;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; //trochu jiny import?

@Component
public class BandResourceAssembler implements ResourceAssembler<BandDTO, Resource<BandDTO>> {
    @Override
    public Resource<BandDTO> toResource(BandDTO bandDTO) {
        Resource<BandDTO> bandResource = new Resource<>(bandDTO);
        try {
            bandResource.add(linkTo(BandController.class).slash(bandDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return bandResource;
    }
}
