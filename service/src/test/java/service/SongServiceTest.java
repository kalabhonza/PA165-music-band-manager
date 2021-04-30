package service;

import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistance.interfaces.SongDAO;
import cz.fi.muni.pa165.service.SongService;
import cz.fi.muni.pa165.service.SongServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;

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
    private List<Song> songs;

    @Mock
    private SongDAO songDAO;

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        songService = new SongServiceImpl(songDAO);

        songA = new Song();
        songA.setName("A");
        songA.setDuration(new Time(60));

        songB = new Song();
        songB.setName("B");
        songB.setDuration(new Time(60));

        songs = new ArrayList<Song>();
        songs.add(songA);
        songs.add(songB);
    }

    @Test
    public void createSong() {
        given(songDAO.create(songA)).willReturn(1L);
        Long idA = songService.createSong(songA);
        given(songDAO.findSongById(idA)).willReturn(songA);
        Song storedA = songService.findSongById(idA);
        assertEquals(songA, storedA);
    }

    @Test
    public void updateSong() {
        songA.setName("Z");
        given(songDAO.update(songA)).willReturn(songA);
        Song updatedA = songService.updateSong(songA);
        assertEquals(songA, updatedA);
    }

    @Test
    public void deleteSong() {
        given(songDAO.findSongById(songA.getId())).willReturn(null);
        songService.deleteSong(songA);
        then(songDAO).should().deleteSong(songA);
    }

    @Test
    public void findById() {
        given(songDAO.findSongById(songA.getId())).willReturn(songA);
        Song song = songService.findSongById(songA.getId());
        assertEquals(song.getName(), songA.getName());
        assertEquals(song.getDuration(), songA.getDuration());
        then(songDAO).should().findSongById(songA.getId());
    }

    @Test
    public void findByName() {
        given(songDAO.findSongByName(songA.getName())).willReturn(songs);
        List<Song> songsAB = songService.findSongByName(songA.getName());
        assertEquals(songs, songsAB);
        then(songDAO).should().findSongByName(songA.getName());
    }

    @Test
    public void findAll() {
        given(songDAO.findAll()).willReturn(songs);
        List<Song> result = songService.findAllSongs();
        assertEquals(songs, result);
        then(songDAO).should().findAll();
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateNonExistingSong() {
        given(songDAO.findSongById(songA.getId())).willReturn(null);
        songService.updateSong(songA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void FailedDelete() {
        given(songDAO.findSongById(songA.getId())).willReturn(songA);
        songService.deleteSong(songA);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingId() {
        given(songDAO.findSongById(666L)).willReturn(null);
        songService.findSongById(666L);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNonExistingName() {
        given(songDAO.findSongByName("UEEE")).willReturn(null);
        List<Song> result = songService.findSongByName("UEEE");
    }

}
