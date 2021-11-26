/*
 * Funzione di callback
 */
function requestCalculate_callback( theXhr, callback ) {

	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
    	// non faccio niente
    	// theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	// non faccio niente
		// theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {

		// verifica della risposta da parte del server
	        if ( theXhr.status === 200 ) {

	        	// operazione avvenuta con successo
	
		        if ( theXhr.responseText && theXhr.responseText !== "" ) {
				        callback(JSON.parse(theXhr.responseText));
				}
	
				else {
			    	// non faccio niente
				}

	        }

	        else {
	        	// errore di caricamento
	        	// non faccio niente nemmeno qui
	        }

	}// if 4

} // categoriaCallback();



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX; per browser legacy
 */
function requestCalculateIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsarlo! è il browser che renderizza l'src dell'iframe!
}// requestCalculateIframe()



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function requestCalculateAJAX(theUri,theData, theXhr, callback) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { requestCalculate_callback(theXhr, callback); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("POST", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	 // Set the request header i.e. which type of content you are sending
	theXhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	// invio richiesta
	theXhr.send(theData);

} // requestCalculateAJAX()



/*
 * Scarica un contenuto testuale dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function requestCalculate(uri,data,callback) { //passo l'uri della servlet e la set state

console.log(uri);//for easier debug
console.log(data);//for easier debug
	// assegnazione oggetto XMLHttpRequest
	var xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		requestCalculateAJAX(uri,data,xhr, callback); 
	else 
		requestCalculateIframe(uri,callback); 

}// caricaFeed()

// Example GET method implementation:
async function postData(url ,callback) {
  // Default options are marked with *
  const response = await fetch(url, {
    method: 'GET', // *GET, POST, PUT, DELETE, etc.
   // mode: 'cors', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
      // 'Content-Type': 'application/x-www-form-urlencoded', 
    },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
  });
  callback(response.json()); // parses JSON response into native JavaScript objects
}
