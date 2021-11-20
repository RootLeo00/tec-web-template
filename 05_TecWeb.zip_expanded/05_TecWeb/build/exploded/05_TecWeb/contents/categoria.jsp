<?xml version='1.0' encoding='ISO-8859-1'?>

<!-- 
NOTA BENE!!!  
Il PROLOGO XML deve SEMPRE essere collocato nella PRIMA RIGA!

Nessun carattere in output fino a questo momento!! 

Usero' ora l'out della jsp per restituire l'xml che ajax si attende!!!!

OSSERVATE IL TRAFFICO HTTP NEL TUNNEL!!!!!

-->

<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="false"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Feed"%>
<%@ page import="it.unibo.tw.web.beans.FeedDb"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- librerie di terze parti -->
<!-- n.d. -->

<!-- creazione Java Bean con scope di applicazione -->
<jsp:useBean id="feedDb" class="it.unibo.tw.web.beans.FeedDb"
	scope="application" />

<%

// QUESTO E' IMPORTANTISSIMO AFFINCHE' L'INTERPRETE JAVASCRIPT RICONOSCA IL CONTENUTO COME XML!!!!!!
response.setHeader("Content-Type","application/xml");

%>
<!-- segue quindi il codice.... questa volta NON HTML!!!!! ... restituito al client -->

<rss version='2.0'> 
<%
// recupero la iniziale cercata dai parametri della richiesta
String categoryStartingWith = request.getParameter("category");
// stampo la categoria ottenuta nell'input text
%> 
<item> 
<cat><![CDATA[<%=feedDb.getCategories(categoryStartingWith).get(0)%>]]></cat>
</item> 
</rss>
