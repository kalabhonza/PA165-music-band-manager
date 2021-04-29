package service;

import cz.fi.muni.pa165.entities.Album;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.AlbumDAO;
import cz.fi.muni.pa165.service.AlbumService;
import cz.fi.muni.pa165.service.AlbumServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Time;
import java.util.*;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
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
    private Album albumC;
    private List<Album> albums;


    @Mock
    private AlbumDAO albumDAO;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeTest() {
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
        albumA.setSongs(new HashSet<>(Arrays.asList(songB)));

        albumC = new Album();
        albumC.setName("C");
        albumA.setSongs(new HashSet<>(Arrays.asList(songA, songB)));

        albums = new ArrayList<>();
        albums.add(albumA);
        albums.add(albumB);
        albums.add(albumC);
    }

    @Test
    public void create() {
        when(albumDAO.create(albumA)).thenReturn(1L);
        Long idA = albumService.create(albumA);

        when(albumDAO.findById(idA)).thenReturn(albumA);
        Album storedA = albumService.findById(idA);

        assertEquals(albumA, storedA);
    }

    @Test
    public void updateName() {
        albumA.setName("Z");

        when(albumDAO.update(albumA)).thenReturn(albumA);
        Album updatedA = albumService.update(albumA);

        assertEquals(albumA, updatedA);
    }

    @Test
    public void updateSongs() {
        albumA.setSongs(new HashSet<>(Arrays.asList(songA, songB)));

        when(albumDAO.update(albumA)).thenReturn(albumA);
        Album updatedA = albumService.update(albumA);

        assertEquals(albumA, updatedA);
    }

    @Test
    public void delete() {
        albumService.remove(albumA);
        then(albumDAO).should().remove(albumA);
    }

    @Test
    public void findById() {
        when(albumDAO.findById(albumA.getId())).thenReturn(albumA);

        Album album = albumService.findById(albumA.getId());
        assertEquals(album.getName(), albumA.getName());
        assertEquals(album.getSongs(), albumA.getSongs());

        verify(albumDAO, times(1)).findById(albumA.getId());
    }

    @Test
    public void findByName() {
        when(albumDAO.findAlbumsByName(albumA.getName())).thenReturn(albums);
        List<Album> albumsABC = albumService.findAlbumsByName(albumA.getName());
        assertEquals(albums, albumsABC);
        verify(albumDAO, times(1)).findAlbumsByName(albumA.getName());
    }

    @Test
    public void findAll() {
        when(albumDAO.findAllAlbums()).thenReturn(albums);
        List<Album> result = albumService.findAllAlbums();
        assertEquals(albums, result);
        verify(albumDAO, times(1)).findAllAlbums();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExistingAlbum() {
        when(albumDAO.findById(albumA.getId())).thenReturn(null);
        albumService.update(albumA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void FailedDelete() {
        when(albumDAO.findById(albumA.getId())).thenReturn(albumA);
        albumService.remove(albumA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        when(albumDAO.findById(666L)).thenReturn(null);
        albumService.findById(666L);
    }

    @Test
    public void findByNonExistingName() {
        when(albumDAO.findAlbumsByName("UEEE")).thenReturn(null);
        List<Album> result = albumService.findAlbumsByName("UEEE");
        assertEquals(result, null);
    }


}
