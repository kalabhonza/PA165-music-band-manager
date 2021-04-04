package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;

import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface MusicianDAO {
    Musician findById(long id);
    Musician findByUserName(String userName);
    List<Musician> findAll();
    List<Musician> findAllByBand(Band band);
    void create(Musician musician);
    void update(Musician musician);
    void remove(Musician musician);
}
