package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.Activity;
import ua.training.domain.User;
import ua.training.services.AdminService;
import ua.training.services.UserService;
import ua.training.services.impl.AdminServiceImpl;
import ua.training.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListUsersTasksCommand extends Command {

    private static final long serialVersionUID = -1573481565177573283L;

    private static final Logger LOG = Logger.getLogger(ListUsersTasksCommand.class);

    /**
     * Execution method for ListUsersTasks command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("ListUsersTasksCommand starts");
        AdminService adminService = new AdminServiceImpl();
        List<Activity> activities = adminService.getAllNotActive();
        LOG.debug("Found in DB: activities --> " + activities);

        request.setAttribute("activities", activities);
        LOG.debug("Set the request attribute: activities --> " + activities);

        LOG.debug("ListUsersTasksCommand finished");
        return Path.PAGE_ADMIN_PAGE_TASKS;
    }
}
