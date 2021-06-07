package cz.fi.muni.pa165.service.service.presentation;

import cz.fi.muni.pa165.api.exceptions.BandManagerServiceException;
import cz.fi.muni.pa165.api.exceptions.ErrorStatus;
import cz.fi.muni.pa165.entities.*;
import cz.fi.muni.pa165.enums.Instrument;
import cz.fi.muni.pa165.enums.Style;
import cz.fi.muni.pa165.persistence.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
    private ConcertDAO concertDAO;
    private TourDAO tourDAO;

    @Autowired
    public PresentationDataServiceImpl(MusicianDAO musicianDAO, ManagerDAO managerDAO, BandDAO bandDAO, SongDAO songDAO, AlbumDAO albumDAO, ConcertDAO concertDAO, TourDAO tourDAO) {
        this.musicianDAO = musicianDAO;
        this.managerDAO = managerDAO;
        this.bandDAO = bandDAO;
        this.songDAO = songDAO;
        this.albumDAO = albumDAO;
        this.concertDAO = concertDAO;
        this.tourDAO = tourDAO;
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
        song1.setDuration("00:03:34");
        songDAO.create(song1);

        Song song2 = new Song();
        song2.setName("Numb");
        song2.setDuration("00:04:12");
        songDAO.create(song2);

        Set<Song> songs = new HashSet<>();
        songs.add(song1);
        songs.add(song2);

        Album album1 = new Album();
        album1.setName("Xero");
        album1.setSongs(songs);
        albumDAO.create(album1);

        Song song3 = new Song();
        song3.setName("Song 3");
        song3.setDuration("00:02:23");
        songDAO.create(song3);

        Set<Song> songs2 = new HashSet<>();
        songs2.add(song3);

        Album album2 = new Album();
        album2.setName("One More Light");
        album2.setSongs(songs2);
        albumDAO.create(album2);

        Set<Album> albums = new HashSet<>();
        albums.add(album1);
        albums.add(album2);
        Set<Band> bands = new HashSet<>();
        bands.add(band);

        band.setAlbums(albums);
        band.setManager(manager);
        //musician.setOffers(bands);

        manager.setBand(band.getId());

        //band with musicians
        Manager manager3 = new Manager();
        manager3.setName("Henry James Beach");
        manager3.setUserName("admin3");
        manager3.setPassword(hashPassword("admin3"));
        managerDAO.create(manager3);

        Musician musician5 = new Musician();
        musician5.setUsername("freddie");
        musician5.setName("Freddie Mercury");
        musician5.setPassword(hashPassword("freddie"));
        List<Instrument> instruments5 = new ArrayList<>();
        instruments5.add(Instrument.PIANO);
        musician5.setInstruments(instruments5);
        musicianDAO.create(musician5);

        Musician musician6 = new Musician();
        musician6.setUsername("roger");
        musician6.setName("Roger Taylor");
        musician6.setPassword(hashPassword("roger"));
        List<Instrument> instruments6 = new ArrayList<>();
        instruments6.add(Instrument.DRUM);
        musician6.setInstruments(instruments6);
        musicianDAO.create(musician6);


        Musician musician7 = new Musician();
        musician7.setUsername("brian");
        musician7.setName("Brian May");
        musician7.setPassword(hashPassword("brian"));
        List<Instrument> instruments7 = new ArrayList<>();
        instruments7.add(Instrument.GUITAR);
        musician7.setInstruments(instruments7);
        musicianDAO.create(musician7);


        Musician musician8 = new Musician();
        musician8.setUsername("john");
        musician8.setName("John Deacon");
        musician8.setPassword(hashPassword("john"));
        List<Instrument> instruments8 = new ArrayList<>();
        instruments8.add(Instrument.BASS);
        musician8.setInstruments(instruments8);
        musicianDAO.create(musician8);


        Band band5 = new Band();
        band5.setName("Queen");
        band5.setStyle(Style.ROCK);
        bandDAO.create(band5);

        Song song5 = new Song();
        song5.setName("Death on two legs");
        song5.setDuration("00:02:13");
        songDAO.create(song5);

        Song song6 = new Song();
        song6.setName("Lazing on a sunday afternoon");
        song6.setDuration("00:03:34");
        songDAO.create(song6);

        Song song7 = new Song();
        song7.setName("Im in love with my car");
        song7.setDuration("00:05:39");
        songDAO.create(song7);

        Song song8 = new Song();
        song8.setName("You are my best friend");
        song8.setDuration("00:07:33");
        songDAO.create(song8);

        Song song9 = new Song();
        song9.setName("Sweet lady");
        song9.setDuration("00:09:34");
        songDAO.create(song9);

        Song song10 = new Song();
        song10.setName("Seaside rendezvous");
        song10.setDuration("00:02:14");
        songDAO.create(song10);

        Set<Song> songs5 = new HashSet<>();
        songs5.add(song5);
        songs5.add(song6);
        songs5.add(song7);
        songs5.add(song8);
        songs5.add(song9);
        songs5.add(song10);

        Album album5 = new Album();
        album5.setName("A night at the opera");
        album5.setSongs(songs5);
        albumDAO.create(album5);

        Album album6 = new Album();
        album6.setName("A day at the races");
        albumDAO.create(album6);


        Set<Album> albums5 = new HashSet<>();
        albums5.add(album5);
        albums5.add(album6);

        Concert concert1 = new Concert();
        concert1.setName("Prague O2 Arena");
        concert1.setDate(LocalDate.of(2021, 7, 10));
        concertDAO.create(concert1);

        Concert concert2 = new Concert();
        concert2.setName("London Royal Albert Hall");
        concert2.setDate(LocalDate.of(2021, 7, 15));
        concertDAO.create(concert2);

        Concert concert3 = new Concert();
        concert3.setName("Paris Accor Arena");
        concert3.setDate(LocalDate.of(2021, 7, 20));
        concertDAO.create(concert3);

        Set<Concert> allConcerts = new HashSet<>();
        allConcerts.add(concert1);
        allConcerts.add(concert2);
        allConcerts.add(concert3);

        Tour tour1 = new Tour();
        tour1.setName("Europe Summer Tour 2021");
        tour1.setConcerts(allConcerts);
        tourDAO.create(tour1);

        Set<Tour> allTours = new HashSet<>();
        allTours.add(tour1);

        band5.setTours(allTours);

        band5.setAlbums(albums5);

        Set<Musician> musicianSet = new HashSet<>();
        musicianSet.add(musician5);
        musicianSet.add(musician6);
        musicianSet.add(musician7);
        musicianSet.add(musician8);
        band5.setMembers(musicianSet);

        musician5.setBand(band5.getId());
        musician6.setBand(band5.getId());
        musician7.setBand(band5.getId());
        musician8.setBand(band5.getId());
        band5.setManager(manager3);
        manager3.setBand(band5.getId());

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
