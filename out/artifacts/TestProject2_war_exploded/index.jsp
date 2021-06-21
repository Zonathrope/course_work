<%@ page import="app.entities.User" %>
<%@ page import="app.servlets.Controller" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tender app</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">Tender App!</a>
</nav>


<div class="w3-container w3-center">
    <h1>Hello</h1>
    <div>
        <label class="w3-margin">
            <form method="post" action="/do/find">
                <input type="text" name="tag">
                <button class="btn btn-outline-primary" type="submit">Find Tender</button>
            </form>
        </label>
    </div>

    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="btn btn-outline-primary" onclick="location.href='/do/list'">List of Tenders</button>
        <%
            if (Controller.getCurrentUser() == null){
                out.println(
                        "<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/registration'\">Register</button>" +
                                "<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/login'\">Login</button>");
            } else {
                out.println(
                        "<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/owned'\">Owned tenders</button>" +
                                "<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/add'\">Add tender</button>" +
                                "<button class=\"btn btn-outline-primary\" onclick=\"location.href='/do/logout'\">Log Out</button>");
            }
        %>
    </div>
</div>
</body>