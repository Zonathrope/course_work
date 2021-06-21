<%--
  Created by IntelliJ IDEA.
  User: Steam
  Date: 20.05.2020
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">Login</a>
</nav>

<div>
    <div class="w3-bar w3-padding-large w3-padding-24">
        <div class="alert alert-light" role="alert">
            <%
                String confirm = (String) request.getAttribute("logged");
                if(confirm != null){
                    out.println("<p>" + confirm + "</p>");
                }
            %>
        </div>
        <form method="post">
            <div class="form-group">
                <label for="exampleInputEmail1">Login</label>
                <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="name">
                <small id="emailHelp" class="form-text text-muted">Enter login.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="pass">
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