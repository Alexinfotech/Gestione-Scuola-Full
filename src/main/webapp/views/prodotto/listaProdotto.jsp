<%@page import="it.molinari.model.ProdottoDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
// Recupera il valore del parametro "id" dalla richiesta
String idParametro = request.getParameter("id");

// Stampa il valore del parametro "id" e il suo tipo nella console del server
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
						<th class="header-custom">Nome Prodotto</th>
						<th class="header-custom">Prezzo</th>
						<th class="header-custom">Descizione Prodotto</th>
						<th class="header-custom"></th>
						<th class="header-custom"></th>
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
						<td><%=prodotto.getPrezzo()%></td>
						<td class="bold-text"><%=prodotto.getDescrizioneProdotto()%></td>
						<td><a
							href="ProdottoServlet?action=dettaglio&id=<%=prodotto.getId()%>"
							class="black-bold-text">Modifica</a></td>
						<td><a
							href="RecensioneServlet?action=listRecensioneProdotto&id=<%=prodotto.getId()%>"
							onclick="logId('<%=prodotto.getId()%>');" class="black-bold-text">Leggi
								Recensione</a></td>

						<td><a
							href="RecensioneServlet?action=create&idProdotto=<%=prodotto.getId()%>&descrizioneProdotto=<%=prodotto.getDescrizioneProdotto()%>&nomeProdotto=<%=prodotto.getNomeProdotto()%>"
							class="black-bold-text">Scrivi Recensione</a></td>

						<td><a
							href="ProdottoServlet?action=delete&id=<%=prodotto.getId()%>"
							onclick="return confermaEliminazione('<%=prodotto.getNomeProdotto()%>', '<%=prodotto.getId()%>');"
							class="black-bold-text">Elimina</a></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="11" class="text-center">Pultroppo non ci sono
							prodotti riprova più tardi.</td>
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
<script type="text/javascript">
    function logId(id) {
        console.log("Tipo di dato di id: " + typeof id);
        console.log("Valore di id: " + id);
    }
</script>
