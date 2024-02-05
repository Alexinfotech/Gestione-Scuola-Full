<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<body>
    <%@include file="views/struttura/header.jsp"%>
    
    <div id="login" class="container mt-4">
        <h2>Registrazione</h2>
        <form id="registrazioneForm" action="LoginServlet?action=register" method="POST">
            <div class="form-group">
                <label for="newEmail">Email:</label> 
                <input type="email" class="form-control" id="newEmail" name="newEmail" required>
            </div>
            <div class="form-group">
                <label for="newPassword">Password:</label> 
                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
            </div>
            <button type="submit" class="btn btn-primary">Registra</button>
        </form>
        <p class="mt-3">
		Vuoi tornare alla Home? <a href="index.jsp" class="text-dark">Home</a>
	</p>
    </div>

    <div id="corpoPrincipale" class="container">
        <% 
            String errore = (String) request.getAttribute("errore");
            if (errore != null && !errore.isEmpty()) {
                out.println("<p class='alert alert-danger'>Errore: " + errore + "</p>");
            } else {
              //  out.println("<p class='alert alert-info'>Pronto per la registrazione.</p>");
            }
        %>
    </div>

    <%@include file="views/struttura/footer.jsp"%>
</body>
