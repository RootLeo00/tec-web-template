/*
 * Funzione per estrarre il contenuto CDATA presente
 * all'interno di un nodo XML 
 * [ad esempio su <guida>ajax</guida> restituisce solo ajax].
 *
 * Utile a far rimanere leggibile il codice di parsificaXml() 
 */
function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};

/*
 * Funzione che genera una lista XHTML 
 * con gli item presi dal testo RSS (linguaggio basato su xml)
 * ricevuto come argomento xml
 */
function parsifica( obj ,id) {
   
if(id=="category"){//parsificaXml
	var	items = obj.getElementsByTagName("item");
	return leggiContenuto(items[0],"cat");
	
}
if(id=="feed"){//parsificaJson
	
	var obj = JSON.parse(obj);
	     // restituzione dell'html da aggiungere alla pagina
return document.getElementById("feed").innerHTML = obj.title + "\n" + obj.author+obj.description + "\n" + obj.category
											+obj.link + "\n" + obj.pubDate+"\n"+
											"<img src="+obj.imageUrl+ "width="+500+" height="+600+">";
}

}// parsificaXml()

/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {
	

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";
	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    	theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    		theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			if ( theXhr.responseXML ) {
				if(theElement.id=="category")
				theElement.value = parsifica(theXhr.responseXML,theElement.id);
				if(theElement.id=="feed")
				theElement.innerHTML = parsifica(theXhr.responseXML,theElement.id);
			}// if XML
			else {
				// visualizzazione contenuto letto
				// evitando di scrivere la risposta 
				// in modo interpretabile dal browser
				theElement.innerHTML = "L'XML restituito dalla richiesta non e' valido.<br />" +
				theXhr.responseText.split('<').join("&lt;").split('>').join("&gt;");
			}// else (if ! XML)
		}// if 200

		else {
			// errore di caricamento
			theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function caricaFeedIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaFeedAJAXJson(theUri, theElement, theXhr) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		xmlhttp.open("get", theUri, true);
		//theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");
	theXhr.setRequestHeader("Content-type", "application/xml");

	// invio richiesta
	theXhr.send(null);
	//theXhr.send("categoria=" + theElement);

} // caricaFeedAJAX()



/*
 * Scarica un contenuto xml dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function caricaFeedJson(uri,e) {

//uri=decodeURIComponent(uri);
	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		caricaFeedAJAXJson(uri,e,xhr); 
	else 
		caricaFeedIframe(uri,e); 

}// caricaFeed()
