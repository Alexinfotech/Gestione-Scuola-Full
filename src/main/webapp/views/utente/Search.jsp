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
			<h2 style="color: #1e90ff;">Utente</h2>

			<form id="searchUtenteForm" action="UtenteServlet" method="GET">
				<input type="hidden" name="action" value="search"> <label
					for="searchCodiceFiscale" style="color: black;">Codice
					Fiscale:</label> <input type="text"
					style="color: black; font-weight: bold;" id="searchCodiceFiscale"
					name="codiceFiscale" required>
				<button type="submit" class="btn btn-secondary"
					style="color: #1e90ff;">
					<strong>Cerca Utente</strong>
				</button>
			</form>




		</div>
	</div>