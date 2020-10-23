package ua.training.web.utils;

import org.apache.log4j.Logger;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;

public class VerifyProvidedPassword {
    private static final Logger LOG = Logger.getLogger(VerifyProvidedPassword.class);

    private VerifyProvidedPassword() {
        //to hide implicit public constructor
    }

    /**
     * Method check does password encrypted or not and than check accordingly .
     *
     * @param providedPassword not encrypted password,
     *                         user
     * @return String.
     */
    public static boolean isPasswordCorrect(String providedPassword, User user) {
        UserService userService = new UserServiceImpl();
        if (userService.getOne(user.getEmail()).getPassword().equals(providedPassword)) {
            return true;
        }
        return false;
//        String hashAlgorithm = userService.getHashAlgorithm(user.getId());
//        LOG.debug("Found in DB: hashAlgorithm --> " + hashAlgorithm);
//        if( hashAlgorithm == null ){
//            return isNoHashedPasswordCorrect(providedPassword,user);
//        }
//        else {
//            return isHashedPasswordCorrect(providedPassword,user);
//        }
    }

//    public static boolean isNoHashedPasswordCorrect(String providedPassword,  User user){
//        return user.getPassword().equals(providedPassword);
//    }
//
//    public static boolean isHashedPasswordCorrect (String providedPassword,  User user){
//        UserService userService = new UserServiceImpl();
//        String salt = userService.getSalt(user.getId());
//        return user.getPassword().equals(SecurePassword.getSecurePassword(providedPassword,salt));
//    }
}

