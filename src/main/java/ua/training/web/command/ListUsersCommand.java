package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListUsersCommand  extends Command {

    private static final long serialVersionUID = -1573481565177573283L;

    private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

    /**
     * Execution method for ListUser command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("ListUsersCommand starts");
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getAll();
        LOG.debug("Found in DB: userList --> " + userList);

        request.setAttribute("userList", userList);
        LOG.debug("Set the request attribute: userList --> " + userList);

        LOG.debug("ListUsersCommand finished");
        return Path.PAGE_ADMIN_PAGE_USERLIST;
    }
}
