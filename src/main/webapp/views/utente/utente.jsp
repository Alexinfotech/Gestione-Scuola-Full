
<%
UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
String codiceFiscale = utente != null && utente.getCodiceFiscale() != null ? utente.getCodiceFiscale() : "";
String nome = utente != null && utente.getNome() != null ? utente.getNome() : "";
String cognome = utente != null && utente.getCognome() != null ? utente.getCognome() : "";

String email = utente != null && utente.getEmail() != null ? utente.getEmail() : "";
String dataNascitaStr = utente != null && utente.getDataNascita() != null
		? new java.text.SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita())
		: "";
String comuneDiNascita = utente != null && utente.getComuneDiNascita() != null ? utente.getComuneDiNascita() : "";

String comuneDiResidenza = utente != null && utente.getComuneDiResidenza() != null ? utente.getComuneDiResidenza() : "";
String provinvia = utente != null && utente.getProvincia() != null ? utente.getProvincia() : "";

String via = utente != null && utente.getVia() != null ? utente.getVia() : "";
String numeroCivico = utente != null && utente.getNumeroCivico() != null ? utente.getNumeroCivico() : "";
String cap = utente != null && utente.getCap() != null ? utente.getCap() : "";
%>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">

</head>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

<body>
	<%@include file="../../views/struttura/header.jsp"%>
	<div id="mainContainer">
		<%@include file="../../views/struttura/menu.jsp"%>

		<div id="corpoPrincipale">
			<h2>Utente</h2>
			<form id="createUtenteForm" action="UtenteServlet" method="POST">
				<!-- Qu� forse qualcosa di interessante sono riuscto a farlo-->
				<div class="form-group">
					<input type="text" class="form-control" id="nome" name="nome"
						required placeholder="Nome">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="cognome" name="cognome"
						required placeholder="Cognome">
				</div>
				<div class="form-group">
					<select class="form-control" id="sesso" name="sesso" required>
						<option value="M">Maschio</option>
						<option value="F">Femmina</option>
				</div>

				<div class="form-group">
					<label for="email">e-mail:</label> <input type="email"
						class="form-control" id="email" name="email" required
						placeholder="E-mail">
				</div>
				<div class="form-group">
					<input type="date" class="form-control" id="dataNascita"
						name="dataNascita" min="1900-01-01" required>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="comuneDiNascita"
						name="comuneDiNascita" required placeholder="Comune di Nascita">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="provincia"
						name="provincia" maxlength="2" required placeholder="provincia">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="comuneDiResidenza"
						name="comuneDiResidenza" required
						placeholder="Comune Di Residenza">
				</div>

				<div class="form-group">
					<input type="text" class="form-control" id="via" name="via"
						required placeholder="Via">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="numeroCivico"
						name="numeroCivico" required placeholder="Numero civico">
				</div>
				<div class="form-group">
					<input type="number" class="form-control" id="cap" name="cap"
						min="00000" max="99999" required placeholder="CAP">
				</div>
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" id="codiceFiscale"
							name="codiceFiscale" size="1" value="<%=codiceFiscale%>" required
							placeholder="Codice Fiscale" pattern="[A-Z0-9]{16}"> <span
							id="erroreCodiceFiscale" class="text-danger"></span>
						<!-- Elemento per l'errore -->
						<select class="form-control" id="listaCodiciFiscali"
							name="listaCodiciFiscali" size="1">
							<!-- Le opzioni saranno aggiunte qui dinamicamente -->
						</select>
					</div>
				</div>

				<div id="messaggi" style="color: red;"></div>


				<!-- Bottone per generare il Codice Fiscale, qui funziona, ma sto lavoro sul front � da randagio -->
				<button type="button" class="btn btn-secondary"
					id="generateCodiceFiscale">Genera Codice Fiscale</button>
				
				<button type="submit" class="btn btn-primary">Aggiungi Utente
					utente</button>
				<p style="color: red; margin: 0;">Per generare automaticamente
					il codice fiscale inserire:</p>
				<p style="color: red; margin: 0;">nome, cognome, sesso, data di
					nascita, comune di nascita, e provincia.</p>


			</form>
		</div>
	</div>
	<script>
document.addEventListener('DOMContentLoaded', () => {
    var btn = document.getElementById('generateCodiceFiscale');
    if (btn) {
        btn.addEventListener('click', function() {
            // il resto del  codice qui , neinte mancia!!
            
            var apiUrl = 'http://api.miocodicefiscale.com/calculate?' +
            'lname=' + encodeURIComponent(document.getElementById('cognome').value) +
            '&fname=' + encodeURIComponent(document.getElementById('nome').value) +
            '&gender=' + encodeURIComponent(document.getElementById('sesso').value) +
            '&city=' + encodeURIComponent(document.getElementById('comuneDiNascita').value) +
            '&state=' + encodeURIComponent(document.getElementById('provincia').value) +
            '&day=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[2]) +
            '&month=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[1]) +
            '&year=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[0]) +
            '&omocodia_level=1' +
            '&access_token=<%= UtenteDTO.getApiToken() %>';
            console.log('URL API completa:', apiUrl);

	            fetch(apiUrl, {
	                method: 'GET'
	            })
	            .then(response => {
	                if (!response.ok) {
	                    throw new Error('Errore di rete o risposta non valida dal server');
	                }
	                return response.json();
	            })
	            .then(data => {
	                if (data.status && data.data && data.data.cf) {
	                    document.getElementById('codiceFiscale').value = data.data.cf;
	                    messaggi.innerText += '\nCodice Fiscale generato con successo.';
	                } else {
	                    messaggi.innerText += '\nErrore: il campo codice fiscale non � presente nella risposta dell\'API.';
     				 }
	            })
	            .catch(error => {
	                messaggi.innerText += '\nErrore nella chiamata all\'API: ' + error.message;
	            });
	        });
	    } else {
	        console.error("Il bottone 'generateCodiceFiscale' non � stato trovato.");
	    }
	});
	</script>



	<%@include file="../../views/struttura/footer.jsp"%>
</body>
