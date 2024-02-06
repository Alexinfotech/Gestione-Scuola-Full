<%@page import="it.molinari.model.UtenteDTO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
UtenteDTO utente = (UtenteDTO) request.getAttribute("utente");
String codiceFiscale = utente != null && utente.getCodiceFiscale() != null ? utente.getCodiceFiscale() : "";
String nome = utente != null && utente.getNome() != null ? utente.getNome() : "";
String cognome = utente != null && utente.getCognome() != null ? utente.getCognome() : "";

String dataNascitaStr = utente != null && utente.getDataNascita() != null
		? new java.text.SimpleDateFormat("yyyy-MM-dd").format(utente.getDataNascita())
		: "";
String comuneDiNascita = utente != null && utente.getComuneDiNascita() != null ? utente.getComuneDiNascita() : "";

String provinvia = utente != null && utente.getProvincia() != null ? utente.getProvincia() : "";

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
			<h2>Genera solo Codice Fiscale</h2>
			<!--  	<form id="createUtenteForm" action="UtenteServlet" method="POST"> -->
			<!-- Campi del form - aggio -->

			<!--  	<form id="createUtenteForm" action="UtenteServlet" method="POST"> -->
			<!-- Quì forse qualcosa di interessante sono riuscto a farlo-->
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
				<div class="input-group">
					<select class="form-control" id="listaCodiciFiscali"
						name="listaCodiciFiscali" size="1">
						<!-- Le opzioni saranno aggiunte qui dinamicamente -->
					</select>

				</div>

			</div>



			<div id="messaggi" style="color: red;"></div>
			<!-- Bottone per generare il Codice Fiscale, qui funziona, ma sto lavoro sul front è da randagio -->
			<button type="button" class="btn btn-secondary"
				id="generateCodiceFiscale">Genera Codice Fiscale</button>

			<p style="color: red; margin: 0;">Per generare automaticamente il
				codice fiscale inserire:</p>
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
            // il resto del  codice qui , niente mancia!!
            
            var apiUrl = 'http://api.miocodicefiscale.com/calculate?' +
            'lname=' + encodeURIComponent(document.getElementById('cognome').value) +
            '&fname=' + encodeURIComponent(document.getElementById('nome').value) +
            '&gender=' + encodeURIComponent(document.getElementById('sesso').value) +
            '&city=' + encodeURIComponent(document.getElementById('comuneDiNascita').value) +
            '&state=' + encodeURIComponent(document.getElementById('provincia').value) +
            '&day=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[2]) +
            '&month=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[1]) +
            '&year=' + encodeURIComponent(document.getElementById('dataNascita').value.split('-')[0]) +
            '&omocodia_level=0' +
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
    				if (data.status && data.data && data.data.all_cf) {
        		var select = document.getElementById('listaCodiciFiscali');
        		select.innerHTML = '';  

        		data.data.all_cf.forEach((cf) => {
            	var option = document.createElement('option');
            	option.value = cf; 
            	option.textContent = cf;  
            	select.appendChild(option);  
        		});

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


	<%@include file="../../views/struttura/footer.jsp"%>
</body>



