<%@page import="it.molinari.model.UtenteDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<%
UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
%>
<%
String formattedDate = "";
if (utente != null && utente.getDataNascita() != null) {
	formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita());
}
%>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">
			<h2>Utente</h2>
			<form id="updateUtenteForm" action="UtenteServlet" method="POST">

				<input type="hidden" name="_method" value="PUT"> <input
					type="hidden" name="id" value="<%=utente.getCodiceFiscale()%>">

				<div class="form-group">
					<label for="nome">Nome:</label> <input type="text"
						class="form-control" id="nome" name="nome"
						value="<%=utente.getNome()%>" required>
				</div>
				<div class="form-group">
					<label for="cognome">Cognome:</label> <input type="text"
						class="form-control" id="cognome" name="cognome"
						value="<%=utente.getCognome()%>" required>
				</div>
				<div class="form-group">
					<label for="email">e-mail:</label> <input type="email"
						class="form-control" id="email" name="email"
						value="<%=utente.getEmail()%>" required>
				</div>
				<div class="form-group">
					<label for="dataNascita">Data di nascita:</label> <input
						type="date" class="form-control" id="dataNascita"
						name="dataNascita" value="<%=formattedDate%>" required>
				</div>
				
				<div class="form-group">
					<label for="comuneDiNascita">Comune di Nascita:</label> <input
						type="text" class="form-control" id="comuneDiNascita"
						name="comuneDiNascita"  min="1900-01-01" value="<%=utente.getComuneDiNascita()%>"required>
				</div>
				
				<div class="form-group">
					<label for="provincia">Provincia:</label> <input type="text"
						class="form-control" id="provincia" name="provincia" maxlength="2"
						value="<%=utente.getProvincia()%>" required>
				</div>	
				<div class="form-group">
					<label for="comuneDiResidenza">Comune di Residenza:</label> <input
						type="text" class="form-control" id="comuneDiResidenza"
						name="comuneDiResidenza"
						value="<%=utente.getComuneDiResidenza()%>" required>
				</div>
				<div class="form-group">
					<label for="via">Via:</label> <input type="text"
						class="form-control" id="via" name="via"
						value="<%=utente.getVia()%>" required>
				</div>
				<div class="form-group">
					<label for="numeroCivico">Numero Civico:</label> <input type="text"
						class="form-control" id="numeroCivico" name="numeroCivico"
						value="<%=utente.getNumeroCivico()%>" required>
				</div>
				<div class="form-group">
					<label for="cap">CAP:</label> <input type="number"
						class="form-control" id="cap" name="cap" min="00000" max="99999"
						value="<%=utente.getCap()%>" required>
				</div>
				<div class="form-group">
					<label for="codiceFiscale">Codice fiscale:</label> <input
						type="text" class="form-control" id="codiceFiscale"
						name="codiceFiscale" value="<%=utente.getCodiceFiscale()%>"
						required pattern="[A-Z0-9]{16}">
				</div>

				<button type="submit" class="btn btn-primary">Modifica
					utente</button>
			</form>
		</div>
	</div>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>
