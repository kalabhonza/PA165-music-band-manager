package cz.fi.muni.pa165.service.service.song;

import cz.fi.muni.pa165.entities.Song;
import cz.fi.muni.pa165.persistence.interfaces.SongDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Igor Ignác
 */
@Service
public class SongServiceImpl implements SongService {

    private SongDAO songDAO;

    @Autowired
    public SongServiceImpl(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    @Override
    public Long createSong(Song song) {
        return songDAO.create(song);
    }

    @Override
    public Song updateSong(Song song) {
        Song updatedSong = songDAO.update(song);
        if (updatedSong == null) {
            throw new DataAccessException("Song with id: " + song.getId() + "does not exist") {};
        }
        if (!updatedSong.equals(song)) {
            throw new DataAccessException("Song with id: " + song.getId() + "was not updated") {};
        }
        return updatedSong;
    }

    @Override
    public Song findSongById(Long id) {
        Song song = songDAO.findSongById(id);
        if (song == null) {
            throw new DataAccessException("Song with id: " + id + "not found") {};
        }
        return song;
    }

    @Override
    public List<Song> findSongByName(String name) {
        List<Song> song = songDAO.findSongByName(name);
        if (song == null) {
            throw new DataAccessException("Song with name: " + name + " not found") {};
        }
        return song;
    }

    @Override
    public List<Song> findAllSongs() {
        return songDAO.findAll();
    }

    @Override
    public void deleteSong(Song song) {
        songDAO.deleteSong(song);
        if (songDAO.findSongById(song.getId()) != null) {
            throw new DataAccessException("Song with id: " + song.getId() + "was not deleted") {};
        }
    }
}
