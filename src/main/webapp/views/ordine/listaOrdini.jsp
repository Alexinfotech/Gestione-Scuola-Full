<%@ page import="it.molinari.model.OrdineDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
Map<String, List<OrdineDTO>> ordiniPerData = (Map<String, List<OrdineDTO>>) request.getAttribute("ordiniPerData");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
<title>Elenco Ordini</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<style>
.hidden {
	display: none;
}

.table-custom, .table-custom th, .table-custom td {
	border-collapse: collapse;
}

.table-custom th, .table-custom td {
	padding: 10px;
	text-align: left;
}

.header-custom {
	background-color: #1e90ff;
	color: white;
}

.bold-text {
	font-weight: bold;
}

.black-bold-text {
	color: black;
	font-weight: bold;
	text-decoration: none;
}
</style>
</head>
<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>
		<div id="corpoPrincipale">
			<h2>Elenco Ordini</h2>
			<table class="table-custom">
				<thead>
					<tr>
						<th class="header-custom">Data Ordine</th>
						<th class="header-custom">Totale Ordini</th>
						<th class="header-custom">Dettaglio</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (ordiniPerData != null && !ordiniPerData.isEmpty()) {
					%>
					<%
					for (Map.Entry<String, List<OrdineDTO>> entry : ordiniPerData.entrySet()) {
					%>
					<%
					String dataOrdine = entry.getKey();
					%>
					<%
					double totalePerData = 0;
					for (OrdineDTO ordine : entry.getValue()) {
						for (Map.Entry<String, Double> dettaglio : ordine.getDettagliProdotti().entrySet()) {
							totalePerData += dettaglio.getValue();
						}
					}
					%>
					<tr>
						<td class="bold-text"><%=dataOrdine%></td>
						<td class="bold-text">€<%=String.format("%.2f", totalePerData)%></td>
						<td><a href="javascript:void(0)"
							onclick="toggleDettagli('dettagli<%=dataOrdine%>')"
							class="black-bold-text">Visualizza prodotti ordinati</a></td>
					</tr>
					<tr id="dettagli<%=dataOrdine%>" class="hidden">
						<td colspan="3">
							<table class="table-custom" style="width: 100%;">
								<thead>
									<tr>
										<th>Codice Ordine</th>
										<th>Prodotti</th>
										<th>Costo</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (OrdineDTO ordine : entry.getValue()) {
									%>
									<tr>
										<td><%=ordine.getIdOrdine()%></td>
										<td>
											<%
											for (Map.Entry<String, Double> dettaglio : ordine.getDettagliProdotti().entrySet()) {
											%> <%=dettaglio.getKey()%> <br> <%
 }
 %>
										</td>
										<td>
											<%
											for (Map.Entry<String, Double> dettaglio : ordine.getDettagliProdotti().entrySet()) {
											%> €<%=String.format("%.2f", dettaglio.getValue())%> <br>
											<%
											}
											%>
										</td>
									</tr>
									<%
									}
									%>
								</tbody>
							</table>
						</td>
					</tr>
					<%
					}
					%>
					<%
					} else {
					%>
					<tr>
						<td colspan="3" class="text-center">Nessun ordine trovato.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		function toggleDettagli(id) {
			var dettagliDiv = document.getElementById(id);
			if (dettagliDiv) {
				dettagliDiv.classList.toggle('hidden');
			}
		}
	</script>
	<%@include file="../../views/struttura/footer.jsp"%>
</body>
</html>
