package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.services.AdminService;
import ua.training.services.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserStatusCommand extends Command {
    private static final long serialVersionUID = -1573481555177573283L;

    private static final Logger LOG = Logger.getLogger(ChangeUserStatusCommand.class);

    /**
     * Execution method for ChangeUserStatusCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("ChangeUserStatusCommand starts");
        AdminService adminService = new AdminServiceImpl();
        Long userId = Long.parseLong(request.getParameter("user_id"));
        boolean currentStatus = Boolean.parseBoolean(request.getParameter("user_current_status"));
        LOG.debug("currentStatus is  --> " + currentStatus);

        adminService.changeUserStatus(userId);


        LOG.debug("Set the requested status to user id --> " + userId);
        LOG.debug("ChangeUserStatusCommand finished");
        return Path.COMMAND_LIST_USERS;
    }
}
