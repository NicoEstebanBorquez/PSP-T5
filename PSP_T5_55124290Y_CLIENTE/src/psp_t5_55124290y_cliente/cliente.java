package psp_t5_55124290y_cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Esta clase se encarga de la creacion del hilo Cliente.
 *
 * @author Nicolás Esteban 55124290Y
 */
public class cliente extends Thread {

    private Socket socketCliente;
    private String server;
    private int puerto;
    private JTextArea consola;
    private BufferedReader recibir;
    private PrintWriter enviar;

    
    /**
     * Constructor de la clase.
     * @param consola Objeto tipo JTextArea donde se imprimirán los mensajes.
     * @param server Server con el que se realizará la conexión.
     * @param puerto Puerto al que se realizará la conexión.
     */
    public cliente(JTextArea consola, String server, int puerto) {
        this.consola = consola;
        this.server = server;
        this.puerto = puerto;

        try {
            socketCliente = new Socket(server, puerto);
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
        enviar.println(mensaje);
        enviar.flush();
    }

      /**
     * Método que inicia el hilo Cliente.
     */
    public void run() {
        try {
            recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            enviar = new PrintWriter(socketCliente.getOutputStream(), true);

            while (true) {
                String textoRecibido = recibir.readLine();

                if (textoRecibido.contains("Stock")) {
                    consola.append(textoRecibido + System.lineSeparator());
                    JOptionPane.showMessageDialog(null, textoRecibido);
                } else {
                    consola.append("Servidor: " + textoRecibido + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
