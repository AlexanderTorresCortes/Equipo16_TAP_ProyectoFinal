/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.MODELO_Ejemplo;
import VistaEjemplo.VISTA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alexa
 */
public class CONTROLADOR_Ejemplo implements ActionListener{
    
    private VISTA view;
    private MODELO_Ejemplo model;
    
    public CONTROLADOR_Ejemplo(VISTA view, MODELO_Ejemplo model){
        this.view=view;
        this.model=model;
        this.view.btnMultiplicar.addActionListener(this);
    }
    
    public void iniciar(){
        view.setTitle("MVC multiplicar");
        view.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e){
        
        model.setNum1(Integer.parseInt(view.lbl1.getText()));
        model.setNum2(Integer.parseInt(view.lbl2.getText()));
        model.Multiplicar();
        view.lblResultado.setText(String.valueOf(model.getResultado()));
    }
}
