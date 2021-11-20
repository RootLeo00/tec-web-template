<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>

<!-- accesso alla sessione -->
<%@ page session="false"%>

<!-- metodi richiamati nel seguito -->
<%! //%>

<!-- codice html restituito al client -->
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<title>Conversione valute</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery-1.12.3.min.js"></script><!-- libreria per le function jquery -->
<%!

String setValoreConvertito(Object val){
	if(val == null || val==""){
		return "";
	}
	else{
		return val.toString();
	}
}


%>

</head>
<body>
	<p>Si relializzi una interfaccia web single page che tramite jsp e
		servlet consenta la conversione di una valuta in un'altra. Per
		semplicita' si considerino solo EURO, DOLLARI (1 euro = 1.15 dollari)
		e STERLINE (1 euro = 0.86 sterline). Si mantenga anche la cronologia
		delle conversioni effettuate dallo stesso utente nella sessione
		attiva.</p>

	<!-- realizzo:
    1) un input text dove l'utente può inserire la valuta originale
    2) un input text (in sola lettura) dove l'utente legge il risultato della conversione
    3) un bottone per azionare la conversione
    4) un menu a tendina a scelta singola per selezionare il tipo di valuta per l'input text
-->
	<form name="richiesta_converti" action="valutaServlet" method="post">
		<label>Valuta da convertire</label> <input type="text" name="valore_originale" value=<%=setValoreConvertito(request.getParameter("valore_originale"))%>> 
		<select name="valuta_originale">
			<option value="EURO">EURO</option>
			<option value="DOLLARI">DOLLARI</option>
			<option value="STERLINE">STERLINE</option>
		</select> <br> <label>Tipo valuta del risultato</label> <input type="text" name="valore_convertito" value='<%=setValoreConvertito(request.getAttribute("valore_convertito"))%>'> 
		<select
			name="valuta_convertita">
			<option value="EURO">EURO</option>
			<option value="DOLLARI">DOLLARI</option>
			<option value="STERLINE">STERLINE</option>
		</select> <input type="submit" name="converti" value="CONVERTI"> <br>
	</form>
	

		<% 	//codice per la gestione del risultato passatoci dalla servlet attraverso una post
        Double valore_convertito_NoCookie = (Double)request.getAttribute("valore_convertito");
		if( valore_convertito_NoCookie != null ){
			// mostro il risultato della ricerca appena effettuata
			%>
    		Risultato:<br />
    		guadagno=<%= valore_convertito_NoCookie %><br />
    		NB: questa jsp riesce a inserire il risultato anche nell'input text
    		<%

		}
		else{
			// mostro il risultato della ricerca effettuata l'ultima volta
			// e memorizzata tramite cookie
			String valore_convertito = null;
	    	Cookie[] cookies = request.getCookies();
	    	if ( cookies != null && cookies.length > 0 ) {
				for ( Cookie cookie : cookies ) {
	        		if ( cookie.getName().equals("valore_convertito") ) {
	        			valore_convertito = cookie.getValue();
	        		}
	    		}
	    	}
	    	if ( valore_convertito!=null ) {
	    		%>
	    		Ricerca precedente:<br/>
	    		valore convertito=<%= valore_convertito %><br />
	    		<%
	    	}
		}
    	%>		


</body>
</html>