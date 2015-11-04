/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscunach.vif.controladores.devif;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import oscunach.vif.busquedas.FBDevif;
import recursos.MesesDias;

/**
 *
 * @author Geovanny
 */
@ManagedBean
@RequestScoped
public class CtrlDevifMesDenuncia {

    private ArrayList<String> lst;
    private CartesianChartModel graficaMeses;
    private CartesianChartModel graficaMesesGenero;
    private int anioSel;

    public ArrayList<String> getLst() {
        return lst;
    }

    public void setLst(ArrayList<String> lst) {
        this.lst = lst;
    }

    public CartesianChartModel getGraficaMeses() {
        return graficaMeses;
    }

    public void setGraficaMeses(CartesianChartModel graficaMeses) {
        this.graficaMeses = graficaMeses;
    }

    public CartesianChartModel getGraficaMesesGenero() {
        return graficaMesesGenero;
    }

    public void setGraficaMesesGenero(CartesianChartModel graficaMesesGenero) {
        this.graficaMesesGenero = graficaMesesGenero;
    }

    public int getAnioSel() {
        return anioSel;
    }

    public void setAnioSel(int anioSel) {
        this.anioSel = anioSel;
    }

    public CtrlDevifMesDenuncia() {
        this.reinit();
    }
    
    private void reinit() {
        this.graficar();
    }

    @PostConstruct
    public void graficar() {
        graficaMeses = diasAtencion(anioSel);
        graficaMesesGenero = diasAtencionGenero(anioSel);

    }

    private CartesianChartModel diasAtencion(int anio) {
        CartesianChartModel model = new CartesianChartModel();
        try {
            lst = MesesDias.obtenerMeses();
            ChartSeries dias = new ChartSeries();
            dias.setLabel("Meses de Denuncia");
            for (int i = 0; i < lst.size(); i++) {
                dias.set(lst.get(i), FBDevif.obtenerDatosDadoAnioMesDenuncia(anio, lst.get(i)).size());
            }
            model.addSeries(dias);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }

    private CartesianChartModel diasAtencionGenero(int anio) {
        CartesianChartModel model = new CartesianChartModel();
        try {
            lst = MesesDias.obtenerMeses();
            ChartSeries femenino = new ChartSeries();
            femenino.setLabel("Femenino");
            for (int i = 0; i < lst.size(); i++) {
                femenino.set(lst.get(i), FBDevif.obtenerDatosDadoAnioMesDenunciaGenero(anio, lst.get(i), "FEMENINO").size());
            }

            ChartSeries masculino = new ChartSeries();
            masculino.setLabel("Masculino");
            for (int i = 0; i < lst.size(); i++) {
                masculino.set(lst.get(i), FBDevif.obtenerDatosDadoAnioMesDenunciaGenero(anio, lst.get(i), "MASCULINO").size());
            }

            model.addSeries(femenino);
            model.addSeries(masculino);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return model;
    }

}
