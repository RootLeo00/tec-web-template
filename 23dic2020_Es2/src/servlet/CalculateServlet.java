package servlet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import pojo.Data;
import pojo.FileServlet;
import pojo.Result;
//l’applicazione deve chiedere al servitore quali parole contengono ALMENO due delle tre lettere inserite.
public class CalculateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int n;
	private Gson gson;
	private FileServlet file;
	private int k;
	private final static int PRIMA_PARTE=1;
	private final static int SECONDA_PARTE=2;
	
	
	@Override
	public void init() {
		gson = new Gson();
		file=new FileServlet("dizionario.txt");
		k=3;//da cambiare (con k parametro di configurazione specificato in web.xml)
	}
	
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
			 Data data = gson.fromJson(request.getReader(), Data.class);
			 
			 if(file.getNumRighe()<k) {
				 single(data,response);
			 }
			 else {
				 multi(data,response);
			 }
			 
	
	}
	
	private void single(Data data,ServletResponse response) throws IOException {
		List<String> result = new ArrayList<String>();
		result=this.file.getWords(data.getL1(), data.getL2(), data.getL3());
		response.getWriter().println(gson.toJson(result));
	}
	
	private void multi(Data data,ServletResponse response) throws IOException {
		 
		List<String> result = new ArrayList<String>();
		 Result res=new Result(result);
		 ThreadServlet t1=new ThreadServlet(this.file.getHalf(PRIMA_PARTE),data.getL1(),data.getL2(),data.getL3(), res);
		 ThreadServlet t2=new ThreadServlet(this.file.getHalf(SECONDA_PARTE),data.getL1(),data.getL2(),data.getL3(), res);
		 
		 t1.run();
		 t2.run();
		 
		 try {
			t1.join();
			t2.join();
		 } catch (InterruptedException e) {
			e.printStackTrace();
		}
		 			 
		 response.getWriter().println(gson.toJson(res));
	}
}
