package cz.fi.muni.pa165.rest.assemblers;

import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.rest.controllers.AlbumController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AlbumResourceAssembler implements RepresentationModelAssembler<AlbumDTO, EntityModel<AlbumDTO>> {
    @Override
    public EntityModel<AlbumDTO> toModel(AlbumDTO albumDTO) {
        EntityModel<AlbumDTO> albumResource = new EntityModel<>(albumDTO);
        try {
            albumResource.add(linkTo(AlbumController.class).slash(albumDTO.getId()).withSelfRel().withType("GET"));
        } catch (Exception ex){

        }
        return albumResource;
    }
}
