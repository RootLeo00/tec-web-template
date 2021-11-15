package it.unibo.tw.web;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConvertiValutaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// retrieve former values, if any
		//valuta_originale= è il tipo di valuta originale (euro,dollaro,sterline)
		//valore_originale= esprime la quantità di [euro|dollaro|sterline]
		//valuta_convertita= è il tipo di valuta in output (euro,dollaro,sterline)
		String valuta_originale = request.getParameter("valuta_originale");
		if ( valuta_originale == null ) valuta_originale = "valuta anonima";

		double valore_originale = Integer.parseInt(request.getParameter("valore_originale"));

		String valuta_convertita = request.getParameter("valuta_convertita");
		if ( valuta_convertita == null) 
			valuta_convertita = "valuta anonima";
		double valore_convertito=-1;

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Conversione servlet</title>");
		out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
		out.println("</head>");
		out.println("<body>");
		out.println("This is the servlet output.<br/><br/>");

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
		
		if(valuta_originale.equals("valuta anonima")||valuta_convertita.equals("valuta anonima")) 
			out.println("<p>Non è stato possibile calcolare il risultato</p>");

		else
			out.println("<p> Risultato:  "+ valore_convertito+ "</p>");

		//aggiungo bottone per tornare alla pagina iniziale
		out.println("<form name=\"statisticheForm\" action=/Valute method=\"post\">");
		out.println("<input type=submit name=return value=\"RETURN HOME\">");
		out.println("</form>");


		out.println("</body>");
		out.println("</html>");

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
