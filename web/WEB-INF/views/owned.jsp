<%--
  Created by IntelliJ IDEA.
  User: Steam
  Date: 21.05.2020
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Tender" %>
<%@ page language="java" contentType="text/html;charset=cp1251"%>
<%@ page pageEncoding="cp1251"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Owned</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>My tenders</h1>
</div>



<div class="w3-container w3-center w3-margin-bottom w3-padding">


    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Tenders</h2>
        </div>
        <%
            List<Tender> tenders = (List<Tender>) request.getAttribute("find");
            if(tenders != null) {
                for (Tender s : tenders) {
                    out.println("<a href='/do/view?id=" + s.getTenderId() + "'>" + s.getTenderName() + "</a>");
                }
            } else {
                out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                        +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                        "   <h5>There are no tenders yet!</h5>\n" +
                        "</div>");
            }
        %>
    </div>
</div>

<div>
    <center><button class="btn btn-outline-primary" onclick="location.href='../..'">Back to main</button></center>
</div>
</body>
</html>