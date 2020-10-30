package ua.training;

public final  class Path {


    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTER = "/registration.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

    //user page
    public static final String PAGE_USER_PAGE = "/WEB-INF/jsp/user/userCab.jsp";

    //admin pages
    public static final String PAGE_ADMIN_PAGE_USERLIST = "/WEB-INF/jsp/admin/userList.jsp";
    public static final String PAGE_ADMIN_PAGE_TASKS = "/WEB-INF/jsp/admin/usersTasks.jsp";
    // commands
    public static final String COMMAND_LIST_USERS = "/controller?command=listUsersCommand";
    public static final String COMMAND_LIST_TASKS = "/controller?command=listUsersTasks";
}
