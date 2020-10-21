package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {


    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("RegistrationCommand starts");
        return Path.PAGE_LOGIN;
    }
}
