package Controlador;

import javax.swing.table.DefaultTableModel;
import Modelo.MODELO_Registro_Criminal;

public class CONTROLADOR_Registro_Criminal {
    private CONTROLADOR_Registro_Criminal modelo;

    public CONTROLADOR_Registro_Criminal(MODELO_Registro_Criminal model) {
        this.modelo = modelo;
    }

    public void createEmail(){
        modelo.createEmail();
    }

    public void nuevoIngreso() {
        modelo.nuevoIngreso();
    }
}
