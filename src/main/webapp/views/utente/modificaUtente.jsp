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
			<h2 style="color: #1e90ff;">Utente</h2>
			<form id="updateUtenteForm" action="UtenteServletDB" method="POST">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="_method" value="PUT"> <input
					type="hidden" name="id" value="<%=utente.getCodiceFiscale()%>">

				<div class="form-group">
					<label for="nome">Nome</label> <input type="text"
						class="form-control" style="color: black; font-weight: bold;"
						id="nome" name="nome" value="<%=utente.getNome()%>" required>
				</div>
				<div class="form-group">
					<label for="cognome">Cognome</label> <input type="text"
						class="form-control" style="color: black; font-weight: bold;"
						id="cognome" name="cognome" value="<%=utente.getCognome()%>"
						required>
				</div>
			
				<div class="form-group">
					<label for="dataNascita">Data di nascita</label> <input type="date"
						class="form-control" style="color: black; font-weight: bold;"
						id="dataNascita" name="dataNascita" value="<%=formattedDate%>"
						required>
				</div>

				<div class="form-group">
					<label for="comuneDiNascita">Comune di Nascita</label> <input
						type="text" class="form-control"
						style="color: black; font-weight: bold;" id="comuneDiNascita"
						name="comuneDiNascita" min="1900-01-01"
						value="<%=utente.getComuneDiNascita()%>" required>
				</div>

				<div class="form-group">
					<label for="provincia">Provincia</label> <input type="text"
						class="form-control" style="color: black; font-weight: bold;"
						id="provincia" name="provincia" maxlength="2"
						value="<%=utente.getProvincia()%>" required>
				</div>
				<div class="form-group">
					<label for="comuneDiResidenza">Comune di Residenza</label> <input
						type="text" class="form-control"
						style="color: black; font-weight: bold;" id="comuneDiResidenza"
						name="comuneDiResidenza"
						value="<%=utente.getComuneDiResidenza()%>" required>
				</div>
				<div class="form-group">
					<label for="via">Via</label> <input type="text"
						class="form-control" style="color: black; font-weight: bold;"
						id="via" name="via" value="<%=utente.getVia()%>" required>
				</div>
				<div class="form-group">
					<label for="numeroCivico">Numero Civico</label> <input type="text"
						class="form-control" style="color: black; font-weight: bold;"
						id="numeroCivico" name="numeroCivico"
						value="<%=utente.getNumeroCivico()%>" required>
				</div>
				<div class="form-group">
					<label for="cap">CAP</label> <input type="number"
						class="form-control" style="color: black; font-weight: bold;"
						id="cap" name="cap" min="00000" max="99999"
						value="<%=utente.getCap()%>" required>
				</div>
				<div class="form-group">
					<label for="codiceFiscale">Codice fiscale</label> <input
						type="text" class="form-control"
						style="color: red; font-weight: bold;" id="codiceFiscale"
						name="codiceFiscale" value="<%=utente.getCodiceFiscale()%>"
						required pattern="[A-Z0-9]{16}">
				</div>

				<button type="submit" class="btn btn-primary">Modifica
					anagrafica</button>
			</form>
		</div>
	</div>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>
