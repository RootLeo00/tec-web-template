package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import com.google.gson.Gson;

import bean.TourismDB;
import pojo.Attraction;
import pojo.Data;

public class InfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	private TourismDB db;

	@Override
	public void init() {
		db=new TourismDB();
		gson = new Gson();	
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		Data data = gson.fromJson(request.getReader(), Data.class);
		//Così prendo il valore del Json ricevuto e lo salvo in una variabile di tipo ValoriJson (che all'interno contiene solamente
		// i due parametri spediti, ovvero l'operazione e il valore
		Attraction res=null;

		if(data.getX()<0 || data.getY()<0){			//suppongo solo coordinate positive
			response.getWriter().println(gson.toJson("error"));
		}
		else {
			res=db.findAttractionByCoord(data.getX(), data.getY());
			if(res!=null)
				response.getWriter().println(gson.toJson(res));
			else
				response.getWriter().println(gson.toJson("error"));
		}


	}

}
