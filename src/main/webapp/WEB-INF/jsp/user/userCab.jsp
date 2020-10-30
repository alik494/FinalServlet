<%@ include file="/WEB-INF/jspf/head.jspf" %>

<html>
<body>

<%@ include file="/WEB-INF/jspf/headerLeanguage.jsp" %>

<br/>
<p style="color: red;">Hello ${user.username}</p>
<form id="newAct" method="POST" action="controller">
    <p style="color: red;">${errorString}</p>
    <input type="hidden" name="command" value="newAct"/>
    <fmt:message key="activPart.nameText"/>
    <input id="email" type="text" name="text"/><br>
    <fmt:message key="activPart.nameTag"/>
    <input type="text" name="tag"/><br>
    <input type="hidden" name="userEmail" value=${user.email}/><br>
    <input type="hidden" name="userId" value=${user.id}><br>
    <input id="btn"  type="submit"><fmt:message key="activPart.addActivity"/></input>
</form>
<br>
</body>
</html>
