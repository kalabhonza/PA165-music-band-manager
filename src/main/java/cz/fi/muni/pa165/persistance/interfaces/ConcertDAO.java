package cz.fi.muni.pa165.persistance.interfaces;

import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Concert;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
public interface ConcertDAO {

    Concert findById(long id);
    List<Concert> findAll();
    List<Concert> findAllByDate(LocalDate date);
    void create(Concert concert);
    void update(Concert concert);
    void remove(Concert concert);

}
