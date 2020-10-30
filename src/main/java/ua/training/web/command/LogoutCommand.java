package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand  extends  Command{

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("LogoutCommand starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            LOG.debug("LogoutCommand invalidate session");
        }

        LOG.debug("LogoutCommand finished");
        return Path.PAGE_LOGIN;
    }
}
