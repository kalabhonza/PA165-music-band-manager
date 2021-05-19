package cz.fi.muni.pa165.api.facade;

import cz.fi.muni.pa165.api.dto.user.UserDTO;

public interface UserFacade {

    UserDTO login(String username, String password);
}
