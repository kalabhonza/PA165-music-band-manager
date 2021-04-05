package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Song;

import java.util.List;

/**
 * @author Igor Ignác
 */
public interface SongDAO {
    /**
     * Saves song into database
     * @param song song object which should be saved
     */
    void create(Song song);

    /**
     * Updates song in database
     * @param song song which should be updated
     * @return updated song
     */
    Song update(Song song);

    /**
     * Search database for song matching given ID
     * @param id song's ID
     * @return found song or null
     */
    Song findSongById(Long id);

    /**
     * Search database for song matching given name
     * @param name song's name
     * @return found song or null
     */
    Song findSongByName(String name);

    /**
     * Returns all songs in database
     * @return all songs in database
     */
    List<Song> findAll();

    /**
     * Deletes song from database
     * @param song song to be deleted
     */
    void deleteSong(Song song);
}
