// RegistroPersonalController.java
package Controlador;

import Modelo.MODELO_Registro_Personal;
import Modelo.MODELO_Registro_Personal_BDD;
import Vista.Registro_Personal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CONTROLADOR_Registrp_Personal {
    private Registro_Personal vista;
    private MODELO_Registro_Personal_BDD modelo;

    public CONTROLADOR_Registrp_Personal(Registro_Personal vista, MODELO_Registro_Personal_BDD modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // Agrega listeners a los botones
        this.vista.btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        this.vista.btnGrafica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGrafica();
            }
        });
    }

    public void registrarUsuario() {
        String usuario = vista.txtUsuario.getText();
        String nombre = vista.txtNombre.getText();
        String apellido = vista.txtApellido.getText();
        int edad = Integer.parseInt(vista.txtEdad.getText());
        String sexo = (String) vista.cboSexo.getSelectedItem();
        String cargo = (String) vista.cboCargo.getSelectedItem();
        String contraseña = new String(vista.txtContraseña.getPassword());
        String ruta = vista.lblRuta.getText();

        if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edad <= 0 || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "DEBES RELLENAR TODOS LOS CAMPOS");
        } else if (cargo.equalsIgnoreCase("Seleccionar")) {
            JOptionPane.showMessageDialog(vista, "DEBES DARLE UN CARGO AL TRABAJADOR");
        } else {
            MODELO_Registro_Personal nuevoUsuario = new MODELO_Registro_Personal();
            nuevoUsuario.setUsuario(usuario);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setEdad(edad);
            nuevoUsuario.setSexo(sexo);
            nuevoUsuario.setCargo(cargo);
            nuevoUsuario.setContraseña(contraseña);
            nuevoUsuario.setRuta(ruta);

            boolean registrado = modelo.registrarUsuario(nuevoUsuario);
            if (registrado) {
                JOptionPane.showMessageDialog(vista, "EL TRABAJADOR HA SIDO REGISTRADO CORRECTAMENTE");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "NO SE PUDO INGRESAR EL TRABAJADOR");
            }
        }
    }

    public void mostrarGrafica() {
        // Lógica para mostrar la gráfica
    }

    private void cargarTabla() {
        // Lógica para cargar los datos en la tabla
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtEdad.setText("");
        vista.cboCargo.setSelectedItem("Seleccionar");
        vista.txtContraseña.setText("");
        vista.txtUsuario.setText("");
        vista.lblFoto.setIcon(null);
        vista.lblRuta.setText("");
    }
}

