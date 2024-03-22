<%@page import="it.molinari.model.RuoloDTO"%>
<%@page import="it.molinari.service.Ruolo"%>
<%@page import="it.molinari.model.ProdottoDTO"%>


<%
String ruolo1 = (String) request.getSession().getAttribute("ruolo");
%>

<div id="menu">
	<ul>
		<%
		if (Ruolo.AMMINISTRATORE.equals(ruolo1)) {
		%>
		
		<li><a
			href="/GestioneScuola_full/LoginServletDB?action=nuovoMagazziniere"
			style="color: #1e90ff;"><strong>NewMagazziniere</strong></a></li>
		<li><a
			href="/GestioneScuola_full/UtenteServletDB?action=ListMagaz"
			style="color: #1e90ff;"><strong>Magazzinieri </strong></a></li>

		<li><a href="/GestioneScuola_full/UtenteServletDB?action=list"
			style="color: #1e90ff;"><strong>Clienti</strong></a></li>

		<li><a
			href="/GestioneScuola_full/UtenteServletDB?action=CreateCF"
			style="color: #1e90ff;"><strong>GeneraSoloC.F.</strong></a></li>

		<li><a
			href="/GestioneScuola_full/UtenteServletDB?action=FormSearch"
			style="color: #1e90ff;"><strong>RicercaTramiteCF</strong></a></li>
		<%
		} else if (Ruolo.MAGAZZINIERE.equals(ruolo1)) {
		%>
		<li><a href="/GestioneScuola_full/ProdottoServlet?action=create"
			style="color: #1e90ff;"><strong>InserisciProdotto</strong></a></li>
		<%
		} else if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo1)) {
		%>

		<li><a
			href="/GestioneScuola_full/OrdineServlet?action=visualizzaOrdini&idUtente=<%=session.getAttribute("idUtente")%>"
			style="color: #1e90ff;"><strong>OrdinieEfettuati</strong></a></li>
		<%
		}
		if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo1) || Ruolo.MAGAZZINIERE.equals(ruolo1)
				|| Ruolo.AMMINISTRATORE.equals(ruolo1)) {
		%>
		<div id="menu">
    <ul>
        <li>
            <a href="#" id="toggleCategories" style="color: #1e90ff;"><strong>Prodotto</strong></a>
            <ul id="categoriesDropdown" style="display: none;">
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Cancelleria" style="color: #1e90ff;"><strong>Cancelleria</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Accessori" style="color: #1e90ff;"><strong>Accessori</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Elettronica" style="color: #1e90ff;"><strong>Elettronica</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Scrittura" style="color: #1e90ff;"><strong>Scrittura</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Giochi" style="color: #1e90ff;"><strong>Giochi</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Orologi" style="color: #1e90ff;"><strong>Orologi</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Arte" style="color: #1e90ff;"><strong>Arte</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Casa" style="color: #1e90ff;"><strong>Casa</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Sport" style="color: #1e90ff;"><strong>Sport</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Cucina" style="color: #1e90ff;"><strong>Cucina</strong></a></li>
                <li><a href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Libri" style="color: #1e90ff;"><strong>Libri</strong></a></li>
            </ul>
        </li>
    </ul>
</div>

<script>
    document.getElementById('toggleCategories').addEventListener('click', function() {
        var dropdown = document.getElementById('categoriesDropdown');
        if (dropdown.style.display === 'none' || dropdown.style.display === '') {
            dropdown.style.display = 'block';
        } else {
            dropdown.style.display = 'none';
        }
    });
</script>
		

		<!-- <li><a href="/GestioneScuola_full/ProdottoServlet?action=list"
			style="color: #1e90ff;"><strong>Prodotti</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Cancelleria"
			style="color: #1e90ff;"><strong>Cancelleria</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Accessori"
			style="color: #1e90ff;"><strong>Accessori</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Elettronica"
			style="color: #1e90ff;"><strong>Elettronica</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Scrittura"
			style="color: #1e90ff;"><strong>Scrittura</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Giochi"
			style="color: #1e90ff;"><strong>Giochi</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Orologi"
			style="color: #1e90ff;"><strong>Orologi</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Arte"
			style="color: #1e90ff;"><strong>Arte</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Casa"
			style="color: #1e90ff;"><strong>Casa</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Sport"
			style="color: #1e90ff;"><strong>Sport</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Cucina"
			style="color: #1e90ff;"><strong>Cucina</strong></a></li>
		<li><a
			href="/GestioneScuola_full/ProdottoServlet?action=list&categoriaProdotto=Libri"
			style="color: #1e90ff;"><strong>Libri</strong></a></li>-->

		<%
		}
		%>


	</ul>
</div>
