<%@page import="it.molinari.model.ProdottoDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
ProdottoDTO prodotto = (ProdottoDTO) request.getAttribute("prodotto");
String nomeProdotto = prodotto != null && prodotto.getNomeProdotto() != null ? prodotto.getNomeProdotto() : "";
String prezzo = prodotto != null && prodotto.getPrezzo() != null ? prodotto.getPrezzo() : "";

String descrizioneProdotto = prodotto != null && prodotto.getDescrizioneProdotto() != null
		? prodotto.getDescrizioneProdotto()
		: "";

%>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">

</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<style>
.form-control::placeholder {
	color: black;
}
</style>
<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">
			<h2 style="color: #1e90ff;">Prodotto</h2>
			<form id="createUtenteForm" action="ProdottoServlet" method="POST">
				<input type="hidden" name="action" value="create">
				<!-- Quì forse qualcosa di interessante sono riuscto a farlo-->
				<div class="form-group">
					<input type="text" class="form-control" style="color: black;"
						id="nomeProdotto" name="nomeProdotto" required
						placeholder="Nome Prodotto">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" style="color: black;"
						id="prezzo" name="prezzo" required placeholder="Prezzo">
				</div>

				<div class="form-group">
					<input type="text" class="form-control" style="color: black;"
						id="descrizioneProdotto" name="descrizioneProdotto" required
						placeholder="Descrizione Prodotto ">
				</div>

				<div id="messaggi" style="color: red;"></div>


				<button type="submit" class="btn btn-secondary"
					style="color: #1e90ff;">
					<strong>Aggiungi Prodotto</strong>
				</button>

			</form>
		</div>
	</div>



	<%@include file="../../views/struttura/footer.jsp"%>
</body>
