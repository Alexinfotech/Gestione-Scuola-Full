<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="it.molinari.model.UtenteDTO"%>

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
			<h2>Utente</h2>

			<form id="searchUtenteForm" action="UtenteServlet" method="GET">
				<input type="hidden" name="action" value="search"> <label
					for="searchCodiceFiscale">Codice Fiscale:</label> <input
					type="text" id="searchCodiceFiscale" name="codiceFiscale" required>
				<button type="submit" class="btn btn-primary">Cerca Utente</button>
			</form>




		</div>
	</div>