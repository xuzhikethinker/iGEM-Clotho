/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PrimerResultFrame.java
 *
 * Created on Jul 7, 2011, 3:27:51 PM
 */
package org.clothocad.tool.primerdesigner;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.clothocore.api.core.Collector;
import org.clothocore.api.data.Oligo;

/**
 *
 * @author jenhan
 */
public class PrimerResultFrame extends javax.swing.JFrame {

    /** Creates new form PrimerResultFrame */
    public PrimerResultFrame() {
        try {
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrimerResultFrame(PrimerDesignController pdc, ArrayList<String> fwd, ArrayList<String> rev, ArrayList<Double> fwdG, ArrayList<Double> revG) {
        try {
            _controller = pdc;
            fwdSequences = fwd;
            revSequences = rev;
            initComponents();
            fwdTableModel = new Object[fwdSequences.size()][5];
            for (int i = 0; i < fwdSequences.size(); i++) {
                fwdTableModel[i][0] = fwdSequences.get(i);
                fwdTableModel[i][1] = fwdSequences.get(i).length();
//                fwdTableModel[i][2] = _controller.calcDeltaG(fwdSequences.get(i));
                fwdTableModel[i][2] = fwdG.get(i);
                fwdTableModel[i][3] = _controller.calcGCContent(fwdSequences.get(i));
                fwdTableModel[i][4] = _controller.calcMeltingTemp(fwdSequences.get(i));

            }
            revTableModel = new Object[revSequences.size()][5];
            for (int i = 0; i < revSequences.size(); i++) {
                revTableModel[i][0] = revSequences.get(i);
                revTableModel[i][1] = revSequences.get(i).length();
//                revTableModel[i][2] = _controller.calcDeltaG(revSequences.get(i));
                revTableModel[i][2] = revG.get(i);
                revTableModel[i][3] = _controller.calcGCContent(revSequences.get(i));
                revTableModel[i][4] = _controller.calcMeltingTemp(revSequences.get(i));

            }
            fwdResultsTable.setModel(new javax.swing.table.DefaultTableModel(fwdTableModel, new String[]{"Fwd Seq", "Length", "Delta G", "GC%", "Tm"}));
            revResultsTable.setModel(new javax.swing.table.DefaultTableModel(revTableModel, new String[]{"Rev Seq", "Length", "Delta G", "GC%", "Tm"}));
            fwdResultsTable.setRowSelectionInterval(0, 0);
            fwdResultsTable.setRowSelectionInterval(0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        revResultsTable = new javax.swing.JTable();
        saveAllButton = new javax.swing.JButton();
        csvButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        fwdResultsTable = new javax.swing.JTable();
        tmDifferenceLabel = new javax.swing.JLabel();
        lengthDifferenceLabel = new javax.swing.JLabel();
        deltaGDifferenceLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fwdSequenceTextField = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        revSequenceTextField = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        dimerTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(200, 200));

        revResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Rev Seq", "Length", "Delta G", "GC%", "Tm"
            }
        ));
        revResultsTable.setFillsViewportHeight(true);
        revResultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        revResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revResultsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(revResultsTable);
        revResultsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.revResultsTable.columnModel.title0")); // NOI18N
        revResultsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title2")); // NOI18N
        revResultsTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title3")); // NOI18N
        revResultsTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title4")); // NOI18N
        revResultsTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.revResultsTable.columnModel.title4")); // NOI18N

        saveAllButton.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.saveAllButton.text")); // NOI18N
        saveAllButton.setToolTipText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.saveAllButton.toolTipText")); // NOI18N
        saveAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllButtonActionPerformed(evt);
            }
        });

        csvButton.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.csvButton.text")); // NOI18N
        csvButton.setToolTipText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.csvButton.toolTipText")); // NOI18N
        csvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csvButtonActionPerformed(evt);
            }
        });

        closeButton.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        fwdResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fwd Seq", "Length", "Delta G", "GC%", "Tm"
            }
        ));
        fwdResultsTable.setFillsViewportHeight(true);
        fwdResultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fwdResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fwdResultsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(fwdResultsTable);
        fwdResultsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title0")); // NOI18N
        fwdResultsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title2")); // NOI18N
        fwdResultsTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title3")); // NOI18N
        fwdResultsTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title4")); // NOI18N
        fwdResultsTable.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.fwdResultsTable.columnModel.title4_1")); // NOI18N

        tmDifferenceLabel.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.tmDifferenceLabel.text")); // NOI18N

        lengthDifferenceLabel.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.lengthDifferenceLabel.text")); // NOI18N

        deltaGDifferenceLabel.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.deltaGDifferenceLabel.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(PrimerResultFrame.class, "PrimerResultFrame.jLabel2.text")); // NOI18N

        jScrollPane3.setHorizontalScrollBar(null);

        fwdSequenceTextField.setColumns(20);
        fwdSequenceTextField.setLineWrap(true);
        fwdSequenceTextField.setRows(5);
        jScrollPane3.setViewportView(fwdSequenceTextField);

        jScrollPane4.setHorizontalScrollBar(null);

        revSequenceTextField.setColumns(20);
        revSequenceTextField.setLineWrap(true);
        revSequenceTextField.setRows(5);
        jScrollPane4.setViewportView(revSequenceTextField);

        dimerTextArea.setColumns(20);
        dimerTextArea.setRows(5);
        jScrollPane6.setViewportView(dimerTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addComponent(tmDifferenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deltaGDifferenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lengthDifferenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(297, 297, 297)
                        .addComponent(closeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveAllButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane2, jScrollPane4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tmDifferenceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lengthDifferenceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deltaGDifferenceLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(csvButton)
                        .addComponent(saveAllButton)
                        .addComponent(closeButton)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void csvButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csvButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csvButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void fwdResultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fwdResultsTableMouseClicked
        int fwdIndex = fwdResultsTable.getSelectedRow();
        int revIndex = revResultsTable.getSelectedRow();
        if (fwdIndex > -1 && revIndex > -1) {
            lengthDifferenceLabel.setText("Length Diff: " + Math.abs(((String) fwdTableModel[fwdIndex][0]).length() - ((String) revTableModel[revIndex][0]).length()));
            tmDifferenceLabel.setText("Tm Diff: " + Math.abs(((Double) fwdTableModel[fwdIndex][4]) - ((Double) revTableModel[revIndex][4])));
            deltaGDifferenceLabel.setText("Delta G Diff: " + Math.abs(((Double) fwdTableModel[fwdIndex][2]) - ((Double) revTableModel[revIndex][2])));
            fwdSequenceTextField.setText(((String) fwdTableModel[fwdIndex][0]));
            revSequenceTextField.setText(((String) revTableModel[revIndex][0]));
            dimerTextArea.setBackground(Color.pink);
            dimerTextArea.setText("forward Sequence: " + fwdTableModel[fwdIndex][0] + "\n" + "reverse Sequence: " + revTableModel[revIndex][0]
                    + "\nForward Delta G: " + fwdTableModel[fwdIndex][2] + "\nReverse delta g: " + revTableModel[revIndex][2]);

            String results = _controller.checkForDimers((String) fwdTableModel[fwdIndex][0], (String) revTableModel[revIndex][0], (Double) fwdTableModel[fwdIndex][2], (Double) revTableModel[revIndex][2]);
            dimerTextArea.setText(results);
        }

    }//GEN-LAST:event_fwdResultsTableMouseClicked

    private void revResultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revResultsTableMouseClicked
        int fwdIndex = fwdResultsTable.getSelectedRow();
        int revIndex = revResultsTable.getSelectedRow();
        if (fwdIndex > -1 && revIndex > -1) {
            lengthDifferenceLabel.setText("Length Diff: " + Math.abs(((String) fwdTableModel[fwdIndex][0]).length() - ((String) revTableModel[revIndex][0]).length()));
            tmDifferenceLabel.setText("Tm Diff: " + Math.abs(((Double) fwdTableModel[fwdIndex][4]) - ((Double) revTableModel[revIndex][4])));
            deltaGDifferenceLabel.setText("Delta G Diff: " + Math.abs(((Double) fwdTableModel[fwdIndex][2]) - ((Double) revTableModel[revIndex][2])));
            fwdSequenceTextField.setText(((String) fwdTableModel[fwdIndex][0]));
            revSequenceTextField.setText(((String) revTableModel[revIndex][0]));
            dimerTextArea.setBackground(Color.pink);
            dimerTextArea.setText("forward Sequence: " + fwdTableModel[fwdIndex][0] + "\n" + "reverse Sequence: " + revTableModel[revIndex][0]
                    + "\nForward Delta G: " + fwdTableModel[fwdIndex][2] + "\nReverse delta g: " + revTableModel[revIndex][2]);

            String results = _controller.checkForDimers((String) fwdTableModel[fwdIndex][0], (String) revTableModel[revIndex][0], (Double) fwdTableModel[fwdIndex][2], (Double) revTableModel[revIndex][2]);
            dimerTextArea.setText(results);

        }    }//GEN-LAST:event_revResultsTableMouseClicked

    private void saveAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllButtonActionPerformed
        String nameBase = JOptionPane.showInputDialog("Enter oligo name root");
        for (int i = 0; i < fwdSequences.size(); i++) {
            Oligo ol = new Oligo(nameBase + "F" + i, "primer", Collector.getCurrentUser(), fwdSequences.get(i));
            ol.saveDefault();
        }
        for (int i = 0; i < revSequences.size(); i++) {
            Oligo ol = new Oligo(nameBase + "R" + i, "primer", Collector.getCurrentUser(), revSequences.get(i));
            ol.saveDefault();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAllButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrimerResultFrame().setVisible(true);
            }
        });
    }
    private Object[][] fwdTableModel;
    private Object[][] revTableModel;
    private PrimerDesignController _controller;
    private ArrayList<String> fwdSequences;
    private ArrayList<String> revSequences;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton csvButton;
    private javax.swing.JLabel deltaGDifferenceLabel;
    private javax.swing.JTextArea dimerTextArea;
    private javax.swing.JTable fwdResultsTable;
    private javax.swing.JTextArea fwdSequenceTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lengthDifferenceLabel;
    private javax.swing.JTable revResultsTable;
    private javax.swing.JTextArea revSequenceTextField;
    private javax.swing.JButton saveAllButton;
    private javax.swing.JLabel tmDifferenceLabel;
    // End of variables declaration//GEN-END:variables
}
