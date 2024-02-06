<%@page import="it.molinari.model.UtenteDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
String codiceFiscale = utente != null && utente.getCodiceFiscale() != null ? utente.getCodiceFiscale() : "";
String nome = utente != null && utente.getNome() != null ? utente.getNome() : "";
String cognome = utente != null && utente.getCognome() != null ? utente.getCognome() : "";

String dataNascitaStr = utente != null && utente.getDataNascita() != null
		? new java.text.SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita())
		: "";
String comuneDiNascita = utente != null && utente.getComuneDiNascita() != null ? utente.getComuneDiNascita() : "";

String provinvia = utente != null && utente.getProvincia() != null ? utente.getProvincia() : "";

%>
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
			<h2 style="color: #1e90ff;">Prova CF</h2>
			<form id="formCodiceFiscale" action="ProvaCodiceFiscale"
				method="POST">
				<!-- Campi del form -->
				<div class="form-group">
					<label for="nome" style="color: red;">Nome:</label> <input
						type="text" class="form-control" id="nome" name="nome" required
						value="<%=request.getParameter("nome") != null ? request.getParameter("nome") : ""%>">
				</div>
				<div class="form-group">
					<label for="cognome" style="color: red;">Cognome:</label> <input
						type="text" class="form-control" id="cognome" name="cognome"
						required
						value="<%=request.getParameter("cognome") != null ? request.getParameter("cognome") : ""%>">
				</div>
				<div class="form-group">
					<label for="sesso" style="color: red;">Sesso:</label> <select
						class="form-control" id="sesso" name="sesso" required>
						<option value="M"
							<%="M".equals(request.getParameter("sesso")) ? "selected" : ""%>>Maschio</option>
						<option value="F"
							<%="F".equals(request.getParameter("sesso")) ? "selected" : ""%>>Femmina</option>
					</select>
				</div>
				<div class="form-group">
					<label for="dataNascita" class="label-rossa">Data di
						nascita:</label> <input type="date" class="form-control" id="dataNascita"
						name="dataNascita" min="1900-01-01" required
						value="<%=request.getParameter("dataNascita") != null ? request.getParameter("dataNascita") : ""%>">
				</div>
				<div class="form-group">
					<label for="comuneDiNascita" style="color: red;">Comune di
						Nascita:</label> <input type="text" class="form-control"
						id="comuneDiNascita" name="comuneDiNascita" required
						value="<%=request.getParameter("comuneDiNascita") != null ? request.getParameter("comuneDiNascita") : ""%>">
				</div>
				<div class="form-group">
					<label for="provincia">Provincia:</label> <input type="text"
						class="form-control" id="provincia" name="provincia" maxlength="2"
						required
						value="<%=request.getParameter("provincia") != null ? request.getParameter("provincia") : ""%>">
				</div>
				<div class="form-group">
					<label for="codiceFiscale">Codice fiscale:</label> <input
						type="text" class="form-control" id="codiceFiscale"
						name="codiceFiscale"
						value="<%=request.getAttribute("codiceFiscaleGenerato") != null ? request.getAttribute("codiceFiscaleGenerato") : ""%>"
						pattern="[A-Z0-9]{16}">
				</div>

				<!-- Bottone per inviare il form alla servlet, che ovviamente non va!!-->
				<button type="submit" class="btn btn-secondary">Genera
					Codice Fiscale</button>
			</form>
		</div>
	</div>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>

