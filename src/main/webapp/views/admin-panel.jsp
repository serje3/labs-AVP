<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Admin panel</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Имя пользователя</th>
            <th scope="col">Зашифрованный пароль</th>
            <th scope="col">Это суперпользователь?</th>
        </tr>
        </thead>
        <tbody>
        <% for (User user : ((List<User>) request.getAttribute("admin:users"))) { %>
        <tr>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getPassword()%>
            </td>
            <td><%=user.isSuperUser()%>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>