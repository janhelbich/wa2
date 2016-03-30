<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wa2 autopujcovna 3</title>

<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css" />
<script type="text/javascript" src="../static/js/jquery-1.12.2.min.js" ></script>
<script type="text/javascript" src="../static/js/bootstrap.js" ></script>

</head>
<body>

	<div class="container-fluid">

		<div class="header">
			<nav class="navbar navbar-default">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="../customers">Customers</a></li>
						<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" 
							role="button" aria-haspopup="true" 
							aria-expanded="false">Cars <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">List all cars</a></li>
							<li><a href="new">New car</a></li>
						</ul>
					</li>
					</ul>
				</div>
			</nav>
		</div>

		<div class="content">
			
			<h1>All cars</h1>
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Brand</th>
						<th>Colour</th>
						<th>Licence plate</th>
						<th>Rent by</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="car" items="${cars}">
						<tr>
							<td><c:out value="${car.id}" /></td>
							<td><c:out value="${car.brand.brandName}" /></td>
							<td><c:out value="${car.colour}" /></td>
							<td><c:out value="${car.licencePlate}" /></td>
							<td><c:out
									value="${car.rentBy.firstName} ${car.rentBy.surname}" /></td>
							<th><a href="${car.id}" class="btn btn-info">Edit</a>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>


	<div class="footer"></div>

</body>
</html>