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
	
	@Override
public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		//deserializzazione di un oggetto
		Data data=gson.fromJson(request.getParameter("data"),Data.class);
//		String data.getOp()=gson.fromJson(request.getParameter("data"),String.class);
		
//		String number_string=request.getParameter("x").trim();
//		String data.getOp()=request.getParameter("data.getOp()").trim();
//		
//		//check for null data.getOp()erator
//		if(number_string==null) response.getWriter().println("error");
//		if(data.getOp()==null) response.getWriter().println("error");
//		
//		//parse the number (check for invalid number)
//		double x=Integer.parseInt(number_string);
//		
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
