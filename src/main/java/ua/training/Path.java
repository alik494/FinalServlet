package ua.training;

public final  class Path {


    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTER = "/registration.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_USER_PAGE = "/WEB-INF/jsp/user/user_page.jsp";
    public static final String PAGE_ADMIN_PAGE = "/WEB-INF/jsp/admin/list_quizes.jsp";

    //admin pages
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_ADMIN_CREATE_SUBJECT = "/WEB-INF/jsp/admin/createSubject.jsp";

    // commands
    public static final String COMMAND_LIST_USERS = "/controller?command=listUsersCommand";
}
