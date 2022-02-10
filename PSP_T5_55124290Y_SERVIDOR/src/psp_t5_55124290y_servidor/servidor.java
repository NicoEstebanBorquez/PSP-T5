package psp_t5_55124290y_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 * Esta clase se encarga de la creacion del hilo Servidor y del control del Stock de chirimoyas.
 *
 * @author Nicolás Esteban 55124290Y
 */
public class servidor extends Thread {

    private ServerSocket socketServidor;
    private Socket socketCliente;
    private int puerto;
    private JTextArea consola;
    private BufferedReader recibir;
    private PrintWriter enviar;
    private int stock;

    /**
     * Constructor de la clase.
     * @param consola Objeto tipo JTextArea donde se imprimirán los mensajes.
     * @param puerto Puerto al que se realizará la conexión.
     * @param stockInicial Stock con el que se inicia el programa.
     */
    public servidor(JTextArea consola, int puerto, int stockInicial) {
        this.consola = consola;
        this.puerto = puerto;
        this.stock = stockInicial;
        try {
            socketServidor = new ServerSocket(puerto);
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
            socketCliente = socketServidor.accept();
            consola.append("Conexión realizada con el cliente" + System.lineSeparator());
            consola.append(socketCliente.toString() + System.lineSeparator());

            recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            enviar = new PrintWriter(socketCliente.getOutputStream(), true);
            while (true) {
                String textoRecibido = recibir.readLine();
                switch (textoRecibido) {
                    case "CONSULTA DE STOCK":
                        this.enviar("Stock disponible: " + this.getStock());
                        break;
                    case "+1":
                        this.aumentarStock();
                        break;
                    case "-1":
                        this.reducirStock();
                        break;
                    default:
                }
                consola.append("Cliente: " + textoRecibido + System.lineSeparator());
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
        enviar.println(mensaje);
        enviar.flush();
    }

    /**
     * Método que disminuye el stock en -1 chirimoya.
     */
    private void reducirStock() {
        this.stock = stock - 1;
    }

    /**
     * Método que aumenta el stock en 1 chirimoya.
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
