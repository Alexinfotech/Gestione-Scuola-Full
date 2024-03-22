<%@page import="it.molinari.model.ProdottoDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
ProdottoDTO prodotto = (ProdottoDTO) request.getAttribute("prodotto");
String categoriaProdotto = prodotto != null && prodotto.getCategoriaProdotto() != null ? prodotto.getCategoriaProdotto()
		: "";
String nomeProdotto = prodotto != null && prodotto.getNomeProdotto() != null ? prodotto.getNomeProdotto() : "";
String prezzo = prodotto != null && prodotto.getPrezzo() != null ? prodotto.getPrezzo() : "";
String quantita = prodotto != null && prodotto.getQuantita() != null ? String.valueOf(prodotto.getQuantita()) : "0";
String descrizioneProdotto = prodotto != null && prodotto.getDescrizioneProdotto() != null
		? prodotto.getDescrizioneProdotto()
		: "";

// Stampiamo tutti i parametri sulla console di ispezione
System.out.println("Categoria Prodotto: " + categoriaProdotto);
System.out.println("Nome Prodotto: " + nomeProdotto);
System.out.println("Prezzo: " + prezzo);
System.out.println("Quantità: " + quantita);
System.out.println("Descrizione Prodotto: " + descrizioneProdotto);
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
					<label for="categoriaProdotto">Categoria Prodotto</label> <select
						class="form-control" id="categoriaProdotto"
						name="categoriaProdotto" required>
						<option value="">Seleziona una categoria</option>
						<option value="Elettronica">Elettronica</option>
						<option value="Libri">Libri</option>
						<option value="Cancelleria">Cancelleria</option>
						<option value="Giochi">Giochi</option>
						<option value="Cucina">Cucina</option>
						<!-- Aggiungi altre categorie qui -->
					</select>
				</div>

				<div class="form-group">
					<input type="text" class="form-control" style="color: black;"
						id="nomeProdotto" name="nomeProdotto" required
						placeholder="Nome Prodotto">
				</div>
				<div class="form-group">
					<label for="prezzo">Prezzo</label> <input type="number"
						class="form-control" id="prezzo" name="prezzo" required
						placeholder="Prezzo" step="0.01">
				</div>

				<div class="form-group">
					<input type="text" class="form-control" style="color: black;"
						id="descrizioneProdotto" name="descrizioneProdotto" required
						placeholder="Descrizione Prodotto ">
				</div>





				<div class="form-group">
					<input type="number" class="form-control" style="color: black;"
						id="quantita" name="quantita" required
						placeholder="Quantità Prodotto" min="0">
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
