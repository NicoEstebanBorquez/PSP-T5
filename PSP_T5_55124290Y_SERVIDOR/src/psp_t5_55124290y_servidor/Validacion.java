package psp_t5_55124290y_servidor;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validacion {

    public int iniciarSesion(String usuario, String contraseña) {
        int resultado = 0;
        try {

            String contraseñaEncriptada = encriptarSha256(contraseña);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/psp_t5", "root", "");

            String sql = "SELECT * FROM usuarios WHERE nombre_usuario =? and password_usuario=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, contraseñaEncriptada);

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

    public int crearUsuario(String usuario, String contraseña) {
        int resultado = 0;
        try {

            String contraseñaEncriptada = encriptarSha256(contraseña);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/psp_t5", "root", "");

            String sql = "INSERT INTO usuarios VALUES(?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, contraseñaEncriptada);

            resultado = pst.executeUpdate();

            pst.close();
            cn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public String encriptarSha256(String texto) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha-256");

            byte[] cifrado = md.digest(texto.getBytes());

            StringBuffer ch = new StringBuffer();
            for (int i = 0; i < cifrado.length; i++) {
                ch.append(Integer.toHexString(0xFF & cifrado[i]));
            }
            return ch.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
