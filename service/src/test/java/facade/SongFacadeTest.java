package facade;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.service.SongService;
import cz.fi.muni.pa165.service.facade.SongFacadeImpl;
import cz.fi.muni.pa165.service.mapping.mapstruct.SongMapperImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author Aleš Paroulek
 */
public class SongFacadeTest {
    private SongFacade songFacade;

    private Song songA;
    private Song songB;
    private List<Song> songs;

    private SongDTO songAdto;
    private SongDTO songBdto;
    private List<SongDTO> songDTOs;

    private SongCreateDTO songCreateDTO;
    private SongUpdateDTO songUpdateDTO;

    @Mock
    private SongService songService;

    @Mock
    private SongMapperImpl songMapper;

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        songFacade = new SongFacadeImpl(songService, songMapper);

        songA = new Song();
        songA.setId(1L);
        songA.setName("A");
        songA.setDuration(new Time(60));

        songB = new Song();
        songB.setId(2L);
        songB.setName("B");
        songB.setDuration(new Time(60));

        songs = new ArrayList<Song>();
        songs.add(songA);
        songs.add(songB);

        songAdto = new SongDTO();
        songAdto.setId(songA.getId());
        songAdto.setName(songA.getName());
        songAdto.setDuration(songA.getDuration());

        songBdto = new SongDTO();
        songBdto.setId(songB.getId());
        songBdto.setName(songB.getName());
        songBdto.setDuration(songB.getDuration());

        songDTOs = new ArrayList<SongDTO>();
        songDTOs.add(songAdto);
        songDTOs.add(songBdto);

        songCreateDTO = new SongCreateDTO();
        songCreateDTO.setName("A");
        songCreateDTO.setDuration(new Time(60));

        songUpdateDTO = new SongUpdateDTO();
        songUpdateDTO.setId(1L);
        songUpdateDTO.setName("A");
        songUpdateDTO.setDuration(new Time(60));
    }

    @Test
    public void createSong() {
        given(songMapper.mapToEntity(songCreateDTO)).willReturn(songA);
        given(songService.createSong(songA)).willReturn(songA.getId());
        Long id = songFacade.createSong(songCreateDTO);
        then(songService).should().createSong(songA);

        given(songService.findSongById(songA.getId())).willReturn(songA);
        given(songMapper.mapToSongDTO(songA)).willReturn(songAdto);
        SongDTO storedA = songFacade.findSongById(id);

        assertEquals(storedA, songAdto);
    }

    @Test
    public void updateSong() {
        given(songMapper.mapToEntity(songUpdateDTO)).willReturn(songA);
        given(songService.updateSong(songA)).willReturn(songA);
        given(songMapper.mapToSongDTO(songA)).willReturn(songAdto);

        songA.setName("Z");
        songAdto.setName("Z");

        SongDTO updated = songFacade.updateSong(songUpdateDTO);
        then(songService.updateSong(songA));
        assertEquals(songA.getName(), updated.getName());
    }

    @Test
    public void findSongById() {
        given(songService.findSongById(songA.getId())).willReturn(songA);
        given(songMapper.mapToSongDTO(songA)).willReturn(songAdto);
        SongDTO song = songFacade.findSongById(songA.getId());
        assertEquals(song, songAdto);
        then(songService).should().findSongById(songA.getId());
    }

    @Test
    public void findSongByName() {
        given(songService.findSongByName(songA.getName())).willReturn(songs);
        given(songMapper.mapToListDTO(songs)).willReturn(songDTOs);
        List<SongDTO> result = songFacade.findSongByName(songA.getName());
        assertEquals(songDTOs, result);
        then(songService).should().findSongByName(songA.getName());
    }

    @Test
    public void findAllSongs() {
        given(songService.findAllSongs()).willReturn(songs);
        given(songMapper.mapToListDTO(songs)).willReturn(songDTOs);
        List<SongDTO> result = songFacade.findAllSongs();
        assertEquals(songDTOs, result);
        then(songService).should().findAllSongs();
    }

    @Test
    public void deleteSong() {
        given(songMapper.mapToEntity(songAdto)).willReturn(songA);
        songFacade.deleteSong(songAdto);
        then(songService).should().deleteSong(songA);
    }

}
