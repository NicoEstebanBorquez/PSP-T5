package psp_t5_55124290y_cliente;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validacion {

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
