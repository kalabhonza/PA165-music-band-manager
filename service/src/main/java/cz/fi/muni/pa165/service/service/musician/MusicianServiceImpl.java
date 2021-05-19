package cz.fi.muni.pa165.service.service.musician;

import cz.fi.muni.pa165.api.exceptions.BandManagerServiceException;
import cz.fi.muni.pa165.api.exceptions.ErrorStatus;
import cz.fi.muni.pa165.entities.Band;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistence.interfaces.MusicianDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleš Paroulek
 */
@Service
public class MusicianServiceImpl implements MusicianService {
    private MusicianDAO musicianDAO;

    @Autowired
    public MusicianServiceImpl(MusicianDAO musicianDAO) {
        this.musicianDAO = musicianDAO;
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
    public List<Musician> findAllByBand(Band band) {
        return musicianDAO.findAllByBand(band);
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

    private void passwordCheck(String passwordHash, String password){
        String hash = hashPassword(password);
        if (!hash.equals(passwordHash))
            throw new BandManagerServiceException("Wrong password/login combination", ErrorStatus.BAD_LOGIN);
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
