package encriptacion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;


public class GeneradorClaves {
    private KeyPairGenerator generador;
    private KeyPair parDeClaves;
    private PrivateKey clavePrivada;
    private PublicKey clavePublica;

    /**
     * Constructor de la clase. Prepara el generador de claves
     *
     * @param longitud Longitud de clave a utilizar
     */
    public GeneradorClaves(int longitud)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(longitud);
    }

    /**
     * Utiliza el KeyPairGenerator para crear una clave privada y una pública
     */
    public void generarClaves() {
        parDeClaves = this.generador.generateKeyPair();
        clavePrivada = parDeClaves.getPrivate();
        clavePublica = parDeClaves.getPublic();
    }

    public PrivateKey getClavePrivada() {
        return clavePrivada;
    }

    public PublicKey getClavePublica() {
        return clavePublica;
    }

    public void escribirFichero(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
}
