<%@ include file="/WEB-INF/jspf/head.jspf" %>


<html>
<body>
<h2>Hello World! userList</h2>

<%@ include file="/WEB-INF/jspf/headerLeanguage.jsp" %>
<br/>
<p style="color: red;">${   userList}</p>

<table id="list_users" class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
        <th>id</th>

        <th><fmt:message key="userList.name"/></th>

<%--        <th><fmt:message key="registration.registration_form.first_name"/></th>--%>
<%--        <th><fmt:message key="registration.registration_form.last_name"/></th>--%>
<%--        <th><fmt:message key="admin.table_header.role"/></th>--%>
<%--        <th><fmt:message key="admin.table_header.status"/></th>--%>
<%--        <th>${changeRole}</th>--%>
<%--        <th>${changeStatus}</th>--%>
    </tr>
    </thead>


    <c:forEach items="${requestScope.userList}" var="user" varStatus="loop">

        <tr>
            <td>${user.id}</td>
            <td>${user.email}</td>

            <td>${user.username} </td>
            <td>${user.password}</td>
            <td>${user.roles}</td>
<%--            <td>--%>

<%--                <c:choose>--%>
<%--                    <c:when test="${user.roleId == 0}">--%>
<%--                        ${admin_al}--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        ${user_al}--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${user.userStatus == true}">--%>
<%--                        ${act_us}--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        ${not_act_us}--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form action="controller" method="post">--%>
<%--                    <input type="hidden" name="command" value="changeRoleCommand"/>--%>
<%--                    <input type="hidden" name="user_id" value="${user.id}">--%>
<%--                    <input type="hidden" name="user_current_role" value="${user.roleId}">--%>
<%--                    <button type="submit" class = "button btn-info">${changeRole}</button>--%>
<%--                </form>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <form action="controller" method="post">--%>
<%--                    <input type="hidden" name="command" value="changeUserStatusCommand"/>--%>
<%--                    <input type="hidden" name="user_id" value="${user.id}">--%>
<%--                    <input type="hidden" name="user_current_status" value="${user.userStatus}">--%>
<%--                    <button type="submit" class = "button btn-warning">${changeStatus}</button>--%>
<%--                </form>--%>
<%--            </td>--%>
        </tr>

    </c:forEach>
</table>
<br>
</body>
</html>
