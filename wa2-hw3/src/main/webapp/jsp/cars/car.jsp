<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="cz.cvut.wa2.hw3.model.Car"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wa2 autopujcovna 3</title>

<link rel="stylesheet" type="text/css"
	href="../static/css/bootstrap.css" />
<script type="text/javascript" src="../static/js/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="../static/js/bootstrap.js"></script>

</head>
<body>

	<div class="container-fluid">

		<div class="header">
			<nav class="navbar navbar-default">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="../customers">Customers</a></li>
					<li class="active"><a href="../cars/">Cars</a></li>
				</ul>
			</div>
			</nav>
		</div>

		<div class="content">

			<h1>Car detail</h1>

			<form method="post" action="${car.id}" class="form-horizontal">
				<c:if test="${car.id != null}">
					<div class="form-group">
						<label for="idField" class="col-sm-2 control-label">ID</label>
						<div class="col-sm-10">
							<input id="idField" type="text" name="id" value="${car.id}"
								class="form-control" disabled="disabled" />
						</div>
					</div>
				</c:if>

				<div class="form-group">
					<label for="brandField" class="col-sm-2 control-label">Brand</label>
					<div class="col-sm-10">
						<select name="brand" id="brandField" class="form-control" >
							<option value="" label="---" />
							<c:forEach var="brand" items="${brands}">
								<option value="${brand.id}" label="${brand.brandName}" ${car.brand.id == brand.id ? 'selected="selected"' : ''}/>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="colourField" class="col-sm-2 control-label">Colour</label>
					<div class="col-sm-10">
						<input id="colourField" type="text" name="colour" class="form-control"
							value="${car.colour}" />
					</div>
				</div>

				<div class="form-group">
					<label for="plateField" class="col-sm-2 control-label">Licence plate number</label>
					<div class="col-sm-10">
						<input id="plateField" type="text" name="licencePlate" class="form-control"
							value="${car.licencePlate}" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<c:if test="${car.id != null}">
							<button class="btn btn-primary" type="submit">Update car
								info</button>
						</c:if>
						<c:if test="${car.id eq null}">
							<button class="btn btn-primary" type="submit">Save new
								car</button>
						</c:if>
					</div>
				</div>

			</form>
		</div>
	</div>

	<div class="footer"></div>

</body>
</html>