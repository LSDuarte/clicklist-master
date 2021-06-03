package com.clicklist.util;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtilRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public static <E> void imprimirRelatorio(String relatorioNome, Map<String, Object> parametros, List<?> lista) {

		try {
			
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();
			ServletContext scContext = (ServletContext) facesContext.getExternalContext().getContext();
			
			String path = scContext.getRealPath("/WEB-INF/classes/jasper");
			System.out.println(path);
			parametros.put("SUBREPORT_DIR", path + File.separator);
			System.out.println(path + File.separator + relatorioNome + ".jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + relatorioNome + ".jasper", parametros, datasource);
			byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
			
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			
			int codigo = (int) (Math.random() * 1000);
			response.setHeader("Content-disposition", "inline; filename=relatorio_" + codigo + ".pdf");
			
			response.getOutputStream().write(b);
			
			facesContext.responseComplete();

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao Emitir Relatório!" + relatorioNome);
			e.printStackTrace();
		}
		
	}

}