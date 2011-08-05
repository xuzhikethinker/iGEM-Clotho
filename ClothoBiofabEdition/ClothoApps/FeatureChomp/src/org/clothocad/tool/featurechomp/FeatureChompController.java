/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothocad.tool.featurechomp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.clothocore.api.core.Collector;
import org.clothocore.api.data.Collection;
import org.clothocore.api.data.Feature;
import org.clothocore.api.data.ObjLink;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;

/**
 *
 * @author jenhan
 */
public class FeatureChompController {

    public FeatureChompController(JFrame frame) {
        _frameView = frame;
        _isTC = false;
    }
    private TopComponent _tcView;
    private JFrame _frameView;
    private boolean _isTC;


    public void importGenbankFeatures() {
        try {
            java.io.BufferedReader inFile = null;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter genbankFilter = new FileNameExtensionFilter("GenBank File", "gen", "gb", "gbank", "genbank");
            chooser.addChoosableFileFilter(genbankFilter);
            chooser.showOpenDialog(null);
            if (chooser.getSelectedFile() != null) {
                inFile = new java.io.BufferedReader(new java.io.FileReader(chooser.getSelectedFile()));
                String line = inFile.readLine();
                if (line.startsWith("LOCUS")) {
                    ArrayList<String> featureLines = new ArrayList(); //holds lines that contain feature information; used to generate new features after sequence is parsed
                    String area = "";
                    String toComments = "";
                    String sequence = "";
                    while (line != null) {
                        if (line.startsWith("   ")) {
                            if (area.equals("COMMENT")) {
                                toComments = toComments + line.substring(12, line.length());
                            }
                        }
                        if (line.startsWith("COMMENT")) {
                            String comment = line.substring(12, line.length());
                            area = "COMMENT";
                            // FIXME
                            // Do something with ApE methylation data if present
                            if (comment.startsWith("ApEinfo:methylated")) {
                                String meth = line.substring(comment.length() - 1, comment.length());
                            } else {
                                toComments = toComments + comment;
                            }
                        }
                        if (line.startsWith("FEATURES")) {
                            area = "FEATURES";
                            line = inFile.readLine().trim();
                            while (!(line.startsWith("ORIGIN"))) {
                                if (!(line.startsWith("//")) && !(line.startsWith("SOURCE"))) {
                                    featureLines.add(line);
                                }
                                line = inFile.readLine().trim();
                            }
                        }
                        if (line.startsWith("ORIGIN")) {
                            line = inFile.readLine().trim();
                            while (!(line.startsWith("//"))) {
                                ArrayList<String> seq = new ArrayList(Arrays.asList(line.split(" ")));
                                for (int i = 1; i < seq.size(); i++) {
                                    sequence = sequence + seq.get(i);
                                }
                                line = inFile.readLine().trim();
                            }
                        }
                        line = inFile.readLine();
                    }
                    new ImportFeaturesWindow("genbank", sequence, this, featureLines);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Selected file does not appear to be a genbank file", "Sequence View Import", JOptionPane.ERROR_MESSAGE);
                }
                inFile.close();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void importApEFeatures() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        if (chooser.getSelectedFile() != null) {
            try {
                java.io.BufferedReader inputFile = new java.io.BufferedReader(new java.io.FileReader(chooser.getSelectedFile()));
                String line = inputFile.readLine();
                ArrayList<String> featureLines = new ArrayList();
                while (line != null) {
                    featureLines.add(line);
                    line = inputFile.readLine();
                }
                if (featureLines.size() > 0) {
                    new ImportFeaturesWindow("ApE", "", this, featureLines);
                } else {
                    JOptionPane.showMessageDialog(null, "File does not appear to be a valid ApE feature file", "Sequence View Import", JOptionPane.ERROR_MESSAGE);
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }


    }






    
    public void switchViews() {
        if (_isTC) {
            Component[] components = _tcView.getComponents();
            _frameView = new JFrame();
            _frameView.setContentPane((Container) components[1]);
            _frameView.setJMenuBar((JMenuBar) components[0]);
            _frameView.pack();
            _frameView.setVisible(true);
            _isTC = false;
            _tcView.close();
        } else {
            final JComponent guiContentPane = (JComponent) _frameView.getContentPane();
            final JMenuBar menu = _frameView.getJMenuBar();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    _tcView = new TopComponent();
                    _tcView.setLayout(new BorderLayout());
                    JScrollPane sp = new JScrollPane(guiContentPane);
                    _tcView.add(menu, BorderLayout.NORTH);
                    _tcView.add(sp, BorderLayout.CENTER);
                    _tcView.setName("Feature Chomp");
                    _tcView.open();
                    _tcView.requestActive();

                }
            });
            _frameView.dispose();
            _isTC = true;
        }
    }

    public void close() {
    if (_isTC) {
        _tcView.close();
    } else {
        _frameView.dispose();
    }
    }
}
