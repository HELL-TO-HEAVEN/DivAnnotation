/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.unifr.diva.divannotation.textzone;

import ch.unifr.diva.divaservices.DivaServicesCommunicator;
import ch.unifr.diva.divaservices.returntypes.DivaServicesResponse;
import ch.unifr.tei.TeiHisDoc;
import ch.unifr.tei.facsimile.surfacegrp.surface.TeiGraphic;
import ch.unifr.tei.facsimile.surfacegrp.surface.TeiSurface;
import ch.unifr.tei.facsimile.surfacegrp.surface.zone.TeiTextSegZone;
import ch.unifr.tei.facsimile.surfacegrp.surface.zone.TeiTextZone;
import ch.unifr.tei.facsimile.surfacegrp.surface.zone.TeiZone;
import ch.unifr.tei.teiheader.filedesc.editionstmt.respstmt.TeiRespStmt;
import ch.unifr.tei.text.body.div.div.ab.TeiAb;
import ch.unifr.tei.tools.TranscriptionManager;
import ch.unifr.tei.tools.exception.InvalidTranscriptionException;
import ch.unifr.tei.utils.TeiArea;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.JDOMException;

/**
 *
 * @author ms
 */
public class OcrFrame extends javax.swing.JFrame {
    protected List<TeiZone> zones;
    protected TeiSurface surface;
    protected TeiHisDoc tei;
    
    /**
     * Creates new form OcrFrame
     */
    public OcrFrame(TeiHisDoc tei, TeiSurface surface, List<TeiZone> zones) {
        this.surface = surface;
        this.zones = zones;
        this.tei = tei;
        initComponents();
        
        this.modelList.setSelectedIndex(0);
        
        this.cbImages.removeAllItems();
        for (TeiGraphic g : surface.getGraphics()) {
            this.cbImages.addItem(g.getDescription());
        }
        
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modelList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        cbImages = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        octButton = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Running Ocropy on selected data");

        modelList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "English", "Fraktur", "Bentham", "BenthamEN" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(modelList);

        jLabel2.setText("Language model:");

        cbImages.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Select binary image");

        octButton.setText("Run OCR");
        octButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octButtonActionPerformed(evt);
            }
        });

        txtRes.setColumns(20);
        txtRes.setRows(5);
        jScrollPane3.setViewportView(txtRes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(octButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jScrollPane1)
                                .addComponent(jLabel2)
                                .addComponent(cbImages, 0, 449, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbImages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(octButton))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void octButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octButtonActionPerformed
        // Selecting the image
        TeiGraphic graphic = null;
        for (TeiGraphic g : surface.getGraphics()) {
            if (g.getDescription().equals(cbImages.getSelectedItem())) {
                graphic = g;
                break;
            }
        }
        BufferedImage bi;
        try {
            bi = graphic.loadImage();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load image. Reason:\n"+ex.getMessage());
            return;
        }
        
        this.octButton.setEnabled(false);
        
        List<TeiTextSegZone> segZones = new LinkedList<>();
        for (TeiZone z : zones) {
            if (z instanceof TeiTextSegZone) {
                if (((TeiTextSegZone) z).getTranscription()==null) {
                    segZones.add((TeiTextSegZone)z);
                }
            } else if (z instanceof TeiTextZone) {
                TeiTextZone zz = (TeiTextZone)z;
                for (TeiTextSegZone zzz : zz.getTextSegZones()) {
                    if (zzz.getTranscription()==null) {
                        segZones.add(zzz);
                    }
                }
            }
        }
        System.out.println(segZones.size()+" tei text seg zones");
        progress.setMaximum(segZones.size());
        progress.setValue(0);
        
        new Thread(new Runnable(){
            TeiRespStmt baseResp = tei.getCurrentResponsible();
            public void run() {
                TeiRespStmt stmt = TeiRespStmt.method("Ocropy", "Automatic OCR", "Transcription of text lines");
                tei.setRespStmt(stmt);
                int n = 0;
                txtRes.setText(segZones.size()+" lines to transcribe\n");
                for (TeiTextSegZone z : segZones) {
                    progress.setValue(n++);
                    txtRes.setText(txtRes.getText()+"Line "+n+"/"+segZones.size()+":");
                    
                    BufferedImage tmp = z.getArea().extractZoneImage(bi, TeiArea.ExtractionMethod.POLYGON);
                    
                    BufferedImage patch = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_RGB);
                    patch.getGraphics().drawImage(tmp, 0, 0, tmp.getWidth(), tmp.getHeight(), 0, 0, tmp.getWidth(), tmp.getHeight(), null);
                    
                    
                    DivaServicesCommunicator dsc = new DivaServicesCommunicator("http://divaservices.unifr.ch/api/v1");
                    DivaServicesResponse<Object> res = null;
                    try {
                        res = dsc.runOcropyTextExtraction2(modelList.getSelectedValue(), patch);
                    } catch (Throwable e) {
                        txtRes.setText(txtRes.getText()+"Error\n");
                        continue;
                    }
                    String txt = (String)res.getOutput().get("recognition");
                    txtRes.setText(txtRes.getText()+txt+"\n");
                    
                    tei.setRespStmt(stmt);
                    z.initializeTranscription();
                    TeiAb trans = z.getTranscription();
                    
                    TranscriptionManager tm = new TranscriptionManager();
                    TeiAb newAb = null;
                    try {
                        newAb = tm.getStructuredAB(trans.getParent(), txt);
                    } catch (JDOMException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid XML content.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        tei.setRespStmt(baseResp);
                        return;
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Input/output error.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        tei.setRespStmt(baseResp);
                        return;
                    } catch (InvalidTranscriptionException ex) {
                        JOptionPane.showMessageDialog(null, "The transcription is not valid.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        tei.setRespStmt(baseResp);
                        return;
                    }
                    newAb.setFacs(trans.getFacs());

                    trans.getParent().replaceAb(trans, newAb);
                    z.setTranscription(newAb);
                    tei.setRespStmt(baseResp);
                }
                progress.setValue(progress.getMaximum());
            }
        }).start();
    }//GEN-LAST:event_octButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbImages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> modelList;
    private javax.swing.JButton octButton;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTextArea txtRes;
    // End of variables declaration//GEN-END:variables
}
