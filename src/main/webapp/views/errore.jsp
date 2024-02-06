<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<head>
<title>Errore</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<body>
	<%@include file="../../views/struttura/header.jsp"%>

	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale" class="container">
			<h1>Si è verificato un errore</h1>
			<%
			String errore = (String) request.getAttribute("errore");
			if (errore != null && !errore.isEmpty()) {
				out.println("<p class='alert alert-danger'>Errore: " + errore + "</p>");
			} else {
				out.println("<p class='alert alert-info'>Nessun errore rilevato.</p>");
			}
			%>
		</div>
	</div>

	<%@include file="../../views/struttura/footer.jsp"%>
</body>
</html>
