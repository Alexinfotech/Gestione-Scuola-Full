<%@page import="it.molinari.model.RuoloDTO"%>
<%@page import="it.molinari.service.Ruolo"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
String ruolo1 = (String) request.getSession().getAttribute("ruolo");
%>

<div id="menu">
    <ul>
    <%
        if (Ruolo.AMMINISTRATORE.equals(ruolo1)){
        %>
        <li><a href="/GestioneScuola_full/UtenteServletDB?action=create"
               style="color: #1e90ff;"><strong>NuovoUtente</strong></a></li>

        <li><a href="/GestioneScuola_full/UtenteServletDB?action=list"
               style="color: #1e90ff;"><strong>Utenti</strong></a></li>

        <li><a href="/GestioneScuola_full/UtenteServletDB?action=CreateCF"
               style="color: #1e90ff;"><strong>GeneraSoloC.F.</strong></a></li>

        <li><a href="/GestioneScuola_full/UtenteServletDB?action=FormSearch"
               style="color: #1e90ff;"><strong>RicercaTramiteCF</strong></a></li>
        <%
        } else if (Ruolo.MAGAZZINIERE.equals(ruolo1)){
        %>
            <li><a href="/GestioneScuola_full/ProdottoServlet?action=create"
                   style="color: #1e90ff;"><strong>InserisciProdotto</strong></a></li>
        <%
        }
        if (Ruolo.UTENTE_NAVIGATORE.equals(ruolo1) || Ruolo.MAGAZZINIERE.equals(ruolo1) || Ruolo.AMMINISTRATORE.equals(ruolo1)){
        %>
            <li><a href="/GestioneScuola_full/ProdottoServlet?action=list"
                   style="color: #1e90ff;"><strong>Prodotti</strong></a></li>
        <% } %>

        <li><a href="#" style="color: #1e90ff;">GestioneCategorie*</a></li>
        <li><a href="#" style="color: #1e90ff;">Statistiche*</a></li>
        <li><a href="#" style="color: #1e90ff;">Impostazioni*</a></li>
    </ul>
</div>
