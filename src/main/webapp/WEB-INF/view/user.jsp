<%--
  Created by IntelliJ IDEA.
  User: 18627
  Date: 2020-8-12
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<h1>用户列表</h1>

<table>
    <tr>
        <th>用户名称</th>
        <th>电话号码</th>
    </tr>

    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.userName}</td>
            <td>${user.telephone}</td>
            <td>
                <a href="#">删除</a>
                <a href="#">编辑</a>
            </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
