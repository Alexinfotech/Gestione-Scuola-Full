<%@page import="it.molinari.model.ProdottoDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<%
ProdottoDTO prodotto = (ProdottoDTO) request.getAttribute("prodotto");
%>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<body>
    <%@include file="../../views/struttura/header.jsp"%>
    <div id="mainContainer">
        <%@include file="../../views/struttura/menu.jsp"%>

        <div id="corpoPrincipale">
            <h2 style="color: #1e90ff;">Modifica Prodotto</h2>
            <form id="updateProdottoForm" action="ProdottoServlet" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">

                <div class="form-group">
                    <label for="nomeProdotto">Nome Prodotto</label>
                    <input type="text" class="form-control" id="nomeProdotto" name="nomeProdotto" value="<%= prodotto.getNomeProdotto() %>" required>
                </div>

                <div class="form-group">
                    <label for="prezzo">Prezzo</label>
                    <input type="text" class="form-control" id="prezzo" name="prezzo" value="<%= prodotto.getPrezzo() %>" required>
                </div>

                <div class="form-group">
                    <label for="descrizioneProdotto">Descrizione Prodotto</label>
                    <textarea class="form-control" id="descrizioneProdotto" name="descrizioneProdotto" required><%= prodotto.getDescrizioneProdotto() %></textarea>
                </div>

                <button type="submit" class="btn btn-primary">Modifica prodotto</button>
            </form>
        </div>
    </div>
    <%@include file="../../views/struttura/footer.jsp"%>
</body>
</html>
