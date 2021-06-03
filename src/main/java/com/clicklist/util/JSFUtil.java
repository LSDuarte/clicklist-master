package com.clicklist.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void adicionarMensagemSucesso(String mensagem, String tittleMessage) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, tittleMessage);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}
	
	public static void adicionarMensagemWarn(String mensagem, String tittleMessage) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, tittleMessage);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public static void adicionarMensagemErro(String mensagem, String tittleMessage) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, tittleMessage);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

}