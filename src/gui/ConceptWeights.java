/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.Map;
import java.util.Vector;
import utils.ReadUtils;

/**
 *
 * @author Administrator
 */
public class ConceptWeights extends javax.swing.JFrame {

    /**
     * Creates new form ConceptWeights
     */
    public ConceptWeights() {
        initComponents();
    }
    
    public ConceptWeights(Map originConcepts,Vector expandedOriginConcepts,Map plagConcepts,Vector expandedPlagConcepts) {
        initComponents();
        showWeights(originConcepts,expandedOriginConcepts,plagConcepts,expandedPlagConcepts);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitter = new javax.swing.JSplitPane();
        originScrollPane = new javax.swing.JScrollPane();
        originTextArea = new javax.swing.JTextArea();
        plagScrollPane = new javax.swing.JScrollPane();
        plagTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitter.setDividerLocation(400);
        mainSplitter.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        originTextArea.setColumns(20);
        originTextArea.setRows(5);
        originScrollPane.setViewportView(originTextArea);

        mainSplitter.setTopComponent(originScrollPane);

        plagTextArea.setColumns(20);
        plagTextArea.setRows(5);
        plagScrollPane.setViewportView(plagTextArea);

        mainSplitter.setRightComponent(plagScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitter, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitter, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ConceptWeights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConceptWeights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConceptWeights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConceptWeights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConceptWeights().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane mainSplitter;
    private javax.swing.JScrollPane originScrollPane;
    private javax.swing.JTextArea originTextArea;
    private javax.swing.JScrollPane plagScrollPane;
    private javax.swing.JTextArea plagTextArea;
    // End of variables declaration//GEN-END:variables

    private void showWeights(Map originConcepts, Vector expandedOriginConcepts, Map plagConcepts, Vector expandedPlagConcepts) {
      //show origin concepts before expanding and after expanding
      String originConcpetsStr=ReadUtils.printConceptsMap(originConcepts);
      String originExpandedConceptsStr=ReadUtils.printConceptsVector(expandedPlagConcepts);
      this.originTextArea.setText(originConcpetsStr+"\n"+originExpandedConceptsStr);
      
      String plagConcpetsStr=ReadUtils.printConceptsMap(plagConcepts);
      String plagExpandedConceptsStr=ReadUtils.printConceptsVector(expandedPlagConcepts);
      this.plagTextArea.setText(plagConcpetsStr+"\n"+plagExpandedConceptsStr);
      
      this.originTextArea.validate();
      this.originTextArea.repaint();
      
      this.plagTextArea.validate();
      this.plagTextArea.repaint();
      
        
    }
}
