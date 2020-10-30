package ua.training.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {


    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());

        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("changeLanguageCommand", new ChangeLanguageCommand());
        //admin commands
        commands.put("listUsersCommand", new ListUsersCommand());
        commands.put("changeUserStatusCommand", new ChangeUserStatusCommand());
        commands.put("listUsersTasks", new ListUsersTasksCommand());
        commands.put("newAct", new NewActivityCommand());
//        commands.put("noCommand", new NoCommand());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {

        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
