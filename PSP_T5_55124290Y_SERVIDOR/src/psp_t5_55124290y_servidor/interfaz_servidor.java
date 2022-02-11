package psp_t5_55124290y_servidor;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import psp_t5_55124290y_servidor.Validacion;

public class interfaz_servidor extends javax.swing.JFrame {

    servidor socketServidor;

    public interfaz_servidor() {

        //Asociar fichero "serverKey.jks" al KeyStore
        System.setProperty("javax.net.ssl.keyStore", "serverKey.jks");
        //Contraseña
        System.setProperty("javax.net.ssl.keyStorePassword", "claveSecreta");

        initComponents();
        this.setTitle("Almacén de ordenadores - Programa SERVIDOR");
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setSize(595, 520);
        this.setDefaultCloseOperation(interfaz_servidor.EXIT_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaConsola = new javax.swing.JTextArea();
        btnIniciarSesion = new javax.swing.JButton();
        btnArrancarServidor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbPuerto = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtStockInicial = new javax.swing.JTextField();
        btnCrearUsuario = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtAreaConsola.setColumns(20);
        txtAreaConsola.setRows(5);
        jScrollPane1.setViewportView(txtAreaConsola);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 385, 285));

        btnIniciarSesion.setBackground(new java.awt.Color(29, 33, 123));
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 110, -1));

        btnArrancarServidor.setBackground(new java.awt.Color(51, 153, 0));
        btnArrancarServidor.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        btnArrancarServidor.setForeground(new java.awt.Color(255, 255, 255));
        btnArrancarServidor.setText("Iniciar servidor");
        btnArrancarServidor.setEnabled(false);
        btnArrancarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrancarServidorActionPerformed(evt);
            }
        });
        getContentPane().add(btnArrancarServidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 140, 50));

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(29, 33, 123));
        jLabel1.setText("Contraseña:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(29, 33, 123));
        jLabel2.setText("Stock inicial:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, -1, -1));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 100, -1));

        jLabel3.setFont(new java.awt.Font("Gadugi", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(29, 33, 123));
        jLabel3.setText("Almacén de ordenadores - SERVIDOR");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        cbPuerto.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        cbPuerto.setForeground(new java.awt.Color(29, 33, 123));
        cbPuerto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8899", "8098", "9090" }));
        getContentPane().add(cbPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 175, -1, -1));

        jLabel7.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(29, 33, 123));
        jLabel7.setText("Consultar stock:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(29, 33, 123));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sin título.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 305, 40, 33));

        jLabel4.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(29, 33, 123));
        jLabel4.setText("Iniciar sesión:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(29, 33, 123));
        jLabel5.setText("Puerto:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 175, -1, -1));

        jLabel6.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(29, 33, 123));
        jLabel6.setText("Usuario:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Gadugi", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(29, 33, 123));
        jLabel8.setText("Operaciones:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 275, -1, -1));
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 100, -1));
        getContentPane().add(txtStockInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 60, -1));

        btnCrearUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnCrearUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearUsuario.setText("Crear usuario");
        btnCrearUsuario.setEnabled(false);
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 110, -1));

        wallpaper.setBackground(new java.awt.Color(153, 255, 51));
        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Wallpaper.png"))); // NOI18N
        wallpaper.setText("jLabel3");
        getContentPane().add(wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

        Validacion validacion = new Validacion();
        String usuario = this.txtUsuario.getText().trim();
        String contraseña = this.txtContraseña.getText().trim();
        if (!usuario.equals("") && !contraseña.equals("")) {

            int permitirAcceso = validacion.iniciarSesion(usuario, contraseña);

            if (permitirAcceso > 0) {
                this.btnArrancarServidor.setEnabled(true);
                this.btnCrearUsuario.setEnabled(true);
                    btnCrearUsuario.setForeground(new java.awt.Color(29, 33, 123));
                txtAreaConsola.append("Se ha iniciado sesión correctamente." + System.lineSeparator());
            } else {
                txtAreaConsola.append("Nombre de usuario o contraseña incorrectos." + System.lineSeparator());
            }
        } else {
            txtAreaConsola.append("Debe introducir un Usuario y Contraseña." + System.lineSeparator());
        }
        this.txtUsuario.setText("");
        this.txtContraseña.setText("");

    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnArrancarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrancarServidorActionPerformed
        String puerto = "";
        int comboPuerto = cbPuerto.getSelectedIndex();

        switch (comboPuerto) {
            case 0:
                puerto = "8899";
                break;
            case 1:
                puerto = "8098";
                break;
            case 2:
                puerto = "9090";
                break;
        }

        String stockInicial = this.txtStockInicial.getText().trim();

        if (!stockInicial.equals("")) {
            socketServidor = new servidor(this.txtAreaConsola, Integer.parseInt(puerto), Integer.parseInt(stockInicial));
            socketServidor.start();

            //socketServidor.setStock(Integer.parseInt(stockInicial));
        } else {
            JOptionPane.showMessageDialog(this, "Debe indicar un STOCK INICAL para iniciar el servidor.", null, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnArrancarServidorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String stock = String.valueOf(socketServidor.getStock());
        JOptionPane.showMessageDialog(this, "Stock disponible: " + stock);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed

        Validacion validacion = new Validacion();
        String usuario = this.txtUsuario.getText().trim();
        String contraseña = this.txtContraseña.getText().trim();

        if (!usuario.equals("") && !contraseña.equals("")) {
            int usuarioCreado = validacion.crearUsuario(usuario, contraseña);

            if (usuarioCreado > 0) {
                txtAreaConsola.append("Usuario creado correctamente." + System.lineSeparator());
            } else {
                txtAreaConsola.append("Error al crear el usuario." + System.lineSeparator());
            }
        } else {
            txtAreaConsola.append("Debe introducir un Usuario y Contraseña." + System.lineSeparator());
        }

        this.txtUsuario.setText("");
        this.txtContraseña.setText("");
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        System.out.println(
                "***************************************\n"
                + "* PSP - Tarea Individual 5 - Almacén multiusuario *\n"
                + "***************************************\n"
                + "*  Nicolás Esteban Bórquez  *\n"
                + "***************************************\n"
                + "*  55124290Y  *\n"
                + "*************************************** ");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz_servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz_servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz_servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz_servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz_servidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArrancarServidor;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JComboBox<String> cbPuerto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaConsola;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtStockInicial;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
}
