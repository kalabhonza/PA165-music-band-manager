package cz.fi.muni.pa165.service.service.musician;

import cz.fi.muni.pa165.api.exceptions.BandManagerServiceException;
import cz.fi.muni.pa165.api.exceptions.ErrorStatus;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistence.interfaces.BandDAO;
import cz.fi.muni.pa165.persistence.interfaces.MusicianDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Service
public class MusicianServiceImpl implements MusicianService {
    private MusicianDAO musicianDAO;
    private BandDAO bandDAO;

    @Autowired
    public MusicianServiceImpl(MusicianDAO musicianDAO, BandDAO bandDAO) {
        this.musicianDAO = musicianDAO;
        this.bandDAO = bandDAO;
    }

    @Override
    public Musician findById(long id) {
        Musician musician = musicianDAO.findById(id);
        if (musician == null) {
            throw new DataAccessException("Musician with id " + id + " was not found.") {};
        }
        return musician;
    }

    @Override
    public Musician acceptOffer(Musician musician, Band band) {
        musician.acceptOffer(band);
        band.getMembers().add(musician);
        bandDAO.update(band);
        return musicianDAO.update(musician);
    }

    @Override
    public void declineOffer(Musician musician, Band band) {
        musician.declineOffer(band);
        musicianDAO.update(musician);

    }

    @Override
    public Musician findByUserName(String userName) {
        Musician musician = musicianDAO.findByUserName(userName);
        if (musician == null) {
            throw new DataAccessException("Musician with username " + userName + " was not found.") {};
        }
        return musician;
    }

    @Override
    public List<Musician> findAll() {
        return musicianDAO.findAll();
    }

    @Override
    public List<Musician> findAllByBand(Long bandId) {
        return musicianDAO.findAllByBand(bandId);
    }

    @Override
    public List<Musician> findAllWithoutBand() {
        return musicianDAO.findAllWithoutBand();
    }

    @Override
    public Long create(Musician musician) {
        return musicianDAO.create(musician);
    }

    @Override
    public Musician update(Musician musician) {
        Musician updatedMusician = musicianDAO.update(musician);
        if (updatedMusician == null) {
            throw new DataAccessException("Musician with id: " + musician.getId() + " does not exist") {};
        }
        if (!updatedMusician.equals(musician)) {
            throw new DataAccessException("Updating of musician with id: " + musician.getId() + " failed") {};
        }
        return updatedMusician;
    }

    @Override
    public void remove(Musician musician) {
        musicianDAO.remove(musician);
        if (musicianDAO.findById(musician.getId()) != null) {
            throw new DataAccessException("Removing of musician with id: " + musician.getId() + " failed") {};
        }
    }

    @Override
    public Musician login(String username, String password) {
        Musician musician = findByUserName(username);
        passwordCheck(musician.getPassword(), password);
        setSecurityContext(musician.getUsername(), musician.getPassword());
        return musician;
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    private void passwordCheck(String encodedPassword, String rawPassword){
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        if (!argon2PasswordEncoder.matches(rawPassword, encodedPassword))
            throw new BandManagerServiceException("Wrong password/login combination", ErrorStatus.BAD_LOGIN);
    }

    private void setSecurityContext(String name, String password){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User springUser
                = new org.springframework.security.core.userdetails.User(name, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                springUser.getUsername(),
                springUser.getPassword(),
                springUser.getAuthorities()));
    }
}
