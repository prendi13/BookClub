<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Title </title>
</head>
<body>
<!-- //// HEADER /////////////////////////////////////////// -->
<header>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <a href="/" class="col-8 navbar-brand"> <strong
                    class="text-warning">Welcome: ${ loggedInUser.userName }</strong>
            </a>
            <div class="col-2 row align-items-center">
                <a class="col btn btn-warning btn-sm round m-2" href="/home">Home</a>
                <a class="col btn btn-danger btn-sm round" href="/logout">LogOut</a>
            </div>
        </div>
    </div>
</header>

<!-- //// MAIN AREA //////////////////////////////////////// -->
<main role="main">
    <div class="container col-8 mt-4">
        <h2> BOOK TITLE: ${ book.getTitle() }</h2>
        <p>${ book.getUser().getUserName() } read ${ book.getTitle() } by ${ book.getAuthor() }</p>
        <p>Here are ${ book.getUser().getUserName() }'s thoughts</p>
        <hr>
        <p>${ book.getThought() }</p>
        <hr>
        <a class="btn btn-success ${book.getUser().getUserName()==loggedInUser.userName ? '' : 'disabled'}" href="/books/${ book.getId() }/edit">editar</a>
    </div>
</main>

</body>
</html>