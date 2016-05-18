/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import serversocket.Server;

/**
 *
 * @author Daniel
 */
public class FmGlavna extends javax.swing.JFrame {

    Server server;

    /**
     * Creates new form FmGlavna
     */
    public FmGlavna() {
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        lbl_naslov = new javax.swing.JLabel();
        lbl_opis_stanja = new javax.swing.JLabel();
        lbl_stanje = new javax.swing.JLabel();
        btn_start_stop = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_korisnici = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        menu_server = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_naslov.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_naslov.setText("Dobrodosli na server user interface 1.0!");

        lbl_opis_stanja.setText("Trenutno stanje servera:");

        lbl_stanje.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbl_stanje.setText("Ne radi");

        btn_start_stop.setText("Pokreni");
        btn_start_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_start_stopActionPerformed(evt);
            }
        });

        menu_korisnici.setText("Korisnici");

        jMenuItem2.setText("Administracija");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_korisnici.add(jMenuItem2);

        jMenuBar1.add(menu_korisnici);

        menu_server.setText("Server");

        jMenuItem3.setText("Administracija");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_server.add(jMenuItem3);

        jMenuBar1.add(menu_server);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_opis_stanja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_stanje, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_naslov)
                        .addContainerGap(33, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btn_start_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_naslov)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_opis_stanja)
                    .addComponent(lbl_stanje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btn_start_stop)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_start_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_start_stopActionPerformed
        if (!server.isAktiviran()) {
            server = new Server();
            server.start();
            server.setAktiviran(true);
            formaServerAktivna();

        } else {
            server.zaustaviNiti();
            formaServerNeaktivna();
            server.setAktiviran(false);

        }
    }//GEN-LAST:event_btn_start_stopActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FmKorisnici fmk = new FmKorisnici();
        fmk.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String brojPorta = JOptionPane.showInputDialog(rootPane, "Unesite broj porta!");
        Server.brojPorta = Integer.parseInt(brojPorta);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FmGlavna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_start_stop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JLabel lbl_naslov;
    private javax.swing.JLabel lbl_opis_stanja;
    private javax.swing.JLabel lbl_stanje;
    private javax.swing.JMenu menu_korisnici;
    private javax.swing.JMenu menu_server;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        lbl_stanje.setForeground(Color.red);
        menu_korisnici.setVisible(false);
    }

    private void formaServerAktivna() {
        btn_start_stop.setText("Zaustavi");
        lbl_stanje.setText("Radi");
        lbl_stanje.setForeground(Color.green);
        menu_server.setVisible(false);
        menu_korisnici.setVisible(true);
    }

    private void formaServerNeaktivna() {
        btn_start_stop.setText("Pokreni");
        lbl_stanje.setText("Ne radi");
        lbl_stanje.setForeground(Color.red);
        menu_server.setVisible(true);
        menu_korisnici.setVisible(false);
    }
}
