<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/resources/css/style.css">
</head>
<style>
.pie-chart {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	position: relative;
}

.slice {
	position: absolute;
	width: 100%;
	height: 100%;
	clip: rect(0px, 75px, 150px, 0px);
	border-radius: 50%;
}
</style>

</html>

