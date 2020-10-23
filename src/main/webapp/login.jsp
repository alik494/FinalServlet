<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
            <input id="email" type="text" name="email"/><br>
            <input type="password" name = "password"/><br>
            <input id="btn" type="submit"/>
        </form>

    </body>
</html>