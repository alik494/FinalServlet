package ua.training.web.utils;

import org.apache.log4j.Logger;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;

import java.util.List;

public class DataHelper {
    private static final Logger LOG = Logger.getLogger(DataHelper.class);

    private DataHelper() {
    }

    /**
     * Method for getting list of users.
     *
     */
    public static List<User> getUsersList() {
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAll();
        LOG.debug("Found in DB: userList --> " + userList);
        return userList;
    }

}
