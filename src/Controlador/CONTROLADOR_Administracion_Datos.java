package Controlador;

import javax.swing.table.DefaultTableModel;
import Modelo.MODELO_Administracion_Datos;

public class CONTROLADOR_Administracion_Datos {
    private CONTROLADOR_Administracion_Datos modelo;

    public CONTROLADOR_Administracion_Datos(MODELO_Administracion_Datos model) {
        this.modelo = modelo;
    }

    public void cargarTabla(DefaultTableModel tableModel, String filtro) {
        modelo.cargarTabla(tableModel, filtro);
    }

    public void generarReporte() {
        modelo.generarReporte();
    }
}

