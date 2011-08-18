/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfigureRackFrame.java
 *
 * Created on Jul 7, 2011, 10:18:39 AM
 */

package org.clothocad.tool.batterboard;

/**
 *
 * @author rishi
 */
import java.lang.ref.WeakReference;
import javax.swing.*;
import java.util.*;
import org.clothocore.api.data.*;
import org.clothocore.api.core.Collector;
public class ConfigureRackFrame extends javax.swing.JFrame {

    /** Creates new form ConfigureRackFrame */
    public ConfigureRackFrame() {
        super("Configure New Rack");
       // initComponents();
        this.myInitComponent();
        addAuthor();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextRackName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxTemperature = new javax.swing.JComboBox();
        jComboBoxShelf = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBarCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxAuthor = new javax.swing.JComboBox();
        jButtonMakeRack = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("New Rack"); // NOI18N

        jTextRackName.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jTextRackName.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel4.text")); // NOI18N

        jComboBoxTemperature.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4 Deg.C", "-20 Deg.C", "-80 Deg.C" }));
        jComboBoxTemperature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTemperatureActionPerformed(evt);
            }
        });

        jComboBoxShelf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bin", "Top Shelf", "Middle Shelf", "Bottom Shelf" }));

        jLabel5.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel5.text")); // NOI18N

        jTextFieldBarCode.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jTextFieldBarCode.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel6.text")); // NOI18N

        jButtonMakeRack.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jButtonMakeRack.text")); // NOI18N
        jButtonMakeRack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMakeRackActionPerformed(evt);
            }
        });

        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jButtonCancel.text")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldBarCode)
                                    .addComponent(jTextRackName, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jComboBoxShelf, 0, 67, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonMakeRack)
                        .addGap(52, 52, 52)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextRackName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxShelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMakeRack)
                    .addComponent(jButtonCancel))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void myInitComponent()
    {
         jTextRackName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxTemperature = new javax.swing.JComboBox();
        jComboBoxShelf = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBarCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxAuthor = new javax.swing.JComboBox();
        jButtonMakeRack = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("New Rack"); // NOI18N

        jTextRackName.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jTextRackName.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel2.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel3.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel4.text")); // NOI18N

        jComboBoxTemperature.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4 Deg.C", "-20 Deg.C", "-80 Deg.C" }));
        jComboBoxTemperature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTemperatureActionPerformed(evt);
            }
        });

        jComboBoxShelf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bin", "Top Shelf", "Middle Shelf", "Bottom Shelf" }));

        jLabel5.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel5.text")); // NOI18N

        jTextFieldBarCode.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jTextFieldBarCode.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jLabel6.text")); // NOI18N

        jButtonMakeRack.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jButtonMakeRack.text")); // NOI18N
        jButtonMakeRack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMakeRackActionPerformed(evt);
            }
        });

        jButtonCancel.setText(org.openide.util.NbBundle.getMessage(ConfigureRackFrame.class, "ConfigureRackFrame.jButtonCancel.text")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldBarCode)
                                    .addComponent(jTextRackName, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jComboBoxShelf, 0, 67, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonMakeRack)
                        .addGap(52, 52, 52)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextRackName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxShelf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMakeRack)
                    .addComponent(jButtonCancel))
                .addGap(29, 29, 29))
        );

        pack();

    }

     private ArrayList<ObjLink> authorList;
     private ArrayList<ObjLink> platetypeList;

      private void addAuthor()
    {
        authorList = Collector.getAllLinksOf(ObjType.PERSON);
        Iterator i = authorList.iterator();
        while (i.hasNext())
            this.jComboBoxAuthor.addItem(i.next());


    }
    private void jComboBoxTemperatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTemperatureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTemperatureActionPerformed

    private void jButtonMakeRackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMakeRackActionPerformed
        // TODO add your handling code here:
         platetypeList = Collector.getAllLinksOf(ObjType.PLATE_TYPE);
        authorList = Collector.getAllLinksOf(ObjType.PERSON);
        int authorSelect = this.jComboBoxAuthor.getSelectedIndex();
       // int plateTypeSelect = this.jComboBoxPlateType.getSelectedIndex();
        int plateTypeSelect =1; //need to change this to "rack" type.
        ObjLink plateLink = platetypeList.get(plateTypeSelect);
        ObjLink personLink = authorList.get(authorSelect);
        Person testPerson = Collector.getPerson(personLink.uuid);
       // PlateType testType =Collector.getPlateType(plateLink.uuid);
        PlateType testType = PlateType.retrieveByName("RACK_9X9");
        if (testType == null)
        {
            JOptionPane.showMessageDialog(this,"The corresponding Rack type not found in DB! Please configure PlateType table in DB");
            return;
        }
        Plate testPlate = new Plate(this.jTextRackName.getText()+" (RACK)",testType,testPerson);
        String location = this.jComboBoxShelf.getSelectedItem().toString()+" : "+this.jComboBoxTemperature.getSelectedItem().toString();
        System.out.println("LOCATION :"+location);
        testPlate.setLocation(location);

        if (this.jTextFieldBarCode.getText()!=null)
            testPlate.setBarcode(this.jTextFieldBarCode.getText());

        boolean didWork = testPlate.saveDefault();
        System.out.println("write plate to DB:"+didWork);
        this.setVisible(false);
         frame aframe = new frame(testPlate);
        hub.guis.add(new WeakReference(aframe));
    }//GEN-LAST:event_jButtonMakeRackActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigureRackFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonMakeRack;
    private javax.swing.JComboBox jComboBoxAuthor;
    private javax.swing.JComboBox jComboBoxShelf;
    private javax.swing.JComboBox jComboBoxTemperature;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextFieldBarCode;
    private javax.swing.JTextField jTextRackName;
    // End of variables declaration//GEN-END:variables

}
