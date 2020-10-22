package ua.training.web.utils;

import org.apache.log4j.Logger;
import ua.training.web.command.RegistrationCommand;

public class InputDataCheck {

    private static final Logger LOG = Logger.getLogger(InputDataCheck.class);
    /**
     * Method which compares input string with regular expressin
     * @param input input string
     * @param regex regular expression
     * @return result of string comparison
     */
    public static boolean inputCheck(String input, String regex)  {
        if (input.matches(regex))
            return true;
        else
            return false;
    }

    public static boolean  inputValidator(String email, String name, String password) {
        boolean isValid;
        isValid = InputDataCheck.inputCheck(email, RegexContainer.REGEX_EMAIL);
        if (!isValid) {
            LOG.debug("Non-valid email entered");
            return false;
        }
        isValid = InputDataCheck.inputCheck(name, RegexContainer.REGEX_NAME_ENG);
        if (!isValid) {
            LOG.debug("Non-valid name entered");
            return false;
        }
        isValid = InputDataCheck.inputCheck(password, RegexContainer.REGEX_PASSWORD);
        if (!isValid) {
            LOG.debug("Non-valid password entered");
            return false;
        }
        return true;
    }

}
