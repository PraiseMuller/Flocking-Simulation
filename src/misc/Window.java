/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package misc;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Window extends javax.swing.JFrame {
    private final int NUM_BOIDS = 200;
    private final int MAX_WIDTH;
    private final int MAX_HEIGHT;
    private Boid[] boidsArr;
    private MainPanel mainPanel;
    
    private final Timer timer;
    private final int deltaTime = 30;   //FPS ?
    
    public Window() {
        initComponents();
        this.MAX_WIDTH = this.getWidth();
        this.MAX_HEIGHT = this.getHeight();
        initBoids();
        this.timer = new Timer(this.deltaTime, (ActionEvent e) -> {
            update();
        });
        this.timer.start();
    }
    
    private void initBoids(){
        this.boidsArr = new Boid[NUM_BOIDS];
        for(int i = 0; i < this.boidsArr.length; i++){
            Boid temp = new Boid(MAX_WIDTH, MAX_HEIGHT);
            this.boidsArr[i] = temp;           
        }   
        this.mainPanel = new MainPanel(this.boidsArr, MAX_WIDTH, MAX_HEIGHT);
        this.add(this.mainPanel);
    }
    
    private void update(){   
        for(Boid b : this.boidsArr){
            this.mainPanel.repaint();
        } 
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BOIDS FLOCKING SIMULATION");
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Window().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
