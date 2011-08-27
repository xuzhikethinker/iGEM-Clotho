/*
 * 
Copyright (c) 2010 The Regents of the University of California.
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
package org.clothocore.widget.fabdash;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.clothocore.api.core.Collator;

import org.clothocore.api.core.Collector;
import org.clothocore.api.core.wrapper.ConnectionWrapper;
import org.clothocore.api.core.wrapper.ToolWrapper;
import org.clothocore.api.data.Collection;
//import org.clothocore.api.data.Feature;
import org.clothocore.api.data.Format;
import org.clothocore.api.data.ObjLink;
import org.clothocore.api.data.ObjType;
import org.clothocore.api.data.Oligo;
import org.clothocore.api.data.Part;
import org.clothocore.api.data.Plasmid;
import org.clothocore.api.data.Vector;
//import org.clothocore.widget.fabdash.browser.ObjTypeChooser;
import org.clothocore.util.basic.ObjBasePopup;

import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.windows.Mode;

@ConvertAsProperties(dtd = "-//org.clothocore.widget.fabdash//Inventory//EN", autostore = false)
public final class InventoryTopComponent extends TopComponent {

    private static InventoryTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "InventoryTopComponent";

    public InventoryTopComponent() {

        initComponents();

        changeButton.setEnabled(false);
        localRadioButton.setSelected(true);
        configurableRadioButton.setSelected(false);
        statusLabel.setText("Not connected to a Clotho connection");
        currentUserLabel.setText("Not connected");
        setName(NbBundle.getMessage(InventoryTopComponent.class, "CTL_InventoryTopComponent"));
        setToolTipText(NbBundle.getMessage(InventoryTopComponent.class, "HINT_InventoryTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        partsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (partsTable.getSelectedRow() > 0 && partsTable.getSelectedRow() < partsTable.getHeight()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        _obp = new ObjBasePopup(partsTable, Part.retrieveByName((String) partsTable.getValueAt(partsTable.getSelectedRow(), 0)));
                    }
                }
            }
        });
        partsTable.setTransferHandler(new InventoryTransferHandler());
        partsTable.setDragEnabled(true);

        vectorsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (vectorsTable.getSelectedRow() > 0 && vectorsTable.getSelectedRow() < vectorsTable.getHeight()) {
                        _obp = new ObjBasePopup(vectorsTable, Vector.retrieveByName((String) vectorsTable.getValueAt(vectorsTable.getSelectedRow(), 0)));
                    }
                }
            }
        });
        vectorsTable.setTransferHandler(new InventoryTransferHandler());
        vectorsTable.setDragEnabled(true);

        plasmidsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (plasmidsTable.getSelectedRow() > 0 && plasmidsTable.getSelectedRow() < plasmidsTable.getHeight()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        _obp = new ObjBasePopup(plasmidsTable, Plasmid.retrieveByName((String) plasmidsTable.getValueAt(plasmidsTable.getSelectedRow(), 0)));
                    }
                }
            }
        });
        plasmidsTable.setTransferHandler(new InventoryTransferHandler());
        plasmidsTable.setDragEnabled(true);

        oligosTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (oligosTable.getSelectedRow() > 0 && oligosTable.getSelectedRow() < oligosTable.getHeight()) {
                        _obp = new ObjBasePopup(oligosTable, Oligo.retrieveByName((String) oligosTable.getValueAt(oligosTable.getSelectedRow(), 0)));
                    }
                }

            }
        });
        oligosTable.setTransferHandler(new InventoryTransferHandler());
        oligosTable.setDragEnabled(true);


    }

    public void changeObjType(ObjType type) {
        _chosenType = type;
        System.out.println(_chosenType);
    }

    private void refreshConnectionInformation() {
        if (Collector.isConnected()) {
            if (Collector.getCurrentUser() != null) {
                currentUserLabel.setText(Collector.getCurrentUser().getDisplayName());
                statusLabel.setText("Connected");
            }
        } else {
            currentUserLabel.setText("Not Connected");
            statusLabel.setText("Not connected to Clotho connection");
        }
    }

    private void fetchInventoryInformation() {
        TopComponent.Registry registry = TopComponent.getRegistry();
        Set toolSet = registry.getOpened();
        for (Object o : toolSet) {
            TopComponent tc = (TopComponent) o;
            if (!tc.getName().equals("Browser") && !tc.getName().equals("Output")) {
                tc.close();
            }
        }
        for (ToolWrapper tw : Collator.getAllTools()) {
            if (tw.getUUID().equals("org.clothocad.tool.toolappbrowser")) {
                tw.launchTool();
                break;
            }
        }


        new SwingWorker() {

            Collection personalCollection = null;

            @Override
            protected Object doInBackground() throws Exception {
                if (!Collector.isConnected()) {
                    statusLabel.setText("Must connect to connection before displaying inventory information");
                    return null;
                }
                try {
//                    personalCollection = Collector.getCurrentUser().getHerCollection();

                    //Populate the Plasmid tab is below
                    ArrayList<ObjLink> allPlasmids = Collector.getAllLinksOf(ObjType.PLASMID);
                    Object[][] plasmidTableModel = new Object[allPlasmids.size()][2];
                    if (allPlasmids.size() > 0) {
                        ObjBasePopup obp = new ObjBasePopup(plasmidsTable, Collector.getPlasmid(allPlasmids.get(0).uuid));
                    }
                    for (int i = 0; i < allPlasmids.size(); i++) {
                        plasmidTableModel[i][0] = allPlasmids.get(i).name;
                        Plasmid aplas = Collector.getPlasmid(allPlasmids.get(i).uuid);
                        Format aform = aplas.getFormat(); //get the Format of aplas
                        plasmidTableModel[i][1] = aform.getName(); //based on the Format, the sequence of the region of interest is retreieved and used to populate the table
                    }
                    plasmidsTable.setModel(new javax.swing.table.DefaultTableModel(plasmidTableModel, new String[]{"Plasmid Name", "Format"}) {

                        @Override
                        public boolean isCellEditable(int rowIndex, int mColIndex) {
                            return false;
                        }
                    });
                    plasmidsTable.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2) {
                                try {
                                    Plasmid aplas = Plasmid.retrieveByName((String) plasmidsTable.getValueAt(plasmidsTable.getSelectedRow(), 0));
//                        ObjBase obj = (ObjBase) _lister.getSelectedValue();
//                        obj.launchDefaultViewer();
                                    aplas.launchDefaultViewer();
                                } catch (Exception ex) {
                                }
                            }
                        }
                    });
                    //populate the Oligo tab
                    ArrayList<ObjLink> allOligos = Collector.getAllLinksOf(ObjType.OLIGO);
                    Object[][] oligoTableModel = new Object[allOligos.size()][2];
                    if (allOligos.size() > 0) {
                        ObjBasePopup obp = new ObjBasePopup(oligosTable, Collector.getOligo(allOligos.get(0).uuid));
                    }
                    for (int i = 0; i < allOligos.size(); i++) {
                        Oligo oligo = Collector.getOligo(allOligos.get(i).uuid);
                        oligoTableModel[i][0] = allOligos.get(i).name;
                        oligoTableModel[i][1] = oligo.getDescription();
                    }
                    oligosTable.setModel(new javax.swing.table.DefaultTableModel(oligoTableModel, new String[]{"Name", "Description"}) {

                        @Override
                        public boolean isCellEditable(int rowIndex, int mColIndex) {
                            return false;
                        }
                    });
                    oligosTable.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2) {
                                try {
                                    Oligo aol = Oligo.retrieveByName((String) oligosTable.getValueAt(oligosTable.getSelectedRow(), 0));
//                        ObjBase obj = (ObjBase) _lister.getSelectedValue();
//                        obj.launchDefaultViewer();
                                    aol.launchDefaultViewer();
                                } catch (Exception ex) {
                                }
                            }
                        }
                    });
                    //populate the Vectors tab is below
                    ArrayList<ObjLink> allVectors = Collector.getAllLinksOf(ObjType.VECTOR);
                    Object[][] vectorTableModel = new Object[allVectors.size()][2];
                    if (allVectors.size() > 0) {
                        ObjBasePopup obp = new ObjBasePopup(vectorsTable, Collector.getVector(allVectors.get(0).uuid));
                    }
                    for (int i = 0; i < allVectors.size(); i++) {
                        vectorTableModel[i][0] = allVectors.get(i).name;
                        Vector avec = Collector.getVector(allVectors.get(i).uuid);
                        vectorTableModel[i][1] = avec.getFormat().toString(); //based on the Format, the sequence of the region of interest is retreieved and used to populate the table
                    }
                    vectorsTable.setModel(new javax.swing.table.DefaultTableModel(vectorTableModel, new String[]{"Vector Name", "Format"}) {

                        @Override
                        public boolean isCellEditable(int rowIndex, int mColIndex) {
                            return false;
                        }
                    });
                    vectorsTable.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2) {
                                try {
                                    Vector avect = Vector.retrieveByName((String) vectorsTable.getValueAt(vectorsTable.getSelectedRow(), 0));
//                        ObjBase obj = (ObjBase) _lister.getSelectedValue();
//                        obj.launchDefaultViewer();
                                    avect.launchDefaultViewer();
                                } catch (Exception ex) {
                                }
                            }
                        }
                    });

                    //Code for populating the Parts tab is below
                    ArrayList<ObjLink> allParts = Collector.getAllLinksOf(ObjType.PART);
                    Object[][] partTableModel = new Object[allParts.size()][2];
                    if (allParts.size() > 0) {
                        ObjBasePopup obp = new ObjBasePopup(partsTable, Collector.getPart(allParts.get(0).uuid));
                    }
                    for (int i = 0; i < allParts.size(); i++) {
                        Part aPart = Collector.getPart(allParts.get(i).uuid);
                        partTableModel[i][0] = aPart.getName();
                        partTableModel[i][1] = aPart.getFormat().toString();
                    }
                    partsTable.setModel(new javax.swing.table.DefaultTableModel(partTableModel, new String[]{"Part Name", "Format"}) {

                        @Override
                        public boolean isCellEditable(int rowIndex, int mColIndex) {
                            return false;
                        }
                    });
                    partsTable.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2) {
                                try {
                                    Part apart = Part.retrieveByName((String) partsTable.getValueAt(partsTable.getSelectedRow(), 0));
//                        ObjBase obj = (ObjBase) _lister.getSelectedValue();
//                        obj.launchDefaultViewer();
                                    apart.launchDefaultViewer();
                                } catch (Exception ex) {
                                }
                            }
                        }
                    });
                    oligosTable.setEnabled(true);
                    partsTable.setEnabled(true);
                    plasmidsTable.setEnabled(true);
                    vectorsTable.setEnabled(true);



                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void done() {
//                browseTree.setModel( CollectionBrowser.generate(personalCollection));
//                browseTree.validate();
                repaint();
            }
        }.execute();


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        searchBar1 = new org.clothocore.widget.fabdash.browser.SearchBar();
        connectionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        currentUserLabel = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        localRadioButton = new javax.swing.JRadioButton();
        configurableRadioButton = new javax.swing.JRadioButton();
        changeButton = new javax.swing.JButton();
        inventoryPanel = new javax.swing.JPanel();
        inventoryTabbedPane = new javax.swing.JTabbedPane();
        oligosScrollPane = new javax.swing.JScrollPane();
        oligosTable = new javax.swing.JTable();
        partsScrollPane = new javax.swing.JScrollPane();
        partsTable = new javax.swing.JTable();
        vectorsScrollPane = new javax.swing.JScrollPane();
        vectorsTable = new javax.swing.JTable();
        plasmidsScrollPane = new javax.swing.JScrollPane();
        plasmidsTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        backgroundPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                backgroundPanelKeyTyped(evt);
            }
        });

        searchBar1.setMaximumSize(new java.awt.Dimension(900, 900));
        searchBar1.setMinimumSize(new java.awt.Dimension(150, 100));
        searchBar1.setPreferredSize(new java.awt.Dimension(150, 100));

        connectionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        connectionPanel.setMaximumSize(new java.awt.Dimension(900, 900));
        connectionPanel.setMinimumSize(new java.awt.Dimension(150, 100));
        connectionPanel.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 12));
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel1.text")); // NOI18N

        currentUserLabel.setFont(new java.awt.Font("Ubuntu", 0, 12));
        org.openide.awt.Mnemonics.setLocalizedText(currentUserLabel, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.currentUserLabel.text")); // NOI18N
        currentUserLabel.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.currentUserLabel.toolTipText")); // NOI18N
        currentUserLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        currentUserLabel.setMaximumSize(new java.awt.Dimension(80, 25));
        currentUserLabel.setMinimumSize(new java.awt.Dimension(80, 25));
        currentUserLabel.setPreferredSize(new java.awt.Dimension(80, 25));

        connectButton.setFont(new java.awt.Font("Ubuntu", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(connectButton, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.connectButton.text")); // NOI18N
        connectButton.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.connectButton.toolTipText")); // NOI18N
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        statusLabel.setFont(new java.awt.Font("Ubuntu", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(statusLabel, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.statusLabel.text")); // NOI18N
        statusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jPanel1.toolTipText")); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(250, 50));
        jPanel1.setMinimumSize(new java.awt.Dimension(250, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 50));

        localRadioButton.setFont(new java.awt.Font("Ubuntu", 0, 12));
        org.openide.awt.Mnemonics.setLocalizedText(localRadioButton, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.localRadioButton.text")); // NOI18N
        localRadioButton.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.localRadioButton.toolTipText")); // NOI18N
        localRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localRadioButtonActionPerformed(evt);
            }
        });

        configurableRadioButton.setFont(new java.awt.Font("Ubuntu", 0, 12));
        org.openide.awt.Mnemonics.setLocalizedText(configurableRadioButton, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.configurableRadioButton.text")); // NOI18N
        configurableRadioButton.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.configurableRadioButton.toolTipText")); // NOI18N
        configurableRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configurableRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(localRadioButton)
                    .addComponent(configurableRadioButton))
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(configurableRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(localRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        changeButton.setFont(new java.awt.Font("Ubuntu", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(changeButton, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.changeButton.text")); // NOI18N
        changeButton.setToolTipText(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.changeButton.toolTipText")); // NOI18N
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connectionPanelLayout = new javax.swing.GroupLayout(connectionPanel);
        connectionPanel.setLayout(connectionPanelLayout);
        connectionPanelLayout.setHorizontalGroup(
            connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, connectionPanelLayout.createSequentialGroup()
                        .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, connectionPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(changeButton)
                            .addComponent(connectButton))))
                .addGap(29, 29, 29))
        );

        connectionPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {changeButton, connectButton});

        connectionPanelLayout.setVerticalGroup(
            connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionPanelLayout.createSequentialGroup()
                .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connectionPanelLayout.createSequentialGroup()
                        .addGroup(connectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(currentUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(connectionPanelLayout.createSequentialGroup()
                        .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        inventoryTabbedPane.setMaximumSize(new java.awt.Dimension(900, 900));
        inventoryTabbedPane.setMinimumSize(new java.awt.Dimension(150, 100));
        inventoryTabbedPane.setName("inventoryTabs"); // NOI18N
        inventoryTabbedPane.setPreferredSize(new java.awt.Dimension(300, 300));

        oligosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Identifier", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        oligosTable.setDragEnabled(true);
        oligosTable.setEnabled(false);
        oligosTable.setFillsViewportHeight(true);
        oligosTable.setName("oligosTable"); // NOI18N
        oligosTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        oligosScrollPane.setViewportView(oligosTable);
        oligosTable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.oligosTable.AccessibleContext.accessibleName")); // NOI18N
        oligosTable.getAccessibleContext().setAccessibleParent(inventoryTabbedPane);

        inventoryTabbedPane.addTab(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.oligosScrollPane.TabConstraints.tabTitle_1"), oligosScrollPane); // NOI18N

        partsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Identifier", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        partsTable.setDragEnabled(true);
        partsTable.setEnabled(false);
        partsTable.setFillsViewportHeight(true);
        partsTable.setName("partsTable"); // NOI18N
        partsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        partsScrollPane.setViewportView(partsTable);
        partsTable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.partsTable.AccessibleContext.accessibleName")); // NOI18N

        inventoryTabbedPane.addTab(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.partsScrollPane.TabConstraints.tabTitle"), partsScrollPane); // NOI18N

        vectorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Identifier", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vectorsTable.setDragEnabled(true);
        vectorsTable.setEnabled(false);
        vectorsTable.setFillsViewportHeight(true);
        vectorsTable.setName("vectorsTable"); // NOI18N
        vectorsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vectorsScrollPane.setViewportView(vectorsTable);
        vectorsTable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.vectorsTable.AccessibleContext.accessibleName")); // NOI18N

        inventoryTabbedPane.addTab(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.vectorsScrollPane.TabConstraints.tabTitle"), vectorsScrollPane); // NOI18N

        plasmidsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Identifier", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        plasmidsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        plasmidsTable.setDragEnabled(true);
        plasmidsTable.setEnabled(false);
        plasmidsTable.setFillsViewportHeight(true);
        plasmidsTable.setName("plasmidsTable"); // NOI18N
        plasmidsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        plasmidsScrollPane.setViewportView(plasmidsTable);
        plasmidsTable.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.plasmidsTable.AccessibleContext.accessibleName")); // NOI18N

        inventoryTabbedPane.addTab(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.plasmidsScrollPane.TabConstraints.tabTitle"), plasmidsScrollPane); // NOI18N

        refreshButton.setFont(new java.awt.Font("Ubuntu", 0, 10));
        org.openide.awt.Mnemonics.setLocalizedText(refreshButton, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.refreshButton.text")); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventoryTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(refreshButton)
                .addContainerGap())
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        inventoryTabbedPane.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.inventoryTabbedPane.AccessibleContext.accessibleName")); // NOI18N
        inventoryTabbedPane.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.inventoryTabbedPane.AccessibleContext.accessibleDescription")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/clothocore/widget/fabdash/browser/logo.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(InventoryTopComponent.class, "InventoryTopComponent.jLabel2.text")); // NOI18N
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 100));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 100));

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                    .addComponent(inventoryPanel, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(connectionPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
                .addGap(12, 12, 12))
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(connectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(backgroundPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        statusLabel.setText("Connecting...");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                if (localRadioButton.isSelected()) {
                    String selectstring = "org.clothocad.connection.localconnection";
                    ConnectionWrapper cw = (ConnectionWrapper) Collator.getPluginByUUID(selectstring);
                    Collator.setDefaultConnection(cw);
                    Collector.connectToDefault();
                    fetchInventoryInformation();
                    refreshConnectionInformation();
                    statusLabel.setText("Connected to Local Connection");
                } else if (configurableRadioButton.isSelected()) {
                    String selectstring = "org.clothocad.connection.configurableconnection-ClothoConnection";
                    ConnectionWrapper cw = (ConnectionWrapper) Collator.getPluginByUUID(selectstring);
                    Collator.setDefaultConnection(cw);
                    Collector.connectToDefault();
                    fetchInventoryInformation();
                    refreshConnectionInformation();
                    statusLabel.setText("Connected to Configurable Connection");
                }
                if (Collector.isConnected()) {
                    localRadioButton.setEnabled(false);
                    configurableRadioButton.setEnabled(false);
                    connectButton.setEnabled(false);
                    jPanel1.setEnabled(false);
                    changeButton.setEnabled(true);
                }
            }
        });

    }//GEN-LAST:event_connectButtonActionPerformed

    private void configurableRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configurableRadioButtonActionPerformed
        localRadioButton.setSelected(false);

    }//GEN-LAST:event_configurableRadioButtonActionPerformed

    private void localRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localRadioButtonActionPerformed
        configurableRadioButton.setSelected(false);
    }//GEN-LAST:event_localRadioButtonActionPerformed

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        if (Collector.isConnected()) {
            UserFrame uf = new UserFrame(currentUserLabel);
            uf.pack();
            uf.setVisible(true);
        }
    }//GEN-LAST:event_changeButtonActionPerformed

    private void backgroundPanelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backgroundPanelKeyTyped
        System.out.println("char: " + evt.getKeyChar());
    }//GEN-LAST:event_backgroundPanelKeyTyped

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        fetchInventoryInformation();
    }//GEN-LAST:event_refreshButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton changeButton;
    private javax.swing.JRadioButton configurableRadioButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JPanel connectionPanel;
    private javax.swing.JLabel currentUserLabel;
    private javax.swing.JPanel inventoryPanel;
    protected javax.swing.JTabbedPane inventoryTabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton localRadioButton;
    protected javax.swing.JScrollPane oligosScrollPane;
    protected javax.swing.JTable oligosTable;
    private javax.swing.JScrollPane partsScrollPane;
    protected javax.swing.JTable partsTable;
    private javax.swing.JScrollPane plasmidsScrollPane;
    protected javax.swing.JTable plasmidsTable;
    private javax.swing.JButton refreshButton;
    private org.clothocore.widget.fabdash.browser.SearchBar searchBar1;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JScrollPane vectorsScrollPane;
    protected javax.swing.JTable vectorsTable;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized InventoryTopComponent getDefault() {
        if (instance == null) {
            instance = new InventoryTopComponent();
        }

        return instance;
    }

    /**
     * Obtain the InventoryTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized InventoryTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(InventoryTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof InventoryTopComponent) {
            return (InventoryTopComponent) win;
        }
        Logger.getLogger(InventoryTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    //Called to populate the tree
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }

        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
    ///////////////////////////////////////////////////////////////////
    ////                      private variables                    ////
    private ObjType _chosenType = ObjType.PART;
    private ObjBasePopup _obp;
}
