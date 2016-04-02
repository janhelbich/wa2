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

			<h1>Truck detail</h1>

			<form method="post" action="${truck.id}" class="form-horizontal">
				<c:if test="${truck.id != null}">
					<div class="form-group">
						<label for="idField" class="col-sm-2 control-label">ID</label>
						<div class="col-sm-10">
							<input id="idField" type="text" name="id" value="${truck.id}"
								class="form-control" readonly="readonly" />
						</div>
					</div>
				</c:if>

				<div class="form-group">
					<label for="brandField" class="col-sm-2 control-label">Brand</label>
					<div class="col-sm-10">
						<select name="brand" id="brandField" class="form-control" >
							<option value="" label="---" />
							<c:forEach var="brand" items="${brands}">
								<option value="${brand.id}" label="${brand.brandName}" ${truck.brand.id == brand.id ? 'selected="selected"' : ''}/>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="colourField" class="col-sm-2 control-label">Colour</label>
					<div class="col-sm-10">
						<input id="colourField" type="text" name="colour" class="form-control"
							value="${truck.colour}" />
					</div>
				</div>

				<div class="form-group">
					<label for="plateField" class="col-sm-2 control-label">Licence plate number</label>
					<div class="col-sm-10">
						<input id="plateField" type="text" name="licencePlate" class="form-control"
							value="${truck.licencePlate}" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="maxLoadField" class="col-sm-2 control-label">Maximum load [Kg]</label>
					<div class="col-sm-10">
						<input id="maxLoadField" type="text" name="maxKgLoad" class="form-control"
							value="${truck.maxKgLoad}" />
					</div>
				</div>
				
				
				<h2>This truck is made of following materials</h2>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>Material</th>
							<th>Supplier</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="supplier" items="${truck.brand.suppliers}">
							<c:forEach var="material" items="${supplier.materials}">
								<tr>
									<td>${material.material}</td>
									<td>${supplier.supplierName}</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</tbody>					  
				</table>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<c:if test="${truck.id != null}">
							<button class="btn btn-primary" type="submit">Update truck
								info</button>
						</c:if>
						<c:if test="${truck.id eq null}">
							<button class="btn btn-primary" type="submit">Save new
								truck</button>
						</c:if>
					</div>
				</div>

			</form>
		</div>
	</div>

	<div class="footer"></div>

</body>
</html>