package cz.fi.muni.pa165.service.service.presentation;

import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.*;
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
    private SongDAO songDAO;
    private AlbumDAO albumDAO;
    private BandDAO bandDAO;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public PresentationDataServiceImpl(MusicianDAO musicianDAO, ManagerDAO managerDAO, BandDAO bandDAO, SongDAO songDAO, AlbumDAO albumDAO) {
        this.musicianDAO = musicianDAO;
        this.managerDAO = managerDAO;
        this.bandDAO = bandDAO;
        this.songDAO = songDAO;
        this.albumDAO = albumDAO;
    }
    @Override
    public void createData() {
        Manager manager = new Manager();
        manager.setName("John");
        manager.setUserName("johnny");
        manager.setPassword("sdfsdf");//(passwordEncoder.encode("mypasswordisgood"));
        managerDAO.create(manager);

        Band band = new Band();
        band.setName("Band 1");
        band.setStyle(Style.ROCK);
        bandDAO.create(band);

        Musician musician = new Musician();
        musician.setUsername("peetee");
        musician.setName("Peter");
        musician.setPassword("resr");//passwordEncoder.encode("pFrsFs46L4fTg"));
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.GUITAR);
        instruments.add(Instrument.BASS);
        musician.setInstruments(instruments);
        musicianDAO.create(musician);

        Song song1 = new Song();
        song1.setName("Song 1");
        song1.setDuration(new Time(65));
        songDAO.create(song1);

        Song song2 = new Song();
        song2.setName("Song 2");
        song2.setDuration(new Time(49));
        songDAO.create(song2);

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setSongs(songs);
        albumDAO.create(album1);

//        Album album2 = new Album();
//        album2.setName("Album 2");
//        album2.setSongs(songs);
//        albumDAO.create(album2);

        Set<Album> albums = new HashSet<>();
        albums.add(album1);
//        albums.add(album2);
        Set<Band> bands = new HashSet<>();
        bands.add(band);

        band.setAlbums(albums);
        band.setManager(manager);
        musician.setOffers(bands);
    }
}
