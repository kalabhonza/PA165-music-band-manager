package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.rest.controllers.AlbumController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class AlbumResourceAssembler implements ResourceAssembler<AlbumDTO, Resource<AlbumDTO>> {
    @Override
    public Resource<AlbumDTO> toResource(AlbumDTO albumDTO) {
        Resource<AlbumDTO> albumResource = new Resource<>(albumDTO);
        try {
            albumResource.add(linkTo(AlbumController.class).slash(albumDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return albumResource;
    }
}
