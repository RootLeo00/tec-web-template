package it.unibo.tw.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import it.unibo.tw.web.pojo.Data;

public class CalculateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();	
	}
	
	public void doPost(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
//		 // Read payload from request (smt goes wrong)
//	    StringBuilder buffer = new StringBuilder();
//	    BufferedReader reader = request.getReader();
//	    String line;
//	    while ((line = reader.readLine()) != null) {
//	        buffer.append(line);
//	        buffer.append(System.lineSeparator());
//	    }
//	    String data_string = buffer.toString();

		//deserializzazione di un oggetto
		Data data=gson.fromJson(request.getParameter("body"),Data.class);
		Double res=null;
		
		if(data.getOp().equals("ln")) {
			res= Math.log(data.getX());
		}
		if(data.getOp().equals("sqrt")) {
			res= Math.sqrt(data.getX());
		}
		if(data.getOp().equals("exp")) {
			res= Math.exp(data.getX());
		}
		if(data.getOp().equals("1/x")) {
			res= (1/data.getX());
		}
		
		data.setRes(res);
		
		//return result back to the client
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(gson.toJson(data));

	}
}
