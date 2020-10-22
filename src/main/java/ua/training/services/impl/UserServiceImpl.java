package ua.training.services.impl;

import org.apache.log4j.Logger;
import ua.training.domain.User;
import ua.training.repository.UserRepository;
import ua.training.repository.impl.UserRepositoryImpl;
import ua.training.services.UserService;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    UserRepository repository = new UserRepositoryImpl();

    @Override
    public User getOne(String login) {
        LOG.trace("Service method getOne --> " + login);
        return repository.getOne(login);
    }

    @Override
    public User getOne(long id) {
        return null;
    }

    @Override
    public User createUser(String email,String name,String password) {
        LOG.trace("Service method createUser  for email--> " + email);

        return repository.createUser( email, name, password);
    }
}
