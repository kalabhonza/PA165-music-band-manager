package cz.fi.muni.pa165.rest.assemblers;


import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.rest.controllers.SongController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class SongResourceAssembler implements RepresentationModelAssembler<SongDTO, EntityModel<SongDTO>> {
    @Override
    public EntityModel<SongDTO> toModel(SongDTO songDTO) {
        EntityModel<SongDTO> songResource = new EntityModel<>(songDTO);
        try {
            songResource.add(linkTo(SongController.class).slash(songDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return songResource;
    }
}
