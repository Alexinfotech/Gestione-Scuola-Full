<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<body>
	<%@include file="views/struttura/header.jsp"%>

	<div id="login" class="container mt-4">
		<h2 class="mb-4">Login</h2>
		<form id="loginForm" action="LoginServletDB?action=login" method="POST">
			
			
			<div class="form-group mb-3">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" name="email"
					placeholder="Inserisci la tua email" required>
			</div>

			<div class="form-group mb-3">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="Inserisci la tua password" required>
			</div>
			<button type="submit" class="btn btn-primary">Invia</button>
		</form>
		<p class="mt-3">
		<p class="mt-3 text-dark">Non hai un account?
		<p class="mt-3">
			<a href="registrazione.jsp" class="text-dark">Registrati ora</a>
		</p>


	</div>

	<div id="corpoPrincipale" class="container mt-4">
		<%
		String errore = (String) request.getAttribute("errore");
		if (errore != null && !errore.isEmpty()) {
			out.println("<div class='alert alert-danger'>" + errore + "</div>");
		} else {
			// Aggiungi un messaggio informativo se necessario
			// out.println("<div class='alert alert-info'>Messaggio informativo.</div>");
		}
		%>
	</div>

	<%@include file="views/struttura/footer.jsp"%>

</body>

