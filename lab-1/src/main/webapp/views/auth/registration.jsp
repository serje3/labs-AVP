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
        <label class="form-label">Username:
            <input class="form-control" type="text" name="username">
        </label>
        <br/>
        <label class="form-label">Password:
            <input class="form-control" type="password" name="password1">
        </label>
        <br/>
        <label class="form-label">Password again:
            <input class="form-control" type="password" name="password2">
        </label>
        <br/>
        <button class="btn btn-info" type="submit">Submit</button>
        <br/>

        <p>Есть аккаунт? <a href="/auth/login">Вход</a></p>

        <% if (request.getAttribute("error") != null) {%>
        <span style="color:red;">Ошибка: <%=request.getAttribute("error")%></span>
        <% } %>
    </form>
</div>
</body>