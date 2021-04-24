package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
@Service
public class SongServiceImpl implements SongService {
    @Override
    public void createSong(Song song) {

    }

    @Override
    public Song updateSong(Song song) {
        return null;
    }

    @Override
    public Song findSongById(Long id) {
        return null;
    }

    @Override
    public List<Song> findSongByName(String name) {
        return null;
    }

    @Override
    public List<Song> findAllSongs() {
        return null;
    }

    @Override
    public void deleteSong(Song song) {

    }
}
