package com.clicklist.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@RequestScoped
public class DashboardBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Dashboard dashboard;
	
	private LineChartModel produto;

	
	@PostConstruct
	public void init() {
		
		createProdutoModels();
	}
	
	
	private void createProdutoModels() {
		produto = initLinearModel();
		produto.setTitle("Produtos");
		produto.setAnimate(true);
		produto.setLegendPosition("s");
        Axis yAxis = produto.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(15);
         
    }
	
	private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries produto1 = new LineChartSeries();
        produto1.setLabel("Produto 1");
 
        produto1.set(1, 5);
        produto1.set(2, 10);
        produto1.set(3, 4);
        produto1.set(4, 11);
        produto1.set(5, 6);
 
        LineChartSeries produto2 = new LineChartSeries();
        produto2.setLabel("Produto 2");
 
        produto2.set(1, 4);
        produto2.set(2, 3);
        produto2.set(3, 6);
        produto2.set(4, 8);
        produto2.set(5, 1);
 
        model.addSeries(produto1);
        model.addSeries(produto2);
         
        return model;
    }
	

	public LineChartModel getProduto() {
		return produto;
	}

	public void setProduto(LineChartModel produto) {
		this.produto = produto;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

}