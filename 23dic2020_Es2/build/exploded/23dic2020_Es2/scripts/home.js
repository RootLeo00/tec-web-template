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
		console.log(obj)
		var obj = JSON.parse(obj); //ritorna un obj
		var res="";

		// restituzione dell'html da aggiungere alla pagina
		res+= "<strong>Result:</strong><br>";
	for (var i=0; i< obj.result.length;i++){

			res+=obj.result[i];
			res+="<br>";

	}
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
				if(theElement.id=="result"){
					const parsed_response_as_obj = parsifica(theXhr.responseText,theElement.id);
					alert(JSON.stringify(parsed_response_as_obj))
					theElement.innerHTML = parsed_response_as_obj;
				}
				
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
	//theXhr.setRequestHeader("connection", "close"); //no sense
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


function changeLetter(e){
	const n=3;
	let letter=e.key;
	let LETTER=letter.toUpperCase();
//check lettere minuscole
		if(letter==LETTER){//se lettera è maiuscola

			document.getElementById("error").innerHTML="Non hai inserito delle lettere minuscole";
			e.target.value="";
			return;
		}

		var count=0;
	for (var i=1; i<=n;i++){

			var l_i=document.getElementById("l"+i);
			if(l_i.value !=""){
				count++;
			
	}
}
if(count>=2){
	setJson(letter);
}
	//se sono arrivato qui vuol dire che tutte e due le matrici sono state riempite

}

function setJson(key) {


	for(var i=1;i<=3;i++){
		if(document.getElementById("l"+i).value==""){
			document.getElementById("l"+i).value=key;
			break;
		}
	}

	var l1=document.getElementById("l1").value;
	var l2=document.getElementById("l2").value;
	var l3=document.getElementById("l3").value;

	var data={
		l1:l1,
		l2:l2,
		l3:l3
	}
	console.log("data", data)

			//var data_encoded=encodeURIComponent(JSON.stringify(data));
			//method 1 with xml
			requestServerJson('calculateServlet', myGetElementById('result'), JSON.stringify(data));
		
			//method 2 with fetch
			//postData('calculateServlet', myGetElementById('result'), JSON.stringify(data));
	}

// Example POST method implementation:
async function postData(url, elem, data) {
  // Default options are marked with *
  const response = await fetch(url, {
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
   // mode: 'cors', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json'
      // 'Content-Type': 'application/x-www-form-urlencoded', 
    },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: data // body data type must match "Content-Type" header
  });
  //temp=await response.json(); //for debugging
  parsifica(response, elem.id);
}