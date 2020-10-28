package ua.training.web.filter;

import org.apache.log4j.Logger;
import ua.training.domain.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);


    /**
     * access for client to remove
     */
    private final Set<String> accessibleCommands;
    /**
     * accessible any logged in users
     */
    private final Set<String> commonCommands;
    /**
     * accessible commands for client
     */
    private final Set<String> clientCommands;
    /**
     * accessible commands for admin
     */
    private final Set<String> adminCommands;


    /**
     * Default constructor.
     */
    public CommandAccessFilter() {

        accessibleCommands = new HashSet<>();
        commonCommands = new HashSet<>();
        clientCommands = new HashSet<>();
        adminCommands = new HashSet<>();

        // common commands
        accessibleCommands.add("login");
        accessibleCommands.add("logout");
        accessibleCommands.add("registration");
        accessibleCommands.add("noCommand");

        accessibleCommands.add("changeLanguageCommand");

        // client commands
        clientCommands.add("userTestCommand");

        // admin commands
        adminCommands.add("createSubjectCommand");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Start initializing filter: " + CommandAccessFilter.class.getSimpleName());

        LOG.debug("Finished initializing filter: " + CommandAccessFilter.class.getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String command = httpRequest.getParameter("command");

        if (accessibleCommands.contains(command)) {
            LOG.debug("This command can be accessed by all users: " + command);
            chain.doFilter(httpRequest, httpResponse);

        } else {
            LOG.debug("This command can be accessed only by logged in users: " + command);
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                LOG.debug("Unauthorized access to resource. Client is not logged-in.");
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {

                Set userRole = (Set) session.getAttribute("userRole");
                LOG.debug("s. Check user role.");
                LOG.debug("Check user role." + userRole);
                if (userRole.contains(Role.ADMIN) && adminCommands.contains(command)) {
                    LOG.debug("User is admin. Command can be executed by admin: " + command);
                    chain.doFilter(httpRequest, httpResponse);
                } else if (userRole.contains(Role.USER) && clientCommands.contains(command)) {
                    LOG.debug("User is client. Command can be executed by client: " + command);
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }

    @Override
    public void destroy() {
        LOG.debug("Start filter destroy: " + CommandAccessFilter.class.getSimpleName());
        // do nothing
        LOG.debug("Finished filter destroy: " + CommandAccessFilter.class.getSimpleName());
    }

}
