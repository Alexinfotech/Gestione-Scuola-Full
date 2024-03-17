<%@page import="it.molinari.model.ProdottoDTO"%>
<%@page import="it.molinari.model.CarrelloDTO"%>
<%@page import="it.molinari.service.CarrelloService"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
CarrelloService carrelloService = new CarrelloService();
List<ProdottoDTO> listaCarrello = carrelloService.recuperaCarrello(); // Assumi che il metodo recupera ritorni una lista di ProdottoDTO che rappresenta i prodotti nel carrello
request.setAttribute("listaCarrello", listaCarrello);
%>
<!DOCTYPE html>
<html>
<head>
<title>Carrello</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>
		<div id="corpoPrincipale">
			<h2 style="color: #1e90ff;">Carrello</h2>
			<table class="table">
				<thead>
					<tr>
						<th>Nome Prodotto</th>
						<th>Prezzo</th>
						<th>Descrizione</th>
						<th>Quantità</th>
						<th>Azioni</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (ProdottoDTO prodotto : listaCarrello) {
					%>
					<tr>
						<td><%=prodotto.getNomeProdotto()%></td>
						<td><%=prodotto.getPrezzo()%></td>
						<td><%=prodotto.getDescrizioneProdotto()%></td>
						<td><%=prodotto.getQuantita()%></td>
						<td><a
							href="CarrelloServlet?action=delete&id=<%=prodotto.getId()%>"
							onclick="return confirm('Sei sicuro di voler rimuovere questo prodotto dal carrello?');"
							class="btn btn-danger">Rimuovi</a></td>
					</tr>
					<%
					}
					%>
					<%
					if (listaCarrello.isEmpty()) {
					%>
					<tr>
						<td colspan="5" class="text-center">Il carrello è vuoto.</td>
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
