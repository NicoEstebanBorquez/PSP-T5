package psp_t5_55124290y_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JTextArea;
import javax.net.ssl.*;

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
            while (true) {
                String textoRecibidoEncriptado = recibir.readLine();

                switch (textoRecibidoEncriptado) {
                    case "345685c267e166c57551ffe6c73fbc77114bf6c56370d7286ead86b0a711f2"://CONSULTA DE STOCK
                        this.enviar("Stock disponible: ");//+ this.getStock()
                        break;
                    case "c59dc4e44ff99288156d4dff2168f6ac7ddee6b1fc7ccc754656ffaa6d351ea": //+1
                        this.aumentarStock();
                        consola.append("Cliente ha añadido 1 ordenador al stock." + System.lineSeparator());
                        break;
                    case "1bad6b8cf97131fceab8543e81f7757195fbb1d36b376ee994ad1cf17699c464"://-1
                        this.reducirStock();
                        consola.append("Cliente ha eliminado 1 ordenador del stock." + System.lineSeparator());
                        break;
                    default:
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que envía el mensaje al Cliente.
     *
     * @param mensaje Mensaje que será enviado.
     */
    public void enviar(String mensaje) {
        String mensajeEncriptado = validar.encriptarSha256(mensaje);
        enviar.println(mensajeEncriptado + this.getStock());
        enviar.flush();
        
        //Muestra por consola:
        System.out.println("Mensaje encriptado enviado por Servidor: " + mensajeEncriptado + this.getStock());
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
