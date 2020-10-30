package ua.training.web.command;

import org.apache.log4j.Logger;
import ua.training.Path;
import ua.training.domain.Activity;
import ua.training.domain.User;
import ua.training.services.UserService;
import ua.training.services.impl.UserServiceImpl;
import ua.training.web.utils.InputDataCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewActivityCommand  extends Command{
    private static final Logger LOG = Logger.getLogger(NewActivityCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.debug("NewActivityCommand starts");

        UserService userService = new UserServiceImpl();
        final String text = req.getParameter("text");
        final String tag = req.getParameter("tag");
        LOG.debug("NewActivityCommand userId is " + req.getParameter("userId"));
        final Long userId =Long.parseLong(req.getParameter("userId"));
        if (text==null||tag==null||text.equals("")||tag.equals("")) {
            req.setAttribute("errorString", "You entered non-valid credentials. Please,try again.");
            return Path.PAGE_USER_PAGE;
        }

        Activity activity =userService.createNewActivity(text,tag,userId );
        LOG.debug("NewActivityCommand finish"+activity);
        return Path.PAGE_USER_PAGE;


    }
}
