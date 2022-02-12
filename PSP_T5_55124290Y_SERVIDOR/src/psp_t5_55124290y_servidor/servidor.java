package psp_t5_55124290y_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JTextArea;
import javax.net.ssl.*;
import encriptacion.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Esta clase se encarga de la creacion del hilo Servidor y del control del Stock de ordenadores.
 *
 * @author Nicolás Esteban 55124290Y
 */
public class servidor extends Thread {

    private SSLServerSocket socketServidor;
    private SSLSocket socketCliente;
    private int puerto;
    private JTextArea consola;
    private BufferedReader recibir;
    private PrintWriter enviar;
    private int stock;
    Validacion validar = new Validacion();

    /**
     * Constructor de la clase.
     *
     * @param consola Objeto tipo JTextArea donde se imprimirán los mensajes.
     * @param puerto Puerto al que se realizará la conexión.
     * @param stockInicial Stock con el que se inicia el programa.
     */
    public servidor(JTextArea consola, int puerto, int stockInicial) {
        this.consola = consola;
        this.puerto = puerto;
        this.stock = stockInicial;
        try {
            //SSLServerSocketFactory utilizado para crear el SSLServerSocket
            SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            //Inicialización del SSLServerSocket
            socketServidor = (SSLServerSocket) factory.createServerSocket(puerto);
            consola.append("El servidor se ha iniciado correctamente en el puerto " + puerto + System.lineSeparator());
        } catch (Exception e) {
            consola.append("Error al iniciar el servidor." + System.lineSeparator() + e.getMessage()
                    + System.lineSeparator());
        }
    }

    /**
     * Método que inicia el hilo Servidor.
     */
    public void run() {

        try {
            socketCliente = (SSLSocket) socketServidor.accept();

            consola.append("Conexión realizada con el cliente" + System.lineSeparator());
            consola.append(socketCliente.toString() + System.lineSeparator());

            recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            enviar = new PrintWriter(socketCliente.getOutputStream(), true);

            //Instancia de la clase "EncriptacionAsimetrica"
            EncriptacionAsimetrica encriptacion = new EncriptacionAsimetrica();

            //Se obtiene la clave Pública
            PublicKey clavePublica = encriptacion.obtenerClavePublica("Claves/clavePublica");

            while (true) {
                String textoEncriptadoRecibido = recibir.readLine();
                System.out.println("1.Mensaje recibido del cliente [encriptado]: " + textoEncriptadoRecibido);

                //Se desencripta el mensaje recibido utilizando la clave Pública
                String textoDesencriptado = encriptacion.desencriptarTexto(textoEncriptadoRecibido, clavePublica);
                System.out.println("2.Mensaje recibido del cliente [desencriptado]" + textoDesencriptado);

                switch (textoDesencriptado) {
                    case "CONSULTA DE STOCK":
                        this.enviar("Stock disponible: " + this.getStock());
                        break;
                    case "+1":
                        this.aumentarStock();
                        consola.append("Cliente ha añadido 1 ordenador al stock." + System.lineSeparator());
                        break;
                    case "-1":
                        this.reducirStock();
                        consola.append("Cliente ha eliminado 1 ordenador del stock." + System.lineSeparator());
                        break;
                    default:
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que envía el mensaje al Cliente.
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
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        enviar.println(mensajeEncriptado);
        enviar.flush();

        //Muestra por consola:
        System.out.println("Mensaje encriptado enviado a Cliente: " + mensajeEncriptado);
    }

    /**
     * Método que disminuye el stock en -1 ordenador.
     */
    private void reducirStock() {
        this.stock = stock - 1;
    }

    /**
     * Método que aumenta el stock en 1 ordenador.
     */
    private void aumentarStock() {
        this.stock = stock + 1;
    }

    /**
     * Método que obtiene el stock actual.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Método setter de stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

}
