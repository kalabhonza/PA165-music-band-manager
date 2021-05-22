package cz.fi.muni.pa165.service.service.presentation;

import cz.fi.muni.pa165.api.exceptions.BandManagerServiceException;
import cz.fi.muni.pa165.api.exceptions.ErrorStatus;
import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        manager.setName("John Lock");
        manager.setUserName("admin");
        manager.setPassword(hashPassword("admin"));
        managerDAO.create(manager);

        //manager with no band - test create band
        Manager manager2 = new Manager();
        manager2.setName("Alton Rock");
        manager2.setUserName("admin2");
        manager2.setPassword(hashPassword("admin2"));
        managerDAO.create(manager2);

        Band band = new Band();
        band.setName("Linkin Park");
        band.setStyle(Style.ROCK);
        bandDAO.create(band);


        Musician musician = new Musician();
        musician.setUsername("user");
        musician.setName("Peter Hrubý");
        musician.setPassword(hashPassword("user"));
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(Instrument.GUITAR);
        instruments.add(Instrument.BASS);
        musician.setInstruments(instruments);
        musicianDAO.create(musician);

        Musician musician2 = new Musician();
        musician2.setUsername("user2");
        musician2.setName("Jan Kotel");
        musician2.setPassword(hashPassword("user2"));
        List<Instrument> instruments2 = new ArrayList<>();
        instruments2.add(Instrument.GUITAR);
        musician2.setInstruments(instruments2);
        musicianDAO.create(musician2);

        Musician musician3 = new Musician();
        musician3.setUsername("user3");
        musician3.setName("Josef Vojtek");
        musician3.setPassword(hashPassword("user3"));
        musicianDAO.create(musician3);

        Song song1 = new Song();
        song1.setName("In the End");
        song1.setDuration(new Time(217));
        songDAO.create(song1);

        Song song2 = new Song();
        song2.setName("Numb");
        song2.setDuration(new Time(186));
        songDAO.create(song2);

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setSongs(songs);
        albumDAO.create(album1);

        Song song3 = new Song();
        song3.setName("Song 3");
        song3.setDuration(new Time(65));
        songDAO.create(song3);

        Set<Song> songs2 = new HashSet<>();
        songs2.add(song3);

        Album album2 = new Album();
        album2.setName("Album 2");
        album2.setSongs(songs2);
        albumDAO.create(album2);

        Set<Album> albums = new HashSet<>();
        albums.add(album1);
        albums.add(album2);
        Set<Band> bands = new HashSet<>();
        bands.add(band);

        band.setAlbums(albums);
        band.setManager(manager);
        musician.setOffers(bands);

        manager.setBand(band.getId());
        System.out.println(manager);
    }

    private String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X ", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex){
            throw new BandManagerServiceException("Error while hashing password.", ErrorStatus.INTERNAL);
        }
    }
}
