/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MVC;

import Modelo.MODELO_Ejemplo;
import VistaEjemplo.VISTA;
import Controlador.CONTROLADOR_Ejemplo;
/**
 *
 * @author alexa
 */
public class Mvc_Ejemplo {
    public static void main(String args[]){
        MODELO_Ejemplo mod = new MODELO_Ejemplo();
        VISTA view = new VISTA();
        
        CONTROLADOR_Ejemplo ctrl = new CONTROLADOR_Ejemplo(view, mod);
        ctrl.iniciar();
        view.setVisible(true);
    }
    
}
