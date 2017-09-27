/*
 * Copyright (c) 2016 - UniFr.
 * DIVA group, University of Fribourg, Switzerland.
 */

package ch.unifr.diva.divannotation.foliospages;

import ch.unifr.diva.divaservices.DivaServicesCommunicator;
import ch.unifr.diva.divaservices.returntypes.DivaServicesResponse;
import ch.unifr.tei.facsimile.surfacegrp.TeiSurfaceGrpFolio;
import ch.unifr.tei.facsimile.surfacegrp.surface.TeiGraphic;
import ch.unifr.tei.facsimile.surfacegrp.surface.TeiSurface;
import ch.unifr.diva.divannotation.GUI;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Priority;

/**
 * @author ms
 */
@SuppressWarnings("FieldCanBeLocal")
public class FoliosAndPages extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(FoliosAndPages.class);

    private GUI gui;

    private DefaultListModel<TeiSurfaceGrpFolio> folios = new DefaultListModel<>();
    private DefaultListModel<TeiSurface> pages = new DefaultListModel<>();
    private DefaultListModel<TeiGraphic> graphics = new DefaultListModel<>();

    /**
     * These fields are created by NetBeans Form Editor.
     * WARNING: Do NOT modify this code.
     * The content is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Fields">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFolioButton;
    private javax.swing.JButton addGraphicButton;
    private javax.swing.JButton addPageButton;
    private javax.swing.JButton btnDoG;
    private javax.swing.JButton btnLoadPage;
    private javax.swing.JButton btnOtsu;
    private javax.swing.JList<TeiSurfaceGrpFolio> folioList;
    private javax.swing.JList<TeiGraphic> graphicList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jtfDescr;
    private javax.swing.JTextField jtfPageNumber;
    private javax.swing.JTextField jtfUrl;
    private javax.swing.JButton openGraphicButton;
    private javax.swing.JList<TeiSurface> pageList;
    private javax.swing.JButton updateGraphicButton;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    /**
     * Creates new form FoliosAndPages
     */
    public FoliosAndPages(GUI gui) {
        this.gui = gui;

        initComponents();

        folioList.setModel(folios);
        pageList.setModel(pages);
        graphicList.setModel(graphics);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "Convert2Lambda", "Anonymous2MethodRef"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        folioList = new javax.swing.JList<>();
        addFolioButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pageList = new javax.swing.JList<>();
        addPageButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        graphicList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfDescr = new javax.swing.JTextField();
        jtfUrl = new javax.swing.JTextField();
        updateGraphicButton = new javax.swing.JButton();
        openGraphicButton = new javax.swing.JButton();
        addGraphicButton = new javax.swing.JButton();
        btnOtsu = new javax.swing.JButton();
        btnLoadPage = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtfPageNumber = new javax.swing.JTextField();
        btnDoG = new javax.swing.JButton();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setText("Folios & pages management");

        folioList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        folioList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                folioListPropertyChange(evt);
            }
        });
        folioList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                folioListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(folioList);

        addFolioButton.setText("Add a folio");
        addFolioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFolioButtonActionPerformed(evt);
            }
        });

        pageList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pageList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pageListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(pageList);

        addPageButton.setText("Add a page");
        addPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPageButtonActionPerformed(evt);
            }
        });

        graphicList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        graphicList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                graphicListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(graphicList);

        jLabel2.setText("Description:");

        jLabel3.setText("Address:");

        jtfDescr.setText("image description");

        jtfUrl.setText("file:// or http://");

        updateGraphicButton.setText("Update selected graphic");
        updateGraphicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGraphicButtonActionPerformed(evt);
            }
        });

        openGraphicButton.setText("Open");
        openGraphicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openGraphicButtonActionPerformed(evt);
            }
        });

        addGraphicButton.setText("Add new graphic");
        addGraphicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGraphicButtonActionPerformed(evt);
            }
        });

        btnOtsu.setText("Apply Otsu binarization");
        btnOtsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtsuActionPerformed(evt);
            }
        });

        btnLoadPage.setText("Load from Page.xml");
        btnLoadPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadPageActionPerformed(evt);
            }
        });

        jLabel4.setText("Page number:");

        jtfPageNumber.setToolTipText("It is recommended to use digits");
        jtfPageNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfPageNumberKeyTyped(evt);
            }
        });

        btnDoG.setText("Apply DoG binarization");
        btnDoG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addPageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(addFolioButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jtfUrl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(openGraphicButton))
                        .addComponent(jtfDescr)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addGraphicButton)
                            .addGap(18, 18, 18)
                            .addComponent(updateGraphicButton))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOtsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoadPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLoadPage)
                        .addGap(18, 18, 18)
                        .addComponent(btnOtsu)
                        .addGap(18, 18, 18)
                        .addComponent(btnDoG)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addFolioButton)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(openGraphicButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPageButton)
                    .addComponent(updateGraphicButton)
                    .addComponent(addGraphicButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfPageNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
//        gui.unlockTabs(3);
        updateFolioData();
        updatePageData();
        updateGraphics();
        gui.updateSharedPanelItems();
    }//GEN-LAST:event_formFocusGained

    private void folioListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_folioListPropertyChange
        logger.debug("folioListPropertyChange"); // not used…
        gui.folio = folioList.getSelectedValue();
        if (gui.folio == null) {
            return;
        }
        updatePageData();
    }//GEN-LAST:event_folioListPropertyChange

    private void addFolioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFolioButtonActionPerformed
        folios.addElement(gui.part.addFolio());
        folioList.setSelectedIndex(folios.size() - 1);
    }//GEN-LAST:event_addFolioButtonActionPerformed

    private void addPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPageButtonActionPerformed
        if (gui.folio == null) {
            return;
        }
        pages.addElement(gui.folio.addPage());
        pageList.setSelectedIndex(pages.size() - 1);
    }//GEN-LAST:event_addPageButtonActionPerformed

    private void addGraphicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGraphicButtonActionPerformed
        if (gui.folio == null || gui.page == null) {
            return;
        }
        TeiGraphic g = gui.page.addGraphic(jtfUrl.getText(), jtfDescr.getText());
        graphics.addElement(g);
        graphicList.setSelectedIndex(graphics.size() - 1);
    }//GEN-LAST:event_addGraphicButtonActionPerformed

    private void folioListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_folioListValueChanged
        if (folioList.getSelectedValue() != null
                && folioList.getSelectedValue() != gui.folio) {
            gui.folio = folioList.getSelectedValue();
            gui.page = null;
            updatePageData();
        }
    }//GEN-LAST:event_folioListValueChanged

    private void pageListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pageListValueChanged
        if (pageList.getSelectedValue() != null
                && pageList.getSelectedValue() != gui.page) {
            gui.page = pageList.getSelectedValue();
            gui.graphic = null;
            updateGraphics();
            jtfPageNumber.setText(gui.page.getPageNum());
            logger.debug("Setting page number text to " + gui.page.getPageNum());
        }
    }//GEN-LAST:event_pageListValueChanged

    private void graphicListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_graphicListValueChanged
        if (graphicList.getSelectedValue() != null) {
//                && graphicList.getSelectedValue() != gui.graphic) {
            gui.graphic = graphicList.getSelectedValue();
            this.jtfDescr.setText(gui.graphic.getDescription());
            this.jtfUrl.setText(gui.graphic.getURL());
            gui.unlockTabs(5);
        }
    }//GEN-LAST:event_graphicListValueChanged

    private void updateGraphicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateGraphicButtonActionPerformed
        if (gui.graphic == null) {
            return;
        }
        gui.graphic.setURL(jtfUrl.getText());
        gui.graphic.setDescription(jtfDescr.getText());
        updateGraphics();
    }//GEN-LAST:event_updateGraphicButtonActionPerformed

    private void openGraphicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openGraphicButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(gui.currentDirectory);

        int c = chooser.showOpenDialog(this);
        if (c == 1) {
            return;
        }

        File f = chooser.getSelectedFile();
        String relative = gui.currentDirectory.toURI().relativize(f.toURI()).getPath();

        this.jtfUrl.setText(relative);
    }//GEN-LAST:event_openGraphicButtonActionPerformed

    private void btnOtsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtsuActionPerformed
        TeiGraphic g = graphicList.getSelectedValue();
        if (g == null) {
            return;
        }

        BufferedImage input;
        try {
            input = g.loadImage();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not load the image from the selected graphic\n" + g.getURL() + "\n"
                            + "Reason given:\n" + ex.getMessage()
                    , "Loading error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        DivaServicesCommunicator communicator = new DivaServicesCommunicator("http://divaservices.unifr.ch/api/v1");
        DivaServicesResponse<Object> result = communicator.runOtsuBinarization(input, true);
        BufferedImage output = result.getImage();

        // Turning .../image.xyz into .../image-otsu.png
        // TODO: use regular expressions
        String sb = gui.currentDirectory.getAbsolutePath() +
                File.separator +
                g.getURL().substring(0, g.getURL().length() - 4) +
                "-otsu.png";

        // Path relative to the TEI location
        //String outputFilename = gui.currentDirectory.toURI().relativize(new File(sb).toURI()).getPath();
        String outputFilename = gui.currentDirectory.getPath()+File.separator+g.getURL().substring(0, g.getURL().length() - 4) + "-otsu.png";
        logger.log(Priority.DEBUG, "Saving otsu binary image as "+outputFilename);
        try {
            ImageIO.write(output, "png", new File(outputFilename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not save the image as " + outputFilename + "\n"
                            + "Reason given:\n" + ex.getMessage()
                    , "Saving error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        String relative = gui.currentDirectory.toURI().relativize(new File(outputFilename).toURI()).getPath();

        TeiGraphic newG = gui.page.addGraphic(relative, "Otsu binarization of \""+g.getDescription()+"\"");
        graphics.addElement(newG);
        updateGraphics();
        repaint();
    }//GEN-LAST:event_btnOtsuActionPerformed

    private void btnLoadPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPageActionPerformed
        try {
            loadFromPage();
        } catch (JDOMException ex) {
            JOptionPane.showMessageDialog(null, "The XML file is not valid:\n" + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The XML file could not be read:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnLoadPageActionPerformed

    @SuppressWarnings("Convert2Lambda")
    private void jtfPageNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPageNumberKeyTyped
        // Must be invoked later to make sure that the getText() method
        // returns the updated value.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (gui.page != null) {
                    String num = jtfPageNumber.getText();
                    if (num.equals("")) {
                        num = null;
                    }
                    gui.page.setPageNum(num);
                    logger.debug("Page number set to " + gui.page.getPageNum());
                }
            }
        });

    }//GEN-LAST:event_jtfPageNumberKeyTyped

    private void btnDoGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoGActionPerformed
        TeiGraphic g = graphicList.getSelectedValue();
        if (g == null) {
            return;
        }

        BufferedImage input;
        try {
            input = g.loadImage();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not load the image from the selected graphic\n" + g.getURL() + "\n"
                            + "Reason given:\n" + ex.getMessage()
                    , "Loading error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        DoGWindow win = new DoGWindow(input, gui);
        if (win.result==null) {
            return;
        }
        System.out.println("Binarization result received");
        
        // Path relative to the TEI location
        //String outputFilename = gui.currentDirectory.toURI().relativize(new File(sb).toURI()).getPath();
        String outputFilename = null;
        outputFilename = gui.currentDirectory.getPath()+File.separator+g.getURL().substring(0, g.getURL().length() - 4) + "-DoG.png";

        logger.log(Priority.DEBUG, "Saving otsu binary image as "+outputFilename);
        try {
            ImageIO.write(win.result, "png", new File(outputFilename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not save the image as " + outputFilename + "\n"
                            + "Reason given:\n" + ex.getMessage()
                    , "Saving error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        String relative = gui.currentDirectory.toURI().relativize(new File(outputFilename).toURI()).getPath();

        TeiGraphic newG = gui.page.addGraphic(relative, "DoG binarization of \""+g.getDescription()+"\"");
        graphics.addElement(newG);
        updateGraphics();
        repaint();
    }//GEN-LAST:event_btnDoGActionPerformed

    private void updateFolioData() {
        // Folio list
        folios.clear();
        for (TeiSurfaceGrpFolio folio : gui.part.getFolios()) {
            folios.addElement(folio);
        }

        // Selecting memorized entry
        // or first entry by default (easier debugging)
        if (gui.folio != null) {
            for (int i = 0; i < folios.size(); i++) {
                if (gui.folio == folios.elementAt(i)) {
                    folioList.setSelectedIndex(i);
                }
            }
        } else if (folios.size() > 0) {
            folioList.setSelectedIndex(0);
        }
    }

    private void updatePageData() {
        // Page list
        pages.clear();
        if (gui.folio == null) {
            return;
        }
        for (TeiSurface s : gui.folio) {
            pages.addElement(s);
        }

        // Selecting memorized entry
        // or first entry by default (easier debugging)
        if (gui.page != null) {
            for (int i = 0; i < pages.size(); i++) {
                if (gui.page == pages.elementAt(i)) {
                    pageList.setSelectedIndex(i);
                }
            }
        } else if (pages.size() > 0) {
            pageList.setSelectedIndex(0);
        }
    }

    private void updateGraphics() {
        graphics.clear();
        jtfDescr.setText("");
        jtfUrl.setText("");
        if (gui.page == null) {
            return;
        }
        for (TeiGraphic g : gui.page.getGraphics()) {
            graphics.addElement(g);
        }

        // Selecting memorized entry
        // or first entry by default (easier debugging)
        if (gui.graphic != null) {
            for (int i = 0; i < graphics.size(); i++) {
                if (gui.graphic == graphics.elementAt(i)) {
                    graphicList.setSelectedIndex(i);
                }
            }
        } else if (!graphics.isEmpty()) {
            graphicList.setSelectedIndex(0);
        }
    }

    private void loadFromPage() throws JDOMException, IOException {
        if (gui.page == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select first a page.");
            return;
        }

        if (!gui.page.getGraphics().isEmpty() || !gui.page.getTextZones().isEmpty()) {
            //Custom button text
            Object[] options = {"Yes, remove existing data",
                    "No, do not remove it"};
            int n = JOptionPane.showOptionDialog(this,
                    "The page has already some content. Loading data from a Page file"
                            + "will remove it first. Do you want to proceed ?",
                    "A Silly Question",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (n == JOptionPane.NO_OPTION) {
                return;
            }
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(gui.currentDirectory);

        int c = chooser.showOpenDialog(this);
        if (c == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File f = chooser.getSelectedFile();
        String pagePath = f.toURI().getPath();

        ch.unifr.tei.tools.PageImporter.replaceSurfaceContent(gui.page, pagePath, "todo");

        logger.debug("Loading the following page file: " + pagePath);
    }


}
