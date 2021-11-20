package it.unibo.tw.web.servlets;

import java.io.IOException;
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


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.	Si decodifica la stringa JSON e la si trasforma in oggetto Java utilizzando un apposito parser
		//Inizializzazione dell’oggetto Gson: 
		Gson g= new Gson();
		//deserializzazione di un oggetto
		Feed f=g.fromJson(req.getParameter("feed"),Feed.class);
		resp.getWriter().write("Ecco il risultato");
		
		if(f==null) {
			resp.getWriter().write("non è possibile calcolare il feed");

		}
		else {
			FeedDb feedDb=new FeedDb();
			for(Feed feed: feedDb.getFeeds())	{
				if(feed.getCategory().equalsIgnoreCase(f.getCategory())) {
					// inserisco il risultato nella risposta
					f.setTitle(feed.getTitle());
					f.setAuthor(feed.getAuthor());
					f.setDescription(feed.getDescription());
					f.setCategory(feed.getCategory());
					f.setLink(feed.getLink());
					f.setPubDate(feed.getPubDate());
					f.setImageUrl(feed.getImageUrl());

					//serializzazione di un oggetto :
					//g.toJson(f);
					
					// passo la risposta
					resp.getWriter().write(g.toJson(f));
				}
			}

		}
	}

}
