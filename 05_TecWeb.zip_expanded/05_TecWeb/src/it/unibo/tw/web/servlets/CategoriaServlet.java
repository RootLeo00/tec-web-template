package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Feed;
import it.unibo.tw.web.beans.FeedDb;

//@WebServlet("/categoryServlet/*")
public class CategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;

	@Override
	public void init() throws ServletException {
		super.init();
		//Inizializzazione dell’oggetto Gson: 
		gson= new Gson();
		
	}

	//uso una service() perchè non so se arriverà una post oppure una get
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.	Si decodifica la stringa JSON e la si trasforma in oggetto Java utilizzando un apposito parser
				//deserializzazione di un oggetto
				Feed f=gson.fromJson(req.getParameter("feed"),Feed.class);
				List<Feed> feeds=new ArrayList<Feed>();
				if(f==null) {
					return;
				}
				else {
					FeedDb feedDb=new FeedDb();
					for(Feed feed: feedDb.getFeeds())	{
						if(feed.getCategory().equalsIgnoreCase(f.getCategory())) 	
							feeds.add(feed);// inserisco il risultato nella risposta	
					}
					// passo la risposta
					//serializzo l'oggetto con gson
					resp.getWriter().println(gson.toJson(f));
				}
	}


}
