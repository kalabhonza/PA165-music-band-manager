package cz.fi.muni.pa165.service.facade;

import cz.fi.muni.pa165.api.dto.user.UserDTO;
import cz.fi.muni.pa165.api.facade.UserFacade;
import org.springframework.security.core.userdetails.User;

public class UserFacadeImpl implements UserFacade {

    @Override
    public UserDTO login(String name, String password) {
        User user = userService.login(name, password);
        return beanMapper.mapTo(user, UserDTO.class);
    }
}
