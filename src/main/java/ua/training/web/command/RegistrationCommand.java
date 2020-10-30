package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;
import ua.training.web.utils.*;
import ua.training.web.utils.RegexContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {


    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.debug("RegistrationCommand starts");
        UserService userService = new UserServiceImpl();
        final String email = req.getParameter("email");
        final String name = req.getParameter("name");
        final String password = req.getParameter("password");
        final String password2 = req.getParameter("password2");
        boolean isValid = InputDataCheck.inputValidator(email, name, password);
        if (!isValid) {
            req.setAttribute("errorString", "You entered non-valid credentials. Please,try again.");
            return Path.PAGE_REGISTER;
        }
        if (!password.equals(password2)) {
            req.setAttribute("errorString", "password are not equal");
            return Path.PAGE_REGISTER;
        }
        if (userService.getOne(email) != null) {
            req.setAttribute("errorString", "User alredy exist!");
            return Path.PAGE_REGISTER;
        }


        userService.createUser(email, name, password);
        return Path.PAGE_LOGIN;


    }

}
