<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.molinari.model.ProdottoDTO" %>
<%@ page import="it.molinari.model.RecensioneDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Aggiungi Recensione</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Aggiungi Recensione per il Prodotto: ${prodotto.nomeProdotto}</h2>

        <p><strong>Descrizione Prodotto:</strong> ${prodotto.descrizioneProdotto}</p>
        <form action="RecensioneServlet" method="post">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="idProdotto" value="${prodotto.id}">
            <div class="mb-3">
                <label for="testo" class="form-label">Testo Recensione</label>
                <textarea class="form-control" id="testo" name="testo" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Aggiungi Recensione</button>
        </form>
    </div>
</body>
</html>
