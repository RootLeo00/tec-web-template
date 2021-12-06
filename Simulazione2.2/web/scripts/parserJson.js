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
   
if(id=="result"){//parsificaJson
	
	var obj = JSON.parse(obj);//ritorna un array
	var res="";
	     // restituzione dell'html da aggiungere alla pagina
res+= "Attraction name:<strong>"+ obj.name + " </strong><br>";
res+="General description of the place "+obj.description + " <br>";
res+="Coordinates: ("+obj.coordinate[0]+","+obj.coordinate[1]+")";
return res;
}

}// parsificaXml()

/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {
	

	// designiamo la formattazione della zona dell'output
	//theElement.class = "content";
	
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
			if ( theXhr.responseText ) {
				if(theElement.id=="result")
				theElement.innerHTML = parsifica(theXhr.responseText,theElement.id);
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
function requestIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function requestAjax(theUri, theElement, theXhr, data) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", theUri, true);
		//theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");
	theXhr.setRequestHeader("Content-type", "application/json");

	// invio richiesta
	theXhr.send(data);
	//theXhr.send("categoria=" + theElement);

} // caricaFeedAJAX()



/*
 * Scarica un contenuto xml dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function requestServerJson(uri,e, data) {

//uri=decodeURIComponent(uri);
	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		requestAjax(uri,e,xhr, data); 
	else 
		requestIframe(uri,e); 

}// caricaFeed()
