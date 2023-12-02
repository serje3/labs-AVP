<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();
%>

<h1>Hi, <%= request.getSession().getAttribute("user") %></h1>

<p>Current date and time is: <%= date %></p>