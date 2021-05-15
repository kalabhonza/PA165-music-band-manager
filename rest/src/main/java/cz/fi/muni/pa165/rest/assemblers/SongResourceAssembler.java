package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.band.BandDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.rest.controllers.BandController;
import cz.fi.muni.pa165.rest.controllers.SongController;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; //trochu jiny import?

@Component
public class SongResourceAssembler implements ResourceAssembler<SongDTO, Resource<SongDTO>> {
    @Override
    public Resource<SongDTO> toResource(SongDTO songDTO) {
        Resource<SongDTO> songResource = new Resource<>(songDTO);
        try {
            songResource.add(linkTo(SongController.class).slash(songDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return songResource;
    }
}
