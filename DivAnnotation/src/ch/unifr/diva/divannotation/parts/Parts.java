/*
 * Copyright (c) 2016 - UniFr.
 * DIVA group, University of Fribourg, Switzerland.
 */

package ch.unifr.diva.divannotation.parts;

import ch.unifr.tei.facsimile.surfacegrp.TeiSurfaceGrpPart;
import ch.unifr.diva.divannotation.GUI;
import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * @author ms
 */
@SuppressWarnings("FieldCanBeLocal")
public class Parts extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(Parts.class);

    private GUI gui;
    private DefaultListModel<TeiSurfaceGrpPart> listModel = new DefaultListModel<>();

    /**
     * These fields are created by NetBeans Form Editor.
     * WARNING: Do NOT modify this code.
     * The content is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Fields">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<TeiSurfaceGrpPart> lstParts;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    /**
     * Creates new form Parts
     */
    public Parts(GUI g) {
        gui = g;
        initComponents();
        lstParts.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "Convert2Lambda", "Anonymous2MethodRef"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstParts = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        jLabel1.setText("Manuscript parts creation");

        lstParts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstParts.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPartsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstParts);

        jButton1.setText("Add a part");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(350, 350, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
//        gui.unlockTabs(2);
        listModel.clear();
        for (TeiSurfaceGrpPart p : gui.getTei().getFacsimile().getParts()) {
            listModel.addElement(p);
        }

        // Selecting memorized entry
        // or first entry by default (easier debugging)
        if (gui.part != null) {
            for (int i = 0; i < listModel.size(); i++) {
                if (gui.part == listModel.elementAt(i)) {
                    lstParts.setSelectedIndex(i);
                }
            }
        }
        if (listModel.size() > 0) {
            lstParts.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TeiSurfaceGrpPart p = gui.getTei().getFacsimile().addPart();
        listModel.addElement(p);
        lstParts.setSelectedIndex(listModel.size() - 1);
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lstPartsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPartsValueChanged
        if (lstParts.getSelectedValue() != null
                && lstParts.getSelectedValue() != gui.part) {
            gui.part = lstParts.getSelectedValue();
            gui.unlockTabs(3);
            gui.folio = null;
        }
    }//GEN-LAST:event_lstPartsValueChanged

}
