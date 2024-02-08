<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/cerulean/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/style.css">
    </head>
    <style>
.pie-chart {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	position: relative;
}

.slice {
	position: absolute;
	width: 100%;
	height: 100%;
	clip: rect(0px, 75px, 150px, 0px);
	border-radius: 50%;
}
</style>
<div id="corpoPrincipale" class="container mt-6">
    
	<h2>Dashboard</h2>

	<!-- Dashboard e statistiche -->
	<div class="row">
		<div class="col-md-6">
			<h4>Statistiche Utenti</h4>
			<p>Numero totale di utenti: 120</p>
			<!-- Qui potrei inserire un grafico se ne ho voglia-->
		</div>
		<div class="col-md-6">
			<h4>Report Attività</h4>
			<p>Attività completate questa settimana: 50</p>
			<!-- Qui potrei inserire un grafico sempre se ne ho vpglia -->
		</div>
		<div class="col-md-6">
			<h4>Elenco Attività</h4>
			<p>Verificare il report di fine mese</p>
			<p>Aggiornare il database clienti</p>
			<p>Preparare la presentazione per la riunione</p>
		</div>

		<div class="col-md-6">
			<h4>Notifiche Importanti</h4>
			<p>Manutenzione programmata per il 15/04/2024</p>
		</div>
		<div class="col-md-6">
			<h4>Prossimi Eventi</h4>
			<p>Riunione di Team - 20/04/2024</p>
			
		</div>
	</div>



	<!-- Feed di aggiornamenti -->


<!-- Esempio di Grafico a Torta, semplice perchè mi siddio -->
<div class="chart-section">
	<h4>Report Utenti*</h4>
	<div class="pie-chart">
		<div class="slice"
			style="transform: rotate(0deg); background-color: red;"></div>
		<div class="slice"
			style="transform: rotate(90deg); background-color: green;"></div>
		<div class="slice"
			style="transform: rotate(180deg); background-color: blue;"></div>
		<!-- Aggiungo altre fette del grafico qui, non penso proprio!!-->
	</div>
</div>


</div>
	<div class="updates-feed">
		<h4>Ultimi Aggiornamenti</h4>
		<p>Rilasciata nuova versione del software - 10/04/2024</p>
	</div>

</html>

