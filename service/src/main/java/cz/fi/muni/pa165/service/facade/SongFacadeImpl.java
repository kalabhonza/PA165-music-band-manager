package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.SongDTO;
import cz.fi.muni.pa165.api.facade.SongFacade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
@Service
public class SongFacadeImpl implements SongFacade {
    @Override
    public void createSong(SongDTO song) {

    }

    @Override
    public SongDTO updateSong(SongDTO song) {
        return null;
    }

    @Override
    public SongDTO findSongById(Long id) {
        return null;
    }

    @Override
    public List<SongDTO> findSongByName(String name) {
        return null;
    }

    @Override
    public List<SongDTO> findAllSongs() {
        return null;
    }

    @Override
    public void deleteSong(SongDTO song) {

    }
}
