package psp_t5_55124290y_servidor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validacion {

    public int iniciarSesion(String usuario, String contraseña) {
        int resultado = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/psp_t5", "root", "");
            
            String sql = "select * from usuarios where nombre_usuario =? and password_usuario=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, contraseña);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                resultado = 1;
            }
            rs.close();
            pst.close();
            cn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public void encriptarContraseña() {

    }

}
