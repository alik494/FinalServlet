<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="currentLocale" value="en" scope="session"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
    <body>

        <br/>
        <br>
        <form id="login" method="POST" action="controller">
            <p style="color: red;">${errorString}</p>
            <input  type="hidden" name="command" value="login"/>
            <fmt:message key="loginPart.login"/>
            <input id="email" type="text" name="email"/><br>
            <fmt:message key="loginPart.pass"/>
            <input type="password" name = "password"/><br>
            <input id="btn" type="submit"/>
        </form>

    </body>
</html>