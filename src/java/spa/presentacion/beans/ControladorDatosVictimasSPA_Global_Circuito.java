/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spa.presentacion.beans;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import recursos.CircuitoSubcircuitoUrbano;
import recursos.Util;
import spa.logica.clases.Global;
import spa.logica.funciones.FGlobal;

/**
 *
 * @author Cristhian Moreno
 */
@ManagedBean
@RequestScoped
public class ControladorDatosVictimasSPA_Global_Circuito {

    private ArrayList<Global> lstDatos;
    private CartesianChartModel lineSPACircuitos;
    private ArrayList<Global> lstDatosSF;
    private ArrayList<Global> lstCircuito;
    private ArrayList<Global> lstCircuito2013;
    private ArrayList<Global> lstCircuito2014;
    private Global datoSel;
    private PieChartModel pieCircuito;
    private int anioSel;
    private ArrayList<String> lstCircuitos;

    public CartesianChartModel getLineSPACircuitos() {
        return lineSPACircuitos;
    }

    public void setLineSPACircuitos(CartesianChartModel lineSPACircuitos) {
        this.lineSPACircuitos = lineSPACircuitos;
    }
    

    public ArrayList<String> getLstCircuitos() {
        return lstCircuitos;
    }

    public void setLstCircuitos(ArrayList<String> lstCircuitos) {
        this.lstCircuitos = lstCircuitos;
    }

    public int getAnioSel() {
        return anioSel;
    }

    public void setAnioSel(int anioSel) {
        this.anioSel = anioSel;
    }

    public ArrayList<Global> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(ArrayList<Global> lstDatos) {
        this.lstDatos = lstDatos;
    }

   
    public ArrayList<Global> getLstDatosSF() {
        return lstDatosSF;
    }

    public void setLstDatosSF(ArrayList<Global> lstDatosSF) {
        this.lstDatosSF = lstDatosSF;
    }

    public ArrayList<Global> getLstCircuito() {
        return lstCircuito;
    }

    public void setLstCircuito(ArrayList<Global> lstCircuito) {
        this.lstCircuito = lstCircuito;
    }

    public ArrayList<Global> getLstCircuito2013() {
        return lstCircuito2013;
    }

    public void setLstCircuito2013(ArrayList<Global> lstCircuito2013) {
        this.lstCircuito2013 = lstCircuito2013;
    }

    public ArrayList<Global> getLstCircuito2014() {
        return lstCircuito2014;
    }

    public void setLstCircuito2014(ArrayList<Global> lstCircuito2014) {
        this.lstCircuito2014 = lstCircuito2014;
    }

    public Global getDatoSel() {
        return datoSel;
    }

    public void setDatoSel(Global datoSel) {
        this.datoSel = datoSel;
    }

    public PieChartModel getPieCircuito() {
        return pieCircuito;
    }

    public void setPieCircuito(PieChartModel pieCircuito) {
        this.pieCircuito = pieCircuito;
    }

    @PostConstruct
    
    public void init() {
        this.lineSPACircuitos = graficaCircuitos(anioSel);
    }

    private void reinit() {
        this.init();
    }

    public ControladorDatosVictimasSPA_Global_Circuito() {
        this.reinit();
    }

    private CartesianChartModel graficaCircuitos(int anio) {
        CartesianChartModel model1 = new CartesianChartModel();
        try {
            this.lstCircuitos = CircuitoSubcircuitoUrbano.obtenerCircuitoUrbano();
            ChartSeries anios = new ChartSeries();
            for (int i = 0; i < lstCircuitos.size(); i++) {
                lstCircuito2013 = FGlobal.ObtenerDatosDadoAnioDadoCircuito(anio, lstCircuitos.get(i));
                System.out.println(lstCircuitos.get(i) + "Dimension: " + lstCircuito2013.size());
                anios.set(lstCircuitos.get(i), lstCircuito2013.size());
            }

            model1.addSeries(anios);
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error");
        }
        return model1;
    }

   /* private CartesianChartModel graficaCircuitosUrbanos() {
        CartesianChartModel model = new CartesianChartModel();
        try {
            this.lstDatos = FGlobal.ObtenerDatosDadoAnioCircuito(anioSel);
            ChartSeries anio13 = new ChartSeries();
//            anio13.setLabel("2013");
            for (int i = 0; i < lstDatos.size(); i++) {
                lstCircuito2013 = FGlobal.ObtenerDatosDadoAnioDadoCircuito(anioSel, lstDatos.get(i).getCircuito());
                anio13.set(lstDatos.get(i).getCircuito(), lstCircuito2013.size());
            }
            model.addSeries(anio13);
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error");
        }
        return model;
    }*/

}
