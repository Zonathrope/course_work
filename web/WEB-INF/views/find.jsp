<%--  Created by IntelliJ IDEA.
  User: Steam
  Date: 21.05.2020
  Time: 16:10
  To change this template use File | Settings | File Templates.--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Tender" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Found tenders</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">Tenders</a>
</nav>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Tenders</h2>
        </div>
        <%
            List<Tender> tenders = (List<Tender>) request.getAttribute("pullOfTenders");
            if(tenders != null) {
                String status;
                out.println("<ul class=\"w3-ul\">");
                for (Tender s : tenders) {
                    if (s.getOnline()){
                        status = "online";
                    } else {status = "offline";}
                    out.println("<a href='/do/view?id=" + s.getTenderId() + "'><li class=\"w3-hover-sand\">" + s.getTenderName() + "</a><br>Status: " + status + "</li>");
                }
            }
        %>
    </div>
</div>
<div>
    <center><button class="btn btn-outline-primary" onclick="location.href='../..'">Back to main</button></center>
</div>
</body>
</html>
