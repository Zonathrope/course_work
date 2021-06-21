<%--
  Created by IntelliJ IDEA.
  User: Steam
  Date: 19.05.2020
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Add new tender</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">Add Tender</a>
</nav>
<div>
    <%
        if ((boolean)request.getAttribute("tenderAdded")) {
            out.println("<p>Tender added!</p>");
        }
    %>
    <div class="w3-bar w3-padding-large w3-padding-24">
        <form method="post">
            <div class="form-group">
                <label for="form-control">Name of tender</label>
                <input class="form-control" type="text" id="form-control" name="tender">
            </div>
            <div class="form-group">
                <label for="form-control2">Tags</label>
                <input class="form-control" type="text" id="form-control2" name="tags">
            </div>
            <div class="form-group">
                <label for="exampleFormControlTextarea1">Description</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description"></textarea>
            </div>
            <button class="btn btn-outline-primary" type="submit">Submit</button>
        </form>
    </div>
</div>
<div>
    <center><button class="btn btn-outline-primary" onclick="location.href='../..'">Back to main</button></center>
</div>
</body>
</html>