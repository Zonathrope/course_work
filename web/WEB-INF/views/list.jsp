<%--
  Created by IntelliJ IDEA.
  User: Steam
  Date: 19.05.2020
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Tender" %>
<%@ page language="java" contentType="text/html;charset=cp1251"%>
<%@ page pageEncoding="cp1251"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Users list</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        .tenders{
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
        }
    </style>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>List of Tenders</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Tenders</h2>
        </div>
        <%
            List<String> names = (List<String>) request.getAttribute("userNames");
            List<Tender> tenders = (List<Tender>) request.getAttribute("pullOfTenders");
            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (Tender s : tenders) {
                    out.println("<a class='tenders' href='/do/view?id=" + s.getTenderId() + "'><li class=\"w3-hover-sand\">" + s.getTenderName() + "</li></a>");
                }
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>There are no users yet!</h5>\n" +
                    "</div>");
        %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../..'">Back to main</button>
</div>
</body>
</html>