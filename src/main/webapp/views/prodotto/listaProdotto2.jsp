<%@page import="it.molinari.model.ProdottoDTO"%>
<%@page import="it.molinari.service.Ruolo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
String ruolo = (String) request.getSession().getAttribute("ruolo");
String idParametro = request.getParameter("id");
System.out.println("Parametro id: " + idParametro);
if (idParametro != null && !idParametro.isEmpty()) {
	System.out.println("Tipo di id: " + idParametro.getClass().getName());
}
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
	function confermaEliminazione(nomeProdotto, id) {
		return confirm('Sei sicuro di voler eliminare la recensione '
				+ nomeProdotto + '?');
	}

	function confermaAcquisto(id) {
		return confirm('Confermi l\'acquisto del prodotto?');
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
						<th class="header-custom">Nome Prodotto</th>
						<th class="header-custom">Prezzo</th>
						<th class="header-custom">Descrizione Prodotto</th>
						<th class="header-custom">Quantita Prodotto</th>
						<th class="header-custom">Acquista Prodotto</th>
						<th colspan="2" class="header-custom">Recensioni</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ProdottoDTO> listaProdotto = (List<ProdottoDTO>) request.getAttribute("listaProdotto");
					if (listaProdotto != null && !listaProdotto.isEmpty()) {
						for (ProdottoDTO prodotto : listaProdotto) {
					%>
					<tr>
						<td class="bold-text"><%=prodotto.getNomeProdotto()%></td>
						<td class="bold-text"><%=prodotto.getPrezzo()%></td>
						<td class="bold-text"><%=prodotto.getDescrizioneProdotto()%></td>
						<%
						int quantita = Integer.parseInt(prodotto.getQuantita());
						if (quantita == 1) {
						%>
						<td class="bold-text">Ce n'è solo uno</td>
						<%
						} else {
						%>
						<td class="bold-text"><%=quantita%></td>
						<%
						}
						%>

						<%
						if (Ruolo.MAGAZZINIERE.equals(ruolo)) {
						%>
						<td><a
							href="ProdottoServlet?action=dettaglio&id=<%=prodotto.getId()%>"
							class="black-bold-text">Modifica</a></td>
						<td><a
							href="ProdottoServlet?action=delete&id=<%=prodotto.getId()%>"
							onclick="return confermaEliminazione('<%=prodotto.getNomeProdotto()%>', '<%=prodotto.getId()%>');"
							class="black-bold-text">Elimina</a></td>
						<%
						}
						if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo)) {
						if (quantita > 0) {
						%>
						<td><a
							href="ProdottoServlet?action=acquista&prodottoId=<%=prodotto.getId()%>"
							onclick="return confermaAcquisto('<%=prodotto.getId()%>');"
							class="black-bold-text">Con unClick</a></td>
						<%
						} else {
						%>
						<td class="bold-text">Esaurito</td>
						<%
						}
						}
						if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo) || Ruolo.AMMINISTRATORE.equals(ruolo)) {
						%>
						<td><a
							href="RecensioneServlet?action=listRecensioneProdotto&id=<%=prodotto.getId()%>"
							class="black-bold-text">Leggi Recensione</a></td>
						<%
						}
						if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo)) {
						%>
						<td><a
							href="RecensioneServlet?action=create&idProdotto=<%=prodotto.getId()%>&descrizioneProdotto=<%=prodotto.getDescrizioneProdotto()%>&nomeProdotto=<%=prodotto.getNomeProdotto()%>"
							class="black-bold-text">Scrivi Recensione</a></td>
						<%
						}
						%>

					</tr>

					<%
					}
					} else {
					%>
					<tr>
						<td colspan="6" class="text-center">Purtroppo non ci sono
							prodotti, riprova più tardi.</td>
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
</html>
