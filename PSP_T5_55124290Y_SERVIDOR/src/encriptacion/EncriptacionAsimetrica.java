package encriptacion;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;


public class EncriptacionAsimetrica {

    private Cipher cipher;

    /**
     * Constructor de la clase. Genera un nuevo par de claves pública y privada para utilizarse en los proceso de encriptación y desencriptación
     */
    public EncriptacionAsimetrica() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException, IOException {
        this.cipher = Cipher.getInstance("RSA");

        GeneradorClaves generador = new GeneradorClaves(1024);
        generador.generarClaves();
        generador.escribirFichero("Claves/clavePublica", generador.getClavePublica().getEncoded());
        generador.escribirFichero("Claves/clavePrivada", generador.getClavePrivada().getEncoded());
    }

    /**
     * Recupera la clave privada del fichero pasado como parámetro
     *
     * @param filename fichero a recuperar
     */
    public PrivateKey obtenerClavePrivada(String ruta) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(ruta).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * Recupera la clave pública del fichero pasado como parámetro
     *
     * @param filename nombre del fichero a recuperar
     */
    public PublicKey obtenerClavePublica(String ruta) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(ruta).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * Realiza la encriptación de un texto
     *
     * @param msg Texto a cifrar
     * @param key Clave privada a usar en el proceso de cifrado
     */
    public String encriptarTexto(String mensaje, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(mensaje.getBytes("UTF-8")));
    }

    /**
     * Desencripta un texto
     *
     * @param msg Texto a desencriptar
     * @param key Clave pública a usar en el proceso de desencriptado
     */
    public String desencriptarTexto(String mensaje, PublicKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(mensaje)), "UTF-8");
    }
    
}
