<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>

<body>

<br/>
<br>
<form id="login" method="POST" action="controller">
    <p style="color: red;">${errorString}</p>
    <input id = "register" type="hidden" name="command" value="registration"/>
    <input id="email" type="text" name="email"/><br>
    <input id="name" type="text" name="name"/><br>
    <input type="password" name = "password" /><br>
    <input type="password" name = "password2"/><br>
    <input id="btn" type="submit"/>
</form>



</body>
</html>