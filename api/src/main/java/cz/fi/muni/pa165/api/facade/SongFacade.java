package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.song.SongCreateDTO;
import cz.fi.muni.pa165.api.dto.song.SongDTO;
import cz.fi.muni.pa165.api.dto.song.SongUpdateDTO;

import java.util.List;

/**
 * Interface for Song facade
 *
 * @author Igor Ignac
 */
public interface SongFacade {
    /**
     * Create new song
     * @param song song to be created
     * @return id of created song
     */
    Long createSong(SongCreateDTO song);

    /**
     * Update song
     * @param song song which to be updated
     * @return updated song
     */
    SongDTO updateSong(SongUpdateDTO song);

    /**
     * Search for song by id
     * @param id id of song
     * @return song with corresponding id
     */
    SongDTO findSongById(Long id);

    /**
     * Search for song by name
     * @param name name of song
     * @return song with corresponding name
     */
    List<SongDTO> findSongByName(String name);

    /**
     * Return all songs
     * @return all songs
     */
    List<SongDTO> findAllSongs();

    /**
     * Delete song from
     * @param song song to be deleted
     */
    void deleteSong(SongDTO song);
}
