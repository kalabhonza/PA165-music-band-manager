package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entities.Song;

import java.util.List;

/**
 *
 * @author Igor Ignac
 */
public interface SongService {
    /**
     * Create new song
     * @param song song to be created
     */
    Long createSong(Song song);

    /**
     * Update song
     * @param song song which to be updated
     * @return updated song
     */
    Song updateSong(Song song);

    /**
     * Search for song by id
     * @param id id of song
     * @return song with corresponding id
     */
    Song findSongById(Long id);

    /**
     * Search for song by name
     * @param name name of song
     * @return song with corresponding name
     */
    List<Song> findSongByName(String name);

    /**
     * Return all songs
     * @return all songs
     */
    List<Song> findAllSongs();

    /**
     * Delete song from
     * @param song song to be deleted
     */
    void deleteSong(Song song);
}
