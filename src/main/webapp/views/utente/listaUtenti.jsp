<%@page import="it.molinari.model.UtenteDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<style>
.black-bold-text {
	color: black; /* Imposta il colore del testo su nero */
	font-weight: bold; /* Imposta il testo in grassetto */
	text-decoration: none;

}
.bold-text {
    font-weight: bold; /* Imposta il testo in grassetto */
  }
</style>


<script type="text/javascript">
	function confermaEliminazione(nomeUtente, codiceFiscale) {
		return confirm('Sei sicuro di voler eliminare l\'utente ' + nomeUtente
				+ ' con codice fiscale ' + codiceFiscale + '?');
	}
</script>
</head>

<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">
			<h2>Elenco Utenti</h2>
			<table class="table-custom">
				<thead>
					<tr>
						<th class="header-custom">Codice Fiscale</th>
						<th class="header-custom">Nome</th>
						<th class="header-custom">Cognome</th>
						<th class="header-custom">Email</th>
						<th class="header-custom">Data Nascita</th>
						<th class="header-custom">Comune di Nascita</th>
						<th class="header-custom">Provincia</th>
						<th class="header-custom">Comune di Residenza</th>
						<th class="header-custom">Via</th>
						<th class="header-custom">Numero Civico</th>
						<th class="header-custom">Cap</th>




						<th class="header-custom"></th>
						<th class="header-custom"></th>
					</tr>
				</thead>
				<tbody>
					<%
					Map<String, UtenteDTO> mappaUtenti = (Map<String, UtenteDTO>) request.getAttribute("mappaUtenti");
					if (mappaUtenti != null && !mappaUtenti.isEmpty()) {
						for (Entry<String, UtenteDTO> entry : mappaUtenti.entrySet()) {
							UtenteDTO utente = entry.getValue();
					%>
					<tr>
						<td class="bold-text"><%=utente.getCodiceFiscale()%></td>
						<td><%=utente.getNome()%></td>
						<td><%=utente.getCognome()%></td>
						<td class="bold-text"><%=utente.getEmail()%></td>
						<td><%=utente.getDataNascita()%></td>
						<td><%=utente.getComuneDiNascita()%></td>
						<td><%=utente.getProvincia()%></td>
						<td><%=utente.getComuneDiResidenza()%></td>
						<td><%=utente.getVia()%></td>
						<td><%=utente.getNumeroCivico()%></td>
						<td><%=utente.getCap()%></td>

						<td><a
							href="UtenteServlet?action=dettaglio&id=<%=utente.getCodiceFiscale()%>"
							class="black-bold-text">Modifica</a></td>
						<td><a
							href="UtenteServlet?action=delete&id=<%=utente.getCodiceFiscale()%>"
							onclick="return confermaEliminazione('<%=utente.getNome()%>', '<%=utente.getCodiceFiscale()%>');"
							class="black-bold-text">Elimina</a></td>

						<%
						}
						} else {
						%>
					
					<tr>
						<td colspan="5" class="text-center">Non ci sono utenti.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>






