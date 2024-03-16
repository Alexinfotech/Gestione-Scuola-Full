<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="it.molinari.service.Ruolo"%>
<% String ruolo = (String) request.getSession().getAttribute("ruolo");%>


<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

	
<body>
<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">

	<div id="login" class="container mt-4">
		<h2>Registrazione Nuovo Magazziniere</h2>
		<form id="registrazioneForm" action="LoginServletDB?action=registraMagazziniere"
			method="POST">
			<div class="form-group">
				<label for="newEmail">Email:</label> <input type="email"
					class="form-control" id="newEmail" name="newEmail" required>
			</div>
			<div class="form-group">
				<label for="newPassword">Password:</label> <input type="password"
					class="form-control" id="newPassword" name="newPassword" required>
			</div>
			<button type="submit" class="btn btn-primary">Registra</button>
		<button type="button" class="btn btn-secondary" onclick="window.location.href='views/welcome.jsp'">Torna alla Welcome</button>
				</form>
		
		<%
			String errore = (String) request.getAttribute("errore");
			if (errore != null && !errore.isEmpty()) {
				out.println("<p class='alert alert-danger'>Errore: " + errore + "</p>");
			} else {
				//out.println("<p class='alert alert-info'>Nessun errore rilevato.</p>");
			}
			%>
			</div>
		</div>
	
	</div>

</body>
	<%@include file="../../views/struttura/footer.jsp"%>
