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

		<jsp:include page="/jsp/include/header-template.html"></jsp:include>
		
		<div class="content">
			
			<h1>All trucks</h1>
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Brand</th>
						<th>Colour</th>
						<th>Licence plate</th>
						<th>Max load [Kg]</th>
						<th>Rent by</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="truck" items="${trucks}">
						<tr>
							<td><c:out value="${truck.id}" /></td>
							<td><c:out value="${truck.brand.brandName}" /></td>
							<td><c:out value="${truck.colour}" /></td>
							<td><c:out value="${truck.licencePlate}" /></td>
							<td><c:out value="${truck.maxKgLoad}" /></td>
							<td><c:out
									value="${truck.rentBy.firstName} ${truck.rentBy.surname}" /></td>
							<th><a href="${truck.id}" class="btn btn-info">Edit</a>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>


	<div class="footer"></div>

</body>
</html>