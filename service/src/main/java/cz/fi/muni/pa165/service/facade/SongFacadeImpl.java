package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.service.service.song.SongService;
import cz.fi.muni.pa165.service.mapping.mapstruct.SongMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Igor Ignác
 */
@Service
@Transactional
public class SongFacadeImpl implements SongFacade {

    private SongService songService;
    private SongMapperImpl songMapper;

    @Autowired
    public SongFacadeImpl(SongService songService, SongMapperImpl songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    @Override
    public Long createSong(SongCreateDTO song) {
        Song createdSong = songMapper.mapToEntity(song);
        return this.songService.createSong(createdSong);
    }

    @Override
    public SongDTO updateSong(SongUpdateDTO song) {
       Song updateSong = songMapper.mapToEntity(song);
       updateSong = songService.updateSong(updateSong);
       return songMapper.mapToSongDTO(updateSong);
    }

    @Override
    public SongDTO findSongById(Long id) {
        Song song = songService.findSongById(id);
        return songMapper.mapToSongDTO(song);
    }

    @Override
    public List<SongDTO> findSongByName(String name) {
        List<Song> songs = songService.findSongByName(name);
        return songMapper.mapToListDTO(songs);
    }

    @Override
    public List<SongDTO> findAllSongs() {
        List<Song> songs = songService.findAllSongs();
        return songMapper.mapToListDTO(songs);
    }

    @Override
    public void deleteSong(SongDTO song) {
        Song deleteSong = songMapper.mapToEntity(song);
        songService.deleteSong(deleteSong);
    }
}
