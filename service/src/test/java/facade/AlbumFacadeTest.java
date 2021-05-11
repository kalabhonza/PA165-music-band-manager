package facade;

import cz.fi.muni.pa165.api.dto.album.AlbumCreateDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumDTO;
import cz.fi.muni.pa165.api.dto.album.AlbumUpdateDTO;
import cz.fi.muni.pa165.api.facade.AlbumFacade;
import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.service.service.album.AlbumService;
import cz.fi.muni.pa165.service.facade.AlbumFacadeImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.AlbumMapperImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

/**
 * @author Aleš Paroulek
 */
public class AlbumFacadeTest {
    private AlbumFacade albumFacade;

    private Album albumA;
    private Album albumB;
    private List<Album> albums;

    private AlbumDTO albumAdto;
    private AlbumDTO albumBdto;
    private List<AlbumDTO> albumDTOs;

    private AlbumCreateDTO albumCreateDTO;
    private AlbumUpdateDTO albumUpdateDTO;

    @Mock
    private AlbumService albumService;

    @Mock
    private AlbumMapperImpl albumMapper;

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        albumFacade = new AlbumFacadeImpl(albumService, albumMapper);

        albumA = new Album();
        albumA.setId(1L);
        albumA.setName("A");

        albumB = new Album();
        albumB.setId(2L);
        albumB.setName("B");

        albums = new ArrayList<>();
        albums.add(albumA);
        albums.add(albumB);

        albumAdto = new AlbumDTO();
        albumAdto.setId(albumA.getId());
        albumAdto.setName(albumA.getName());

        albumBdto = new AlbumDTO();
        albumBdto.setId(albumB.getId());
        albumBdto.setName(albumB.getName());

        albumDTOs = new ArrayList<AlbumDTO>();
        albumDTOs.add(albumAdto);
        albumDTOs.add(albumBdto);

        albumCreateDTO = new AlbumCreateDTO();
        albumCreateDTO.setName("A");

        albumUpdateDTO = new AlbumUpdateDTO();
        albumUpdateDTO.setId(1L);
        albumUpdateDTO.setName("A");
    }

    @Test
    public void createSong() {
        given(albumMapper.mapToEntity(albumCreateDTO)).willReturn(albumA);
        given(albumService.create(albumA)).willReturn(albumA.getId());
        Long id = albumFacade.create(albumCreateDTO);
        then(albumService).should().create(albumA);

        given(albumService.findById(albumA.getId())).willReturn(albumA);
        given(albumMapper.mapToAlbumDTO(albumA)).willReturn(albumAdto);
        AlbumDTO storedA = albumFacade.findById(id);

        assertEquals(storedA, albumAdto);
    }

    @Test
    public void updateSong() {
        given(albumMapper.mapToEntity(albumUpdateDTO)).willReturn(albumA);
        given(albumService.update(albumA)).willReturn(albumA);
        given(albumMapper.mapToAlbumDTO(albumA)).willReturn(albumAdto);

        albumA.setName("Z");
        albumAdto.setName("Z");

        AlbumDTO updated = albumFacade.update(albumUpdateDTO);
        then(albumService.update(albumA));
        assertEquals(albumA.getName(), updated.getName());
    }

    @Test
    public void findSongById() {
        given(albumService.findById(albumA.getId())).willReturn(albumA);
        given(albumMapper.mapToAlbumDTO(albumA)).willReturn(albumAdto);
        AlbumDTO album = albumFacade.findById(albumA.getId());
        assertEquals(album, albumAdto);
        then(albumService).should().findById(albumA.getId());
    }

    @Test
    public void findSongByName() {
        given(albumService.findAlbumsByName(albumA.getName())).willReturn(albums);
        given(albumMapper.mapToListDTO(albums)).willReturn(albumDTOs);
        List<AlbumDTO> result = albumFacade.findAlbumsByName(albumA.getName());
        assertEquals(albumDTOs, result);
        then(albumService).should().findAlbumsByName(albumA.getName());
    }

    @Test
    public void findAllSongs() {
        given(albumService.findAllAlbums()).willReturn(albums);
        given(albumMapper.mapToListDTO(albums)).willReturn(albumDTOs);
        List<AlbumDTO> result = albumFacade.findAllAlbums();
        assertEquals(albumDTOs, result);
        then(albumService).should().findAllAlbums();
    }

    @Test
    public void deleteSong() {
        given(albumMapper.mapToEntity(albumAdto)).willReturn(albumA);
        albumFacade.remove(albumAdto);
        then(albumService).should().remove(albumA);
    }

}
