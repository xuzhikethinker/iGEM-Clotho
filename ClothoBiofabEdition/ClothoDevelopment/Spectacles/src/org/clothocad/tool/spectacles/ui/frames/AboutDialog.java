/*
 Copyright (c) 2009 The Regents of the University of California.
 All rights reserved.
 Permission is hereby granted, without written agreement and without
 license or royalty fees, to use, copy, modify, and distribute this
 software and its documentation for any purpose, provided that the above
 copyright notice and the following two paragraphs appear in all copies
 of this software.

 IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
 FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
 ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
 PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
 CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
 ENHANCEMENTS, OR MODIFICATIONS..
 */

/*
 * AboutDialog.java
 *
 * Created on Aug 21, 2009, 3:58:06 PM
 */

package org.clothocad.tool.spectacles.ui.frames;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.openide.util.ImageUtilities;
import org.clothocad.tool.spectacles.Spectacles;

/**
 * AboutDialog displays a link to Spectacles (and to ClothoCAD),
 * as well as Spectacles' version information.
 * @author Rich
 */
public class AboutDialog extends javax.swing.JDialog {

    /** Creates new form AboutDialog */
    public AboutDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        _kcc = 0;
    }

    private void cleanup() {
        _kcc = 0;
        linkTextField.setText("http://sourceforge.net/projects/clothocad/");
        setTitle("About");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        versionLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        linkTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(AboutDialog.class, "AboutDialog.title")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        versionLabel.setText(org.openide.util.NbBundle.getMessage(AboutDialog.class, "AboutDialog.versionLabel.text")); // NOI18N

        closeButton.setMnemonic('c');
        closeButton.setText(org.openide.util.NbBundle.getMessage(AboutDialog.class, "AboutDialog.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        linkTextField.setEditable(false);
        linkTextField.setText(org.openide.util.NbBundle.getMessage(AboutDialog.class, "AboutDialog.linkTextField.text")); // NOI18N
        linkTextField.setToolTipText(org.openide.util.NbBundle.getMessage(AboutDialog.class, "AboutDialog.linkTextField.toolTipText")); // NOI18N
        linkTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                linkTextFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(linkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(versionLabel)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(versionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
        cleanup();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void linkTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_linkTextFieldKeyPressed
        /*int key = evt.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            if (_kcc == 0 || _kcc == 1) {
                _kcc++;
            } else {
                _kcc = -1;
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if (_kcc == 2 || _kcc == 3) {
                _kcc++;
            } else {
                _kcc = -1;
            }
        } else if (key == KeyEvent.VK_LEFT) {
            if (_kcc == 4 || _kcc == 6) {
                _kcc++;
            } else {
                _kcc = -1;
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (_kcc == 5 || _kcc == 7) {
                _kcc++;
            } else {
                _kcc = -1;
            }
        } else if (key == KeyEvent.VK_B) {
            if (_kcc == 8) {
                _kcc++;
            } else {
                _kcc = -1;
            }
        } else if (key == KeyEvent.VK_A) {
            if (_kcc == 9) {
                if (Spectacles.isRunningSolo()) {
                    JOptionPane.showMessageDialog(this,
                            ImageUtilities.loadImage("tool/spectacles/comp_team.jpg"),
                            "Where is the power button?",
                            JOptionPane.INFORMATION_MESSAGE);
                    linkTextField.setText("http://www.mocpages.com/moc.php/18463");
                    setTitle("If Thien, repeat.");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Nice try, but you'll need the standalone version.",
                            "Where is the power button?",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                _kcc = -1;
            }
        }*/
    }//GEN-LAST:event_linkTextFieldKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cleanup();
    }//GEN-LAST:event_formWindowClosing

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutDialog dialog = new AboutDialog(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private int _kcc;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField linkTextField;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables

}
