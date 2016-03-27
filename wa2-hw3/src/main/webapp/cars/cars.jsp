<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wa2 autopujcovna 3</title>
</head>
<body>

	<div class="header">
		<h1>New car</h1>
	</div>

	<div class="content">

		<table>
			<tbody>
				<c:forEach var="car" items="${carsList}">
					<tr>
						<th>Brand</th>
						<td><c:out value="${car.brand}" /></td>
					</tr>
					<tr>
						<th>Colour</th>
						<td><c:out value="${car.brand}" /></td>
					</tr>
					<tr>
						<th>Licence plate number</th>
						<td><c:out value="${car.brand}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>


	<div class="footer"></div>

</body>
</html>