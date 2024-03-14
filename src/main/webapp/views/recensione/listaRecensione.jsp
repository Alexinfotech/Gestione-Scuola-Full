<%@page import="it.molinari.model.RecensioneDTO"%>
<%@page import="it.molinari.model.ProdottoDTO"%>

<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<% String ruolo3 = (String) request.getSession().getAttribute("ruolo");
%>


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
	function confermaEliminazione(testo, id) {
		return confirm('Sei sicuro di voler eliminare la recensione ' + testo
				+ '?');
	}
</script>
</head>

<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">
			<h2 style="color: #1e90ff;">Elenco Prodotti</h2>
			<table class="table-custom">
				<thead>
					<tr>
						<th class="header-custom">Recensione</th>






						<th class="header-custom"></th>
						<th class="header-custom"></th>
					</tr>
				</thead>
				<tbody>
					<%
					List<RecensioneDTO> listaRecensione = (List<RecensioneDTO>) request.getAttribute("listaRecensione");
					if (listaRecensione != null && !listaRecensione.isEmpty()) {
						for (RecensioneDTO recensione : listaRecensione) {
					%>
					<tr>
						<td class="bold-text"><%=recensione.getTesto()%></td>
						<%
						if (Ruolo.AMMINISTRATORE.equals(ruolo3)) {
						%>
						<td><a
							href="RecensioneServlet?action=delete&id=<%=recensione.getId()%>"
							onclick="return confermaEliminazione('<%=recensione.getTesto()%>', '<%=recensione.getId()%>');"
							class="black-bold-text">Elimina</a></td>
						<%
						}
						%>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="11" class="text-center">Pultroppo non ci sono
							recensioni su questo prodotto riprova più tardi.</td>
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






