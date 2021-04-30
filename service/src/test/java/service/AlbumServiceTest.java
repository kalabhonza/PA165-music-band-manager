package service;

import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.AlbumDAO;
import cz.fi.muni.pa165.service.AlbumService;
import cz.fi.muni.pa165.service.AlbumServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;

import java.sql.Time;
import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * @author Aleš Paroulek
 */
public class AlbumServiceTest {
    private AlbumService albumService;
    private Song songA;
    private Song songB;
    private Album albumA;
    private Album albumB;
    private List<Album> albums;

    @Mock
    private AlbumDAO albumDAO;

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        albumService = new AlbumServiceImpl(albumDAO);

        songA = new Song();
        songA.setName("A");
        songA.setDuration(new Time(60));

        songB = new Song();
        songB.setName("B");
        songB.setDuration(new Time(60));

        albumA = new Album();
        albumA.setName("A");
        albumA.setSongs(new HashSet<>(Arrays.asList(songA)));

        albumB = new Album();
        albumB.setName("B");
        albumB.setSongs(new HashSet<>(Arrays.asList(songB)));

        albums = new ArrayList<>();
        albums.add(albumA);
        albums.add(albumB);
    }

    @Test
    public void create() {
        given(albumDAO.create(albumA)).willReturn(1L);
        Long idA = albumService.create(albumA);

        given(albumDAO.findById(idA)).willReturn(albumA);
        Album storedA = albumService.findById(idA);

        assertEquals(albumA, storedA);
    }

    @Test
    public void updateName() {
        albumA.setName("Z");
        given(albumDAO.update(albumA)).willReturn(albumA);
        Album updatedA = albumService.update(albumA);
        assertEquals(albumA, updatedA);
    }

    @Test
    public void updateSongs() {
        albumA.setSongs(new HashSet<>(Arrays.asList(songA, songB)));
        given(albumDAO.update(albumA)).willReturn(albumA);
        Album updatedA = albumService.update(albumA);
        assertEquals(albumA, updatedA);
    }

    @Test
    public void delete() {
        given(albumDAO.findById(albumA.getId())).willReturn(null);
        albumService.remove(albumA);
        then(albumDAO).should().remove(albumA);
    }

    @Test
    public void findById() {
        given(albumDAO.findById(albumA.getId())).willReturn(albumA);
        Album album = albumService.findById(albumA.getId());
        assertEquals(album.getName(), albumA.getName());
        assertEquals(album.getSongs(), albumA.getSongs());
        then(albumDAO).should().findById(albumA.getId());
    }

    @Test
    public void findByName() {
        given(albumDAO.findAlbumsByName(albumA.getName())).willReturn(albums);
        List<Album> albumsABC = albumService.findAlbumsByName(albumA.getName());
        assertEquals(albums, albumsABC);
        then(albumDAO).should().findAlbumsByName(albumA.getName());
    }

    @Test
    public void findAll() {
        given(albumDAO.findAllAlbums()).willReturn(albums);
        List<Album> result = albumService.findAllAlbums();
        assertEquals(albums, result);
        then(albumDAO).should().findAllAlbums();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExistingAlbum() {
        given(albumDAO.update(albumA)).willReturn(null);//.findById(albumA.getId())).willReturn(null);
        albumService.update(albumA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void FailedDelete() {
        given(albumDAO.findById(albumA.getId())).willReturn(albumA);
        albumService.remove(albumA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        given(albumDAO.findById(666L)).willReturn(null);
        albumService.findById(666L);
    }

    @Test
    public void findByNonExistingName() {
        List<Album> emptyList = new ArrayList<>();
        given(albumDAO.findAlbumsByName("UEEE")).willReturn(emptyList);
        List<Album> result = albumService.findAlbumsByName("UEEE");
        assertEquals(result, emptyList);
    }


}
