package cz.fi.muni.pa165.service.service.presentation;

import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import cz.fi.muni.pa165.persistence.interfaces.ManagerDAO;
import cz.fi.muni.pa165.persistence.interfaces.MusicianDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PresentationDataServiceImpl implements PresentationDataService {

    private MusicianDAO musicianDAO;
    private ManagerDAO managerDAO;
    private BandDAO bandDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PresentationDataServiceImpl(MusicianDAO musicianDAO, ManagerDAO managerDAO, BandDAO bandDAO) {
        this.musicianDAO = musicianDAO;
        this.managerDAO = managerDAO;
        this.bandDAO = bandDAO;
    }
    @Override
    public void createData() {
        Manager manager = new Manager();
        manager.setName("John");
        manager.setUserName("johnny");
        manager.setPassword(passwordEncoder.encode("mypasswordisgood"));

        Band band = new Band();
        band.setManager(manager);
        band.setName("Band 1");
        band.setStyle(Style.ROCK);

        manager.setBand(band);
        managerDAO.create(manager);

        Musician musician = new Musician();
        musician.setUsername("peetee");
        musician.setName("Peter");
        musician.setPassword(passwordEncoder.encode("pFrsFs46L4fTg"));
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.GUITAR);
        instruments.add(Instrument.BASS);
        musician.setInstruments(instruments);

        Song song1 = new Song();
        song1.setName("Song 1");
        song1.setDuration(Time.valueOf("4550"));

        Song song2 = new Song();
        song2.setName("Song 2");
        song2.setDuration(Time.valueOf("3045"));

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setSongs(songs);

        Album album2 = new Album();
        album2.setName("Album 2");
        album2.setSongs(songs);

        Set<Album> albums = new HashSet<>();
        albums.add(album1);
        albums.add(album2);

        band.setAlbums(albums);

        Set<Band> bands = new HashSet<>();
        bands.add(band);

        musician.setOffers(bands);
        bandDAO.create(band);
        musicianDAO.create(musician);
    }
}
