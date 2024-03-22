<%@page import="it.molinari.model.UtenteDTO"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="it.molinari.service.Ruolo"%>

<%
String ruolo = (String) request.getSession().getAttribute("ruolo");
%>


<html>
<head>
<meta charset="UTF-8">
<title>Conferma Acquisto</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>
.card-custom {
	border: 1px solid #007bff;
	box-shadow: 0px 0px 10px rgba(0, 123, 255, 0.5);
}

.card-header-custom {
	background-color: #007bff;
	color: white;
	font-size: 20px;
}

.btn-primary-custom {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary-custom:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}
</style>
</head>
<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>
		<div class="container mt-3">
			<h2 class="text-center">Grazie per l'acquisto!</h2>
			<%
			UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
			if (utente != null) {
			%>
			<div class="card card-custom">
				<div class="card-header card-header-custom">Dettagli di
					Spedizione</div>
				<div class="card-body">
					<p class="card-text">
						Nome:
						<%=utente.getNome()%></p>
					<p class="card-text">
						Cognome:
						<%=utente.getCognome()%></p>
					<p class="card-text">
						Via:
						<%=utente.getVia()%></p>
					<p class="card-text">
						Civico:
						<%=utente.getNumeroCivico()%></p>
					<p class="card-text">
						Comune di Residenza:
						<%=utente.getComuneDiResidenza()%></p>
					<p class="card-text">
						CAP:
						<%=utente.getCap()%></p>
				</div>
			</div>
			<div class="text-center mt-4">
				<a href="/GestioneScuola_full/views/welcome.jsp"
					class="btn btn-primary btn-primary-custom">Continua gli
					acquisti</a>
			</div>

			<%
			} else {
			%>
			<p>Nessun dato disponibile per l'utente richiesto.</p>
			<%
			}
			%>
		</div>
	</div>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>
</html>
