package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import cz.fi.muni.pa165.rest.controllers.MusicianController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Aleš Paroulek
 */
@Component
public class MusicianResourceAssembler implements RepresentationModelAssembler<MusicianDTO, EntityModel<MusicianDTO>>{
    @Override
    public EntityModel<MusicianDTO> toModel(MusicianDTO musicianDTO) {
        EntityModel<MusicianDTO> musicianResource = new EntityModel<>(musicianDTO);
        try {
            musicianResource.add(linkTo(MusicianController.class).slash(musicianDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return musicianResource;
    }
}
