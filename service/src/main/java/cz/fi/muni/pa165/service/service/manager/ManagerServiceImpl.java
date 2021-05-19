package cz.fi.muni.pa165.service.service.manager;

import cz.fi.muni.pa165.api.exceptions.BandManagerServiceException;
import cz.fi.muni.pa165.api.exceptions.ErrorStatus;
import cz.fi.muni.pa165.entities.Manager;
import cz.fi.muni.pa165.entities.Musician;
import cz.fi.muni.pa165.persistence.interfaces.ManagerDAO;
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

@Service
public class ManagerServiceImpl implements ManagerService{

    private ManagerDAO managerDAO;

    public ManagerServiceImpl(ManagerDAO managerDAO){
        this.managerDAO = managerDAO;
    }

    @Override
    public Manager findById(long id) {
        Manager manager = managerDAO.findById(id);
        if (manager == null) {
            throw new DataAccessException("Manager with id: " + id + "was not found") {};
        }
        return manager;
    }

    @Override
    public List<Manager> findAll() {
        return managerDAO.findAll();
    }

    @Override
    public Long create(Manager manager) {
        return managerDAO.create(manager);
    }

    @Override
    public Manager update(Manager manager) {
        Manager updatedManager = managerDAO.update(manager);
        if (updatedManager == null) {
            throw new DataAccessException("Manager with id: " + manager.getId() + "does not exist") {};
        }
        if (!updatedManager.equals(manager)) {
            throw new DataAccessException("Manager with id: " + manager.getId() + "was not updated") {};
        }
        return updatedManager;
    }

    @Override
    public void remove(Manager manager) {
        managerDAO.remove(manager);
        if (managerDAO.findById(manager.getId()) != null) {
            throw new DataAccessException("Manager with id: " + manager.getId() + "was not deleted") {};
        }
    }

    @Override
    public Manager findByUserName(String userName) {
        Manager manager = managerDAO.findByUserName(userName);
        if (manager == null) {
            throw new DataAccessException("Manager with userName: " + userName + "was not found") {};
        }
        return manager;
    }

    @Override
    public List<Manager> findByName(String name) {
        List<Manager> manager = managerDAO.findByName(name);
        if (manager == null) {
            throw new DataAccessException("Internal database error in method findByName") {};
        }
        return manager;
    }

    @Override
    public Manager login(String username, String password) {
        Manager manager = findByUserName(username);
        passwordCheck(manager.getPassword(), password);
        setSecurityContext(manager.getUserName(), manager.getPassword());
        return manager;
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
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        org.springframework.security.core.userdetails.User springUser
                = new org.springframework.security.core.userdetails.User(name, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                springUser.getUsername(),
                springUser.getPassword(),
                springUser.getAuthorities()));
    }
}
