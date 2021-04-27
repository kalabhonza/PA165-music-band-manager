package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.BandDTO;
import cz.fi.muni.pa165.api.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.api.dto.MusicianDTO;
import cz.fi.muni.pa165.enums.Instrument;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface MusicianFacade {
    /**
     * Finds musician by its id
     * @param id Musician's id
     * @return Musician with given id or null if it doesn't exist
     */
    MusicianDTO findById(long id);

    /**
     * Finds musician by its username
     * @param userName Username of a musician
     * @return Musician with given username or null if it doesn't exist
     */
    MusicianDTO findByUserName(String userName);

    /**
     * Finds all musicians
     * @return All musicians in database
     */
    List<MusicianDTO> findAll();

    /**
     * Finds all musicians of given band
     * @param band Band of which musicians will be found
     * @return Musicians of given band
     */
    List<MusicianDTO> findAllByBand(BandDTO band);

    /**
     * Creates a new musician
     * @param musician Musician to be created
     * @return id of created musician
     */
    Long create(MusicianCreateDTO musician);

    /**
     * Updates musician
     * @param musician Musician to be updated
     * @return updated musician
     */
    MusicianDTO update(MusicianDTO musician);

    /**
     * Removes musician
     * @param musician Musician to be removed
     */
    void remove(MusicianDTO musician);
}
