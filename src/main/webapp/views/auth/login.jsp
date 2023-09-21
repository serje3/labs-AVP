<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form method="post">
        <label class="form-label mb-3">Username:
            <input class="form-control" type="text" name="username">
        </label>
        <br/>
        <label class="form-label mb-3">Password:
            <input class="form-control" type="password" name="password">
        </label>
        <br/>
        <button class="btn btn-info mb-3" type="submit">Submit</button>

        <p>Нет аккаунта? <a href="/auth/registration">Регистрация</a></p>

        <% if (request.getAttribute("error") != null) {%>
        <span style="color:red;">Ошибка: <%=request.getAttribute("error")%></span>
        <% } %>
    </form>
</div>
</body>