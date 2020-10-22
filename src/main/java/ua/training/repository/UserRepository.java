package ua.training.repository;

import ua.training.domain.User;

public interface UserRepository {

    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    User getOne(String login);


    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    User getOne(long id);
    /**
     * Add User to database and returns a user.
     *
     * @return User entity.
     */
    User createUser(String email,String name,String password) ;
}
