package ua.training.services;

import ua.training.domain.User;

public interface UserService {
    User getOne(String login);
    User getOne(long id);
    User createUser(String email,String name,String password) ;
}
