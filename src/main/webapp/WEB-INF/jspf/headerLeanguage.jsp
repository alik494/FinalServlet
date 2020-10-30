<%@ page import="ua.training.Path" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<c:if test="${not empty user}">
    <header>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">

            <c:choose>

                <%--===========================================================================
                This way we define the ADMIN MENU.
                ===========================================================================--%>
                <c:when test="${user.admin}">
                    <a class="navbar-brand" href="/controller?command=listUsersCommand">Users List</a> &nbsp;
                    <a class="navbar-brand" href="/controller?command=listUsersTasks">Tasks</a>
                </c:when>


                <%--===========================================================================
                This way we define the USER MENU.
                ===========================================================================--%>
                <c:when test="${!user.admin}">
                    <a class="navbar-brand" href="controller?command=quizzeCommand">Quizzes</a> &nbsp;
                </c:when>
            </c:choose>


            <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="navbar-collapse collapse" id="navbarCollapse" style="">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
<%--                        <c:choose>--%>
<%--                            &lt;%&ndash;===========================================================================--%>
<%--                            This way we define the ADMIN page.--%>
<%--                            ===========================================================================&ndash;%&gt;--%>
<%--                            <c:when test="${userRole.name == 'admin' }">--%>
<%--                                <a class="nav-link" href="controller?command=adminPageCommand"><fmt:message--%>
<%--                                        key="admin.heading_admin"/></a>--%>
<%--                            </c:when>--%>
<%--                            &lt;%&ndash;===========================================================================--%>
<%--                            This way we define the USER profile.--%>
<%--                            ===========================================================================&ndash;%&gt;--%>
<%--                            <c:when test="${userRole.name == 'client'}">--%>
<%--                                <a class="nav-link" href="controller?command=goToUserPageCommand"><fmt:message--%>
<%--                                        key="header.menu_user"/></a>--%>
<%--                            </c:when>--%>
<%--                        </c:choose>--%>
                    </li>
                </ul>
            </div>


            <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
                    <%--===========================================================================
                    Type user name if the user object is presented in the current session.
                    ===========================================================================--%>
                <li class="nav-item">
                    <a class="nav-link p-2 disabled" >
                        <c:out value="${user.username} "/>
                    </a>
                </li>
                    <%--===========================================================================
                    Type user role name if the user object is presented in the current session.
                    ===========================================================================--%>
                <li class="nav-item">
                    <a class="nav-link p-2 disabled"  target="_blank" rel="noopener">
                        <c:if test="${not empty userRole}">
                            <c:out value="(${userRole})"/>
                        </c:if>
                    </a>
                </li>

                    <%--===========================================================================
                   Type link to logout
                   ===========================================================================--%>
                <li class="nav-item">
                    <a class="nav-link p-2" href="controller?command=logout" target="_blank" rel="noopener"
                       aria-label="Slack">
<%--                        <fmt:message--%>
<%--                                key="tag.logout"/>--%>
                        logout
                    </a>
                </li>
            </ul>

            <tags:langChange curr_lang="${locale}"  curr_uri="${pageContext.request.requestURI}"/>



        </nav>
    </header>

</c:if>

<c:if test="${empty user and title ne 'Login'}">

    <header>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="login.jsp">Login</a>
        </nav>
    </header>

</c:if>