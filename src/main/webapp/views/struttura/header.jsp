<style>
/* Rimuove la sottolineatura dal testo */
#logoutLink {
	text-decoration: none; /* Rimuove la sottolineatura */
}

/* Stile per il testo "Esci" */
#logoutLink {
	font-size: 24px; /* Aumenta la dimensione del testo */
}

/* Stile per l'icona della freccia */
.bi.bi-arrow-right-circle {
	font-size: 24px; /* Regola la dimensione dell'icona */
	vertical-align: middle; /* Allinea l'icona verticalmente al testo */
}
</style>

<div id="header" class="container-fluid">
	<div class="row align-items-center">
		<div class="col-md-2">
			<h1 class="text-left text-white">E-COMMERCE</h1>
		</div>
		<div class="col-md-9 text-md-end">
			<nav>
				<ul class="list-inline">
					<li class="list-inline-item">
						<form id="logoutForm"
							action="${pageContext.request.contextPath}/LoginServletDB"
							method="post" style="display: inline;">
							<input type="hidden" name="action" value="logout">
							<!-- Aggiungi un id al link "Esci" -->
							<a href="#" id="logoutLink"
								onclick="document.getElementById('logoutForm').submit();"
								class="text-white">Esci <i class="bi bi-arrow-right-circle"></i></a>
						</form>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</div>
