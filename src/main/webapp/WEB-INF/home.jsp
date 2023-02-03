<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>

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
  <div class="container mt-4">
    <div class="row">
      <h2 class="col">Books from everyone's shelves:</h2>
      <a class="col-2 btn btn-primary btn-sm round m-2" href="/books/new">Add
        a to my shelf</a>
    </div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#Id:</th>
        <th scope="col">Title:</th>
        <th scope="col">Author Name:</th>
        <th scope="col">Posted by:</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="book" items="${books}">
        <tr>
          <th scope="row"> ${ book.getId() }</th>
          <td><a href="/books/${ book.getId() }">${ book.getTitle() }</a></td>
          <td>${ book.getAuthor() }</td>
          <td>${ book.getUser().getUserName() }</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</main>
</body>
</html>