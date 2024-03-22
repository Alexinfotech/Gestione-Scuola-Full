<%@page import="it.ecommerce.model.UtenteDTO"%>
<%@page import="it.molinari.service.Ruolo"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
String ruolo = (String) request.getSession().getAttribute("ruolo");
String idParametro = request.getParameter("id");
UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
String codiceFiscale = utente != null && utente.getCodiceFiscale() != null ? utente.getCodiceFiscale() : "";
String nome = utente != null && utente.getNome() != null ? utente.getNome() : "";
String cognome = utente != null && utente.getCognome() != null ? utente.getCognome() : "";

String dataNascitaStr = utente != null && utente.getDataNascita() != null
		? new java.text.SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita())
		: "";
String comuneDiNascita = utente != null && utente.getComuneDiNascita() != null ? utente.getComuneDiNascita() : "";

String comuneDiResidenza = utente != null && utente.getComuneDiResidenza() != null ? utente.getComuneDiResidenza() : "";
String provincia = utente != null && utente.getProvincia() != null ? utente.getProvincia() : "";

String via = utente != null && utente.getVia() != null ? utente.getVia() : "";
String numeroCivico = utente != null && utente.getNumeroCivico() != null ? utente.getNumeroCivico() : "";
String cap = utente != null && utente.getCap() != null ? utente.getCap() : "";
%>

<%
Integer idUtenteInteger = (Integer) session.getAttribute("idUtente");
String idUtente = idUtenteInteger.toString();
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

	<div id="corpoPrincipale">
		<h2 style="color: #1e90ff;">Inserisci tua nagrafica</h2>
		<form id="createUtenteForm" action="UtenteServletDB" method="POST">
			<input type="hidden" name="action" value="create"> <input
				type="hidden" name="idUtente" value="<%=idUtente%>">

			<!-- Quì forse qualcosa di interessante sono riuscto a farlo-->
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="nome" name="nome" required placeholder="Nome">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="cognome" name="cognome" required placeholder="Cognome">
			</div>
			<div class="form-group">
				<select class="form-control" id="sesso" name="sesso" required>
					<option value="M">Maschio</option>
					<option value="F">Femmina</option>
			</div>



			<div class="form-group">
				<input type="date" class="form-control" style="color: black;"
					id="dataNascita" name="dataNascita" min="1900-01-01" required>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="comuneDiNascita" name="comuneDiNascita" required
					placeholder="Comune di Nascita">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="provincia" name="provincia" maxlength="2" required
					placeholder="Provincia">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="comuneDiResidenza" name="comuneDiResidenza" required
					placeholder="Comune Di Residenza">
			</div>

			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="via" name="via" required placeholder="Via">
			</div>
			<div class="form-group">
				<input type="text" class="form-control" style="color: black;"
					id="numeroCivico" name="numeroCivico" required
					placeholder="Numero civico">
			</div>
			<div class="form-group">
				<input type="number" class="form-control" style="color: black;"
					id="cap" name="cap" min="00000" max="99999" required
					placeholder="CAP">
			</div>
			<div class="form-group">
				<div class="input-group">
					<input type="text" class="form-control"
						style="color: red; font-weight: bold;" id="codiceFiscale"
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


			<!-- Bottone per generare il Codice Fiscale, qui funziona, ma sto lavoro sul front è da randagio -->
			<button type="button" class="btn btn-secondary"
				id="generateCodiceFiscale" style="color: red;">
				<strong>Genera Codice Fiscale</strong>
			</button>

			<button type="submit" class="btn btn-secondary"
				style="color: #1e90ff;">
				<strong>Aggiungi Anagrafica</strong>
			</button>
			<p style="color: #1e90ff; margin: 0;">
				<strong>Per generare automaticamente il Codice Fiscale
					inserire:</strong>
			</p>
			<p style="color: #1e90ff; margin: 0;">
				<strong>Nome, Cognome, Sesso, Data di Nascita, Comune Di
					Nascita e Provincia.</strong>
			</p>


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
            '&access_token=<%=UtenteDTO.getApiToken()%>';
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
	                    messaggi.innerText += '\nErrore: il campo codice fiscale non è presente nella risposta dell\'API.';
     				 }
	            })
	            .catch(error => {
	                messaggi.innerText += '\nErrore nella chiamata all\'API: ' + error.message;
	            });
	        });
	    } else {
	        console.error("Il bottone 'generateCodiceFiscale' non è stato trovato.");
	    }
	});
	</script>
	<script>
    console.log("idUtente:", "<%=idUtente%>");
    console.log("Tipo di dato di idUtente:", typeof <%=idUtente%>);
    console.log("ruolo:", "<%=ruolo%>");
    console.log("Tipo di dato di idUtente:", typeof <%=ruolo%>);
</script>



</body>
