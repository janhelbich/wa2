<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
		<form method="post" action="cars">
			<table>
				<tbody>
					<tr>
						<th>Brand</th>
						<td>
							<input type="text" name="brand" />
						</td>
					</tr>
					<tr>
						<th>Colour</th>
						<td>
							<input type="text" name="colour" />
						</td>
					</tr>
					<tr>
						<th>Licence plate number</th>
						<td>
							<input type="text" name="licencePlate" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td rowspan="2">
							<button type="submit">Create new car</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>


	<div class="footer"></div>

</body>
</html>