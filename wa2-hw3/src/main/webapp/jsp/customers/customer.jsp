<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
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

		<jsp:include page="/jsp/include/header-template.html"></jsp:include>
		
		<div class="content">

			<h1>Customer detail</h1>

			<form method="post" action="${customer.id}" class="form-horizontal">
				<c:if test="${customer.id != null}">
					<div class="form-group">
						<label for="idField" class="col-sm-2 control-label">ID</label>
						<div class="col-sm-10">
							<input id="idField" type="text" name="id" value="${customer.id}"
								class="form-control" readonly="readonly" />
						</div>
					</div>
				</c:if>

				<div class="form-group">
					<label for="firstNameField" class="col-sm-2 control-label">First name</label>
					<div class="col-sm-10">
						<input id="firstNameField" type="text" name="firstName" class="form-control"
							value="${customer.firstName}" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="surnameField" class="col-sm-2 control-label">Surname</label>
					<div class="col-sm-10">
						<input id="surnameField" type="text" name="surname" class="form-control"
							value="${customer.surname}" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<c:if test="${customer.id != null}">
							<button class="btn btn-primary" type="submit">Update customer
								info</button>
						</c:if>
						<c:if test="${customer.id eq null}">
							<button class="btn btn-primary" type="submit">Save new
								customer</button>
						</c:if>
					</div>
				</div>

			</form>
		</div>
	</div>

	<div class="footer"></div>

</body>
</html>