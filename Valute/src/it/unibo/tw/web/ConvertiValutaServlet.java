package it.unibo.tw.web;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConvertiValutaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// retrieve former values, if any
		//valuta_originale= è il tipo di valuta originale (euro,dollaro,sterline)
		//valore_originale o valore_convertito= esprime la quantità di [euro|dollaro|sterline]
		//valuta_convertita= è il tipo di valuta in output (euro,dollaro,sterline)
		String valuta_originale = request.getParameter("valuta_originale");
		if ( valuta_originale == null ) valuta_originale = "valuta anonima";

		double valore_originale = Integer.parseInt(request.getParameter("valore_originale"));

		String valuta_convertita = request.getParameter("valuta_convertita");
		if ( valuta_convertita == null) 
			valuta_convertita = "valuta anonima";
		double valore_convertito=-1;

		//calcolo il risultato
		if(valuta_originale.equals("EURO")) {
			if(valuta_convertita.equals("DOLLARI")) valore_convertito=valore_originale*1.15;
			if(valuta_convertita.equals("STERLINE")) valore_convertito=valore_originale*0.86;
			if(valuta_convertita.equals("EURO")) valore_convertito=valore_originale;

		}
		if(valuta_originale.equals("DOLLARI")) {
			if(valuta_convertita.equals("EURO")) valore_convertito=valore_originale/1.15;
			if(valuta_convertita.equals("STERLINE")) valore_convertito=valore_originale*0.74;//fonte google
			if(valuta_convertita.equals("DOLLARI")) valore_convertito=valore_originale;

		}
		if(valuta_originale.equals("STERLINE")) {
			if(valuta_convertita.equals("EURO")) valore_convertito=valore_originale/0.86;
			if(valuta_convertita.equals("DOLLARI")) valore_convertito=valore_originale/0.74;//fonte google
			if(valuta_convertita.equals("STERLINE")) valore_convertito=valore_originale;		
		}

		Cookie c = new Cookie("valore_convertito", ""+valore_convertito);
		c.setMaxAge(60*60); // 1 ora
		response.addCookie(c);

		// inserisco il risultato nella richiesta che viene passata alla JSP	
		if(valuta_originale.equals("valuta anonima")||valuta_convertita.equals("valuta anonima")) 
			request.setAttribute("valore_convertito", "error");
		else
			request.setAttribute("valore_convertito", valore_convertito);

		// passo il controllo alla JSP
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/valute.jsp");
		dispatcher.forward(request, response);

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
