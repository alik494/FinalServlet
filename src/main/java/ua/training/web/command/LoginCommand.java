package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.Role;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;
import ua.training.web.utils.DataHelper;
import ua.training.web.utils.InputDataCheck;
import ua.training.web.utils.VerifyProvidedPassword;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginCommand extends Command {


    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {


        LOG.debug("LoginCommand starts");
        HttpSession session = req.getSession();
        UserService userService = new UserServiceImpl();
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        if (email == null || email.equals("") || password == null || password.equals("")) {
            req.setAttribute("errorString", "You entered non-valid credentials. Please,try again.");
            return Path.PAGE_LOGIN;
        }
        User user = userService.getOne(email);
        LOG.trace("Found in DB: user --> " + user);

        if (user.getEmail() == null || !VerifyProvidedPassword.isPasswordCorrect(password, user)) {
            req.setAttribute("errorString", "Cannot find user with such login/password");
            return Path.PAGE_LOGIN;
        }

        if (!user.isActive()) {
            req.setAttribute("errorString", "User was blocked by administration. Pleas send mail to admin@admin.com");
            return Path.PAGE_LOGIN;
        }

        LOG.debug("LoginCommand getUser"+ user.toString());
        session.setAttribute("user", user);
        session.setAttribute("userRole", user.getRoles());
        if (user.getRoles().contains(Role.ADMIN)) {
            List<User> userList = DataHelper.getUsersList();
            req.setAttribute("userList", userList);
            return Path.PAGE_ADMIN_PAGE_USERLIST;
        }
        return Path.PAGE_USER_PAGE;

    }
}
