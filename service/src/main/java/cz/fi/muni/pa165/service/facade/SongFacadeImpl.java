package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.SongDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.service.SongService;
import cz.fi.muni.pa165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
@Service
public class SongFacadeImpl implements SongFacade {

    private SongService songService;
    private BeanMapper beanMapper;

    @Autowired
    public SongFacadeImpl(SongService songService, BeanMapper beanMapper) {
        this.songService = songService;
        this.beanMapper = beanMapper;
    }

    @Override
    public void createSong(SongDTO song) {
        Song createdSong = beanMapper.mapTo(song, Song.class);
        this.songService.createSong(createdSong);
    }

    @Override
    public SongDTO updateSong(SongDTO song) {
       Song updateSong = beanMapper.mapTo(song, Song.class);
       updateSong = songService.updateSong(updateSong);
       return this.beanMapper.mapTo(updateSong, SongDTO.class);
    }

    @Override
    public SongDTO findSongById(Long id) {
        Song song = songService.findSongById(id);
        return beanMapper.mapTo(song, SongDTO.class);
    }

    @Override
    public List<SongDTO> findSongByName(String name) {
        List<Song> songs = songService.findSongByName(name);
        return beanMapper.mapTo(songs, SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongs() {
        List<Song> songs = songService.findAllSongs();
        return beanMapper.mapTo(songs, SongDTO.class);
    }

    @Override
    public void deleteSong(SongDTO song) {
        Song deleteSong = beanMapper.mapTo(song, Song.class);
        songService.deleteSong(deleteSong);
    }
}
