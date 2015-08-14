package br.com.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	public static void adicionarMsgSuccess(String mensagem){
		//jsf só reconhece Facemessage entoa temos q usar ela
		//temos 3 tipos de msg o info, error e fatal, vm usar so 2 tipos
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		
		//acessando o contexto da aplicação (area da memoria)
		//primeiro capturamos o context e armazenamos em uma var
		FacesContext contexto = FacesContext.getCurrentInstance();
		//adicionando o facesmsg no contexto
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMsgError(String mensagem){
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
				
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

}
