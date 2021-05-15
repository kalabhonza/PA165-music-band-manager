package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.musician.MusicianDTO;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Aleš Paroulek
 */
@Component
public class MusicianResourceAssembler implements ResourceAssembler<MusicianDTO, Resource<MusicianDTO>>{
    @Override
    public Resource<MusicianDTO> toResource(MusicianDTO musicianDTO) {
        Resource<MusicianDTO> musicianResource = new Resource<>(musicianDTO);
        try {
            musicianResource.add(linkTo(MusicianController.class).slash(musicianDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return musicianResource;
    }
}
