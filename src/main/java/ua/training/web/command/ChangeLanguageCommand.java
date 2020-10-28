package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class ChangeLanguageCommand extends  Command{
    private static final long serialVersionUID = -157348456777573283L;

    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    /**
     * Execution method for change language command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("LanguageChangeCommand starts");
        HttpSession session = ((HttpServletRequest) request).getSession();
        String lang = request.getParameter("lang");
        Set <Role> userRole = (Set) session.getAttribute("userRole");
        session.setAttribute("currentLocale", lang);
        LOG.debug("LanguageChangeCommand finished");
        request.setAttribute("userList", session.getAttribute("userList"));
        if (userRole.contains(Role.ADMIN)) {
            return Path.PAGE_ADMIN_PAGE_USERLIST;
        } else {
            return Path.PAGE_USER_PAGE;
        }
    }
}
