// UsuarioDAO.java
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import conexion.conexionmysql;

public class MODELO_Registro_Personal_BDD {
    private Connection connection;

    MODELO_Registro_Personal_BDD() {
        conexionmysql con = new conexionmysql();
        this.connection = con.conectar();
    }

    public boolean registrarUsuario(MODELO_Registro_Personal usuario) {
        String consulta = "INSERT INTO usuarios (Usuario, Nombre, Apellido, Edad, sexo, Cargo, Contraseña, ruta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(consulta);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setInt(4, usuario.getEdad());
            ps.setString(5, usuario.getSexo());
            ps.setString(6, usuario.getCargo());
            ps.setString(7, usuario.getContraseña());
            ps.setString(8, usuario.getRuta());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MODELO_Registro_Personal> obtenerUsuarios() {
        List<MODELO_Registro_Personal> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MODELO_Registro_Personal usuario = new MODELO_Registro_Personal();
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setEdad(rs.getInt("Edad"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setCargo(rs.getString("Cargo"));
                usuario.setContraseña(rs.getString("Contraseña"));
                usuario.setRuta(rs.getString("ruta"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
