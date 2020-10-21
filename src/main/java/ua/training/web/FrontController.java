package ua.training.web;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.web.command.Command;
import ua.training.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FrontController  extends HttpServlet  {

    private static final Logger LOG = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            LOG.debug("FrontController doGet exception : " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            LOG.debug("FrontController doPost exception : " + e);
        }
    }


    /**
     * Method delegates all request to other services
     *
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("FrontController starts");

        // extract command name from the request
        String commandName = req.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        //obtaine command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtaned command: " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(req, resp);
        } catch (Exception ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }

        LOG.trace("Forward address --> " + forward);

        LOG.debug("FrontController finished, now go to forward address --> " + forward);

        // go to forward
        req.getRequestDispatcher(forward).forward(req, resp);
    }


}
