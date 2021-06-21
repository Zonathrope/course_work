<%@ page import="app.entities.Tender" %>
<%@ page import="app.entities.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.servlets.Controller" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>View</title>
    <style>
        .body {
            margin: 10px;
        }
        .commentsBorder{
            padding: 10px; /* Поля вокруг текста */
            width: 400px;
            margin: 10px;
            background-color: #eee;
            border: 2px solid #ccc;
        }
        .description{
            padding: 10px; /* Поля вокруг текста */
            width: 600px;
            margin: 10px;
        }
    </style>
</head>
<body>
<%
    Tender tender = (Tender) request.getAttribute("Tender");
%>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#"><%out.println("Tender: " + tender.getTenderName());%></a>
</nav>

<%
    out.println("<div class='description'><h2>Tender description: " + tender.getDescription() + "</h2></div>");

    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("list");
    if(comments != null) {
        for (Comment comment : comments) {
            out.println("<div class='commentsBorder'><div>Written by: " + comment.getCommentOwner().getName() + "</div>" + comment.getCommentText() + "</div>");
        }
    }
    if (tender.getOnline() && Controller.getCurrentUser() != null) {
        out.println("<form method=\"post\">" +
                "<label>Add comment:</label>" +
                "</textarea><textarea class=\"form-control\" id=\"exampleFormControlTextarea1\" rows=\"3\" name=\"comment\"></textarea>" +
                "<button class=\"btn btn-outline-primary\" type=\"submit\">Submit</button>" +
                "</form>");
    }

    if (Controller.getCurrentUser() != null && Controller.getCurrentUser().getName().equals(tender.getTenderOwner().getName())) {
        session.setAttribute("CurrentTenderID", tender.getTenderId());
        out.println("<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/delete'\">Delete</button>");
        if (!tender.getOnline()) {
            out.println("<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/startTender'\">Start</button>");
        } else if (tender.getOnline()) {
            out.println("<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/stopTender'\">Stop</button>");
        }
    }
%>
<center><button class="btn btn-outline-primary" onclick="location.href='../..'">Back to main</button></center>
</body>
</html>
