var REQUEST_AUTOCOMPLETE_AJAX_UTILS = null;
var ID_OBJ_AUTOCOMPLETE_AJAX_UTILS = null;

function AjaxAction(){
	this.req = new Object();
	this.queryString = null;
	this.method = 'POST';
	this.idObjetoAutoComplete = '';
	
	this.req = AjaxUtils.defineBrowserAJAX();
	//
	this.submitForm = function(theForm, action, fCallBack){
		this.queryString = AjaxUtils.generateQueryStringByForm(theForm);
		if (this.queryString.length>0) {
			this.queryString += "&";
		}
		this.queryString += "nocache=" + new Date();
		this.req.onreadystatechange = fCallBack;
		try{
			this.req.open("POST", action, true);
      		this.req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      		this.req.setRequestHeader("encoding", "UTF-8"); 
      		this.req.setRequestHeader("charset", "UTF-8"); 
      		this.req.setRequestHeader("Content-length", this.queryString.length);
      		this.req.setRequestHeader("Connection", "close");
			this.req.setRequestHeader('Cache-Control', 'no-store, no-cache, must-revalidate');
			this.req.setRequestHeader('Cache-Control', 'post-check=0, pre-check=0');
			this.req.setRequestHeader('Pragma', 'no-cache');
		}catch(e){
			alert(e);
		}
		this.req.send(this.queryString);	
	};
	//
	this.submitSubForm = function(theForm, nameSubForm, action, fCallBack){
		//Em construcao
	};
	//
	this.submitObject = function(obj, action, fCallBack){
		this.queryString = '';
		for (var property in obj) {
		  	this.queryString += encodeURIComponent(property) + "=" + encodeURIComponent(AjaxUtils.converteCaracteresEspeciais(obj[property]));
			if (this.queryString.length>0) {
				this.queryString += "&";
			}		  	
	    }	
		this.queryString += "nocache=" + new Date();
		this.req.onreadystatechange = fCallBack;
		try{
			this.req.open("POST", action, true);
      		this.req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      		this.req.setRequestHeader("encoding", "UTF-8"); 
      		this.req.setRequestHeader("charset", "UTF-8"); 
      		this.req.setRequestHeader("Content-length", this.queryString.length);
      		this.req.setRequestHeader("Connection", "close");
			this.req.setRequestHeader('Cache-Control', 'no-store, no-cache, must-revalidate');
			this.req.setRequestHeader('Cache-Control', 'post-check=0, pre-check=0');
			this.req.setRequestHeader('Pragma', 'no-cache');
		}catch(e){
			alert(e);
		}
		this.req.send(this.queryString);	
	};
	//
	this.submitObjectMethodGET = function(obj, action, fCallBack){
		this.queryString = '';
		for (var property in obj) {
		  	this.queryString += encodeURIComponent(property) + "=" + encodeURIComponent(AjaxUtils.converteCaracteresEspeciais(obj[property]));
			if (this.queryString.length>0) {
				this.queryString += "&";
			}		  	
	    }	
		if (this.queryString.length>0) {
			this.queryString += "&";
		}
		this.queryString += "nocache=" + new Date();
		this.req.onreadystatechange = fCallBack;
		try{
			this.req.open("GET", action + '?' + this.queryString, true);
      		this.req.setRequestHeader("encoding", "UTF-8"); 
      		this.req.setRequestHeader("charset", "UTF-8"); 			
			this.req.setRequestHeader('Cache-Control', 'no-store, no-cache, must-revalidate');
			this.req.setRequestHeader('Cache-Control', 'post-check=0, pre-check=0');
			this.req.setRequestHeader('Pragma', 'no-cache');
		}catch(e){
			alert(e);
		}
		this.req.send(null);
	};
	//
	this.sendAutoComplete = function(strObjectProcess, strEnviar, strMensagem, classAutoComplete){
		REQUEST_AUTOCOMPLETE_AJAX_UTILS = this.req;
		ID_OBJ_AUTOCOMPLETE_AJAX_UTILS = this.idObjetoAutoComplete;
		this.submitObjectMethodGET({parm1:strEnviar,parm2:strMensagem,parm3:classAutoComplete}, strObjectProcess + '.complete', this.callBackAutoComplete);
	};
	//
	this.callBackAutoComplete = function(){
		if (REQUEST_AUTOCOMPLETE_AJAX_UTILS.readyState == 4){
			if (REQUEST_AUTOCOMPLETE_AJAX_UTILS.status == 200){
				document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).innerHTML = REQUEST_AUTOCOMPLETE_AJAX_UTILS.responseText;
				
				if(document.all) { // Internet Explorer
					var divAux = document.getElementById('DIV_AUX_' + ID_OBJ_AUTOCOMPLETE_AJAX_UTILS);
					if (divAux != null && divAux != undefined){
						divAux.style.height = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).offsetHeight;
						divAux.style.width = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).offsetWidth;
						divAux.style.top = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).style.top;
						divAux.style.left = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).style.left;
					
						var fraAux = document.getElementById('FRA_AUX_' + ID_OBJ_AUTOCOMPLETE_AJAX_UTILS);
						fraAux.style.height = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).offsetHeight;
						fraAux.style.width = document.getElementById(ID_OBJ_AUTOCOMPLETE_AJAX_UTILS).offsetWidth;	
						
						divAux.style.display='block';			
					}
				}
			}
		}
	};	
}

function AjaxUtils(){}

AjaxUtils.converteCaracteresEspeciais = function(str){
	var encoded = '';
	var strFinal = '';
	for (var i = 0; i < str.length; i++){
		encoded = '';
		c = str.charAt(i);
		if (c == 'ç')
			encoded = "[[[cedilhamin]]]";
		else if (c == 'Ç')
			encoded = "[[[cedilhamai]]]";
		else if (c == 'á')
			encoded = "[[[aagudomin]]]";
		else if (c == 'Á')
			encoded = "[[[aagudomai]]]";
		else if (c == 'é')
			encoded = "[[[eagudomin]]]";
		else if (c == 'É')
			encoded = "[[[eagudomai]]]";
		else if (c == 'í')
			encoded = "[[[iagudomin]]]";
		else if (c == 'Í')
			encoded = "[[[iagudomai]]]";
		else if (c == 'ó')
			encoded = "[[[oagudomin]]]";
		else if (c == 'Ó')
			encoded = "[[[oagudomai]]]";
		else if (c == 'ú')
			encoded = "[[[uagudomin]]]";
		else if (c == 'Ú')
			encoded = "[[[uagudomai]]]";
		else if (c == 'â')
			encoded = "[[[acircmin]]]";
		else if (c == 'Â')
			encoded = "[[[acircmai]]]";
		else if (c == 'ê')
			encoded = "[[[ecircmin]]]";
		else if (c == 'Ê')
			encoded = "[[[ecircmai]]]";
		else if (c == 'î')
			encoded = "[[[icircmin]]]";
		else if (c == 'Î')
			encoded = "[[[icircmai]]]";
		else if (c == 'ô')
			encoded = "[[[ocircmin]]]";
		else if (c == 'Ô')
			encoded = "[[[ocircmai]]]";
		else if (c == 'û')
			encoded = "[[[ucircmin]]]";
		else if (c == 'Û')
			encoded = "[[[ucircmai]]]";
		else if (c == 'ã')
			encoded = "[[[atilmin]]]";
		else if (c == 'Ã')
			encoded = "[[[atilmai]]]";
		else if (c == 'õ')
			encoded = "[[[otilmin]]]";
		else if (c == 'Õ') 
			encoded = "[[[otilmai]]]";
		else 
			encoded = c;
			
		strFinal += encoded;
	}	
	
	return strFinal;
}

AjaxUtils.defineBrowserAJAX = function(){
	  var http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
         	//set type accordingly to anticipated content type
            //http_request.overrideMimeType('text/xml');
            http_request.overrideMimeType('text/html');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance #####');
         return false;
      }
	  return http_request;
};

AjaxUtils.generateQueryStringByForm = function(theform) {
	var els = theform.elements;
	var len = els.length;
	var queryString = "";
	this.addField = 
		function(name,value) { 
			if (queryString.length>0) { 
				queryString += "&";
			}
			queryString += encodeURIComponent(name) + "=" + encodeURIComponent(AjaxUtils.converteCaracteresEspeciais(value));
		};
	for (var i=0; i<len; i++) {
		var el = els[i];
		if (!el.disabled) {
			switch(el.type) {
				case 'text': case 'password': case 'hidden': case 'textarea': 
					this.addField(el.name,el.value);
					break;
				case 'select-one':
					if (el.selectedIndex>=0) {
						this.addField(el.name,el.options[el.selectedIndex].value);
					}
					break;
				case 'select-multiple':
					for (var j=0; j<el.options.length; j++) {
						if (el.options[j].selected) {
							this.addField(el.name,el.options[j].value);
						}
					}
					break;
				case 'checkbox': case 'radio':
					if (el.checked) {
						this.addField(el.name,el.value);
					}
					break;
			}
		}
	}
	return queryString;
};
/**
  Faz o autocomplete para um determinado campo
     Exemplo de Utilizacao: 
     	Acrescente no onkeydown do seu input text
     		onKeydown="AjaxUtils.autoComplete(this, event, 'AUTOCOMPLETE', 'AutoCompleteNomePaciente')"
     			onde:
     				'AUTOCOMPLETE' é a div que vai apresentar o resultado do autocomplete
     				'AutoCompleteNomePaciente' é o nome da classe no servidor que executará a acao de pesquisa.
     			obs.: Nao esqueca de colocar o 'Id' para o input text
*/
AjaxUtils.autoComplete = function(obj, evtKeyDown, idDivRetorno, nomeClasseAutoComplete, mensagem, classAutoComplete){
	objPos = HTMLUtils.getPosElement(obj.id);
	document.getElementById(idDivRetorno).style.left = objPos.x;
	document.getElementById(idDivRetorno).style.top = objPos.y + document.getElementById(obj.id).offsetHeight;
	
	var nTecla;
	var valor = obj.value;
	if(document.all) { // Internet Explorer
		nTecla = evtKeyDown.keyCode; 
	}else { // Nestcape, Mozilla
		nTecla = evtKeyDown.which;
	}	
	if (nTecla == 8) { // backspace
		if (valor.length > 0){
			if (valor.length == 1){
				valor = '';
			}else{
				valor = valor.substr(0,valor.length-1);
			}
		}
	}else{
		if (nTecla != undefined) { 
			valor = valor + String.fromCharCode(nTecla);
		}
	}
	if (valor == '' || valor == ' ' || valor == null || valor == undefined || nTecla == 9){
		document.getElementById(idDivRetorno).innerHTML = '';
		document.getElementById(idDivRetorno).style.display='none';	
		document.getElementById('DIV_AUX_' + idDivRetorno).style.display='none';	
		return;
	}
	var ajaxAction = new AjaxAction();
	ajaxAction.idObjetoAutoComplete = idDivRetorno;
	ajaxAction.sendAutoComplete(nomeClasseAutoComplete, valor, mensagem, classAutoComplete);
	document.getElementById(idDivRetorno).style.display='block';
};

AjaxUtils.fecharAutoComplete = function (idDivRetorno){
	document.getElementById(idDivRetorno).style.display='none';
	var divAux = document.getElementById('DIV_AUX_' + idDivRetorno);
	divAux.style.display='none';
};
