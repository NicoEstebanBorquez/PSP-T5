package psp_t5_55124290y_cliente;

import encriptacion.EncriptacionAsimetrica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.net.ssl.*;

/**
 * Esta clase se encarga de la creacion del hilo Cliente.
 *
 * @author Nicolás Esteban 55124290Y
 */
public class cliente extends Thread {

    private SSLSocket socketCliente;
    private String server;
    private int puerto;
    private JTextArea consola;
    private BufferedReader recibir;
    private PrintWriter enviar;
    Validacion validar = new Validacion();

    /**
     * Constructor de la clase.
     *
     * @param consola Objeto tipo JTextArea donde se imprimirán los mensajes.
     * @param server Server con el que se realizará la conexión.
     * @param puerto Puerto al que se realizará la conexión.
     */
    public cliente(JTextArea consola, String server, int puerto) {
        this.consola = consola;
        this.server = server;
        this.puerto = puerto;

        try {
            //SSLSocketFactory utilizado para crear el SSLSocket
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            //Inicialización del SSLSocket
            socketCliente = (SSLSocket) factory.createSocket(server, puerto);
            consola.append("Conexión establecida con el servidor desde el puerto " + puerto
                    + System.lineSeparator());
        } catch (Exception e) {
            consola.append("Error al conectar con el servidor desde el puerto " + puerto
                    + System.lineSeparator() + e.getMessage() + System.lineSeparator());
        }
    }

    /**
     * Método que envía el mensaje al Servidor.
     *
     * @param mensaje Mensaje que será enviado.
     */
    public void enviar(String mensaje) {
        String mensajeEncriptado = "";
        //ENCRIPTACION:
        try {
            //Instancia de la clase "EncriptacionAsimetrica"
            EncriptacionAsimetrica encriptacion = new EncriptacionAsimetrica();

            //Se obtiene la clave Privada
            PrivateKey clavePrivada = encriptacion.obtenerClavePrivada("Claves/clavePrivada");

            //Se encripta el mensaje a enviar utilizando la clave Privada
            mensajeEncriptado = encriptacion.encriptarTexto(mensaje, clavePrivada);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        enviar.println(mensajeEncriptado);
        enviar.flush();

        //Muestra por consola:
        System.out.println("Mensaje encriptado enviado a Servidor: " + mensajeEncriptado);
    }

    /**
     * Método que inicia el hilo Cliente.
     */
    public void run() {

        try {
            recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            enviar = new PrintWriter(socketCliente.getOutputStream(), true);

            //Instancia de la clase "EncriptacionAsimetrica"
            EncriptacionAsimetrica encriptacion = new EncriptacionAsimetrica();

            //Se obtiene la clave Pública
            PublicKey clavePublica = encriptacion.obtenerClavePublica("Claves/clavePublica");

            while (true) {
                String textoEncriptadoRecibido = recibir.readLine();
                System.out.println("1.Mensaje recibido del servidor [encriptado]: " + textoEncriptadoRecibido);

                //Se desencripta el mensaje recibido utilizando la clave Pública
                String textoDesencriptado = encriptacion.desencriptarTexto(textoEncriptadoRecibido, clavePublica);
                System.out.println("2.Mensaje recibido del servidor [desencriptado]" + textoDesencriptado);

                if (textoDesencriptado.contains("Stock disponible")) {
                    consola.append(textoDesencriptado + System.lineSeparator());
                    JOptionPane.showMessageDialog(null, textoDesencriptado);
                } else {
                    consola.append("Servidor: " + textoDesencriptado + System.lineSeparator());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
