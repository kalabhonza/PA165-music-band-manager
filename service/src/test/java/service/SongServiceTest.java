package service;

import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.SongDAO;
import cz.fi.muni.pa165.service.SongService;
import cz.fi.muni.pa165.service.SongServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public class SongServiceTest {
    private SongService songService;
    private Song songA;
    private Song songB;
    private Song songC;
    private List<Song> songs;

    @Mock
    private SongDAO songDAO;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeTest() {
        songService = new SongServiceImpl(songDAO);

        songA = new Song();
        songA.setName("A");
        songA.setDuration(new Time(60));

        songB = new Song();
        songB.setName("B");
        songB.setDuration(new Time(60));

        songC = new Song();
        songC.setName("C");
        songC.setDuration(new Time(60));

        songs = new ArrayList<Song>();
        songs.add(songA);
        songs.add(songB);
        songs.add(songC);
    }

    @Test
    public void createSong() {
        when(songDAO.create(songA)).thenReturn(1L);
        Long idA = songService.createSong(songA);

        when(songDAO.findSongById(idA)).thenReturn(songA);
        Song storedA = songService.findSongById(idA);

        assertEquals(songA, storedA);
    }

    @Test
    public void updateSong() {
        songA.setName("Z");

        when(songDAO.update(songA)).thenReturn(songA);
        Song updatedA = songService.updateSong(songA);

        assertEquals(songA, updatedA);
    }

    @Test
    public void deleteSong() {
        songService.deleteSong(songA);
        then(songDAO).should().deleteSong(songA);
    }

    @Test
    public void findById() {
        when(songDAO.findSongById(songA.getId())).thenReturn(songA);

        Song song = songService.findSongById(songA.getId());
        assertEquals(song.getName(), songA.getName());
        assertEquals(song.getDuration(), songA.getDuration());

        verify(songDAO, times(1)).findSongById(songA.getId());
    }

    @Test
    public void findByName() {
        when(songDAO.findSongByName(songA.getName())).thenReturn(songs);

        List<Song> songsAB = songService.findSongByName(songA.getName());
        assertEquals(songs, songsAB);

        verify(songDAO, times(1)).findSongByName(songA.getName());
    }

    @Test
    public void findAll() {
        when(songDAO.findAll()).thenReturn(songs);
        List<Song> result = songService.findAllSongs();
        assertEquals(songs, result);
        verify(songDAO,times(1)).findAll();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExistingSong() {
        when(songDAO.findSongById(songA.getId())).thenReturn(null);
        songService.updateSong(songA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void FailedDelete() {
        when(songDAO.findSongById(songA.getId())).thenReturn(songA);
        songService.deleteSong(songA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        when(songDAO.findSongById(666L)).thenReturn(null);
        songService.findSongById(666L);
    }

    @Test
    public void findByNonExistingName() {
        when(songDAO.findSongByName("UEEE")).thenReturn(null);
        List<Song> result = songService.findSongByName("UEEE");
        assertEquals(result, null);
    }

}
