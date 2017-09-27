/*
 * Copyright (c) 2016 - UniFr.
 * DIVA group, University of Fribourg, Switzerland.
 */
package ch.unifr.diva.divannotation.textzone;

import ch.unifr.tei.facsimile.surfacegrp.surface.zone.TeiZone;
import ch.unifr.tei.text.body.div.TeiDiv;
import ch.unifr.tei.text.body.div.div.TeiDivDiv;
import ch.unifr.tei.text.body.div.div.ab.TeiAb;
import ch.unifr.tei.tools.TranscriptionManager;
import ch.unifr.diva.divannotation.GUI;
import ch.unifr.diva.divannotation.TeiScrollableImagePanel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.logging.Level;
import org.jdom2.JDOMException;
import ch.unifr.tei.facsimile.surfacegrp.surface.zone.TeiTextSegZone;
import ch.unifr.tei.tools.exception.InvalidTranscriptionException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import virtualkeyboard.gui.DialogVirtualKeyboardReal;

/**
 * @author Fotini Simistira <fotini.simistira@unifr.ch> *
 */
public class TranscriptionPanel extends TeiScrollableImagePanel {

    private static final Logger logger = Logger.getLogger(TranscriptionPanel.class);

    private TranscriptionFunctionsPanel functionPanel;
    private JTextField transcrTextArea = new AutoSavingJTextField();
    private ButtonGroup textCategoryBTNs = new ButtonGroup();
    private JRadioButton abBTN = new JRadioButton("<ab>");
    private JRadioButton lBTN = new JRadioButton("<l>");
    private JButton saveTrans = new JButton();
    private JButton delTrans = new JButton();
    private JButton vKeyboard = new JButton();
    //private JButton saveTrans2 = new JButton("Save transcription - 2");
    private ButtonGroup fontCategoryBTNs = new ButtonGroup();
    private JRadioButton fontJC = new JRadioButton("Junicode");
    private JRadioButton fontTNR = new JRadioButton("Times New Roman");
    private JButton runOcr = new JButton("OCR");

    public TeiAb fAb_gl;
    public TeiDivDiv fDivDiv_gl;
    public TeiTextSegZone selectedZone_gl;

    //TeiDiv fDiv : gui.getTei().getText().getBody().getDivs()
    private DefaultListModel<TeiDiv> divListModel = new DefaultListModel<>();

    public BufferedImage textlineImg;

    /**
     * These fields are created by NetBeans Form Editor. WARNING: Do NOT modify
     * this code. The content is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Fields">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel transPanel;
    private javax.swing.JScrollPane transScrollPane;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    /**
     * Creates new form TranscriptionPanel
     */
    public TranscriptionPanel(GUI gui) {
        super(gui);

        logger.trace(Thread.currentThread().getStackTrace()[1].getMethodName());

        multiselect = false;
        selectableZoneTypes.add(ZONE_TYPES.TEXTSEG_ZONE);
        selectableZoneTypes.add(ZONE_TYPES.TEXT_ZONE);
        selectableZoneTypes.add(ZONE_TYPES.COMMENTSEG_ZONE);
        defaultZoneType = ZONE_TYPES.TEXTSEG_ZONE;

        initializeField(transcrTextArea, "Please enter some text here");

        Image img_save = null;
        try {
            img_save = ImageIO.read(getClass().getResource("/icons/comment_accept.png"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TranscriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeButton(saveTrans, "Save transcription", TranscriptionPanel.this::saveTransClicked, img_save);
        Image img_del = null;
        try {
            img_del = ImageIO.read(getClass().getResource("/icons/comment_delete.png"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TranscriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeButton(delTrans, "Delete transcription", TranscriptionPanel.this::delTransClicked, img_del);
        Image img_vKb = null;
        try {
            img_vKb = ImageIO.read(getClass().getResource("/icons/keyboard.png"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TranscriptionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initializeButton(vKeyboard, "Show Virtual Keyboard", TranscriptionPanel.this::vKeyboardClicked, img_vKb);

        initializeRButton(fontJC, "Set font to Junicode", TranscriptionPanel.this::fontJCClicked);
        initializeRButton(fontTNR, "Set font to Times New Roman", TranscriptionPanel.this::fontTNRClicked);

        //bottomPanel.add(transcrTextArea);
        //transcrTextArea.setText("");
        textCategoryBTNs.add(abBTN);
        textCategoryBTNs.add(lBTN);
        bottomPanel.add(abBTN);
        bottomPanel.add(lBTN);

        fontCategoryBTNs.add(fontJC);
        fontCategoryBTNs.add(fontTNR);
        bottomPanel.add(fontJC);
        bottomPanel.add(fontTNR);
        
        initializeButton(runOcr, "Run Ocropy on selected data", TranscriptionPanel.this::runOcrOnArea, null);
        bottomPanel.add(runOcr);

        this.revalidate();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                // Nothing to do
            }
        });

        this.transcrTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    saveTransClicked(null);
                    toggleSelectedZone(selectedZone_gl);
                    int id = selectedZone_gl.getParent().getIndex(selectedZone_gl);
                    if (selectedZone_gl.getParent().getTextSegZones().size() > id + 1) {
                        selectedZone_gl = selectedZone_gl.getParent().getTextSegZones().get(id + 1);
                    } else {
                        selectedZone_gl = null;
                    }
                    if(selectedZone_gl!=null){
                        toggleSelectedZone(selectedZone_gl);
                        showSelectedTranscription();
                        Rectangle rect = selectedZone_gl.getArea().getShape().getBounds();
                        textlineImg = bi.getSubimage(rect.x, rect.y, rect.width, rect.height);
                    } else {
                        textlineImg = null;
                        transcrTextArea.setBackground(Color.GRAY);
                    }
                    repaint();
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 905, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 171, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseDraggedEvent(MouseEvent me) {
    }

    @Override
    public void mouseClickedEvent(MouseEvent me) {
        transcrTextArea.setText("");
        float x = getRealX(me.getX());
        float y = getRealY(me.getY());
        Point2D pt = new Point2D.Float(x, y);
        TeiZone selectedZone = getSelectedZone(pt, currentZoneType);
        toggleSelectedZone(selectedZone);
        if (selectedZone != null && selectedZone instanceof TeiTextSegZone) {
            selectedZone_gl = (TeiTextSegZone) selectedZone;
            selectedZone_gl.getParent().doSortChildZonesByYPosition();
        }
        if (isSelected(selectedZone_gl)) {
            if (selectedZone_gl==null || selectedZone_gl.getArea()==null) {
                return;
            }
            if (selectedZone_gl.getArea().getShape()==null) {
                return;
            }
            Rectangle rect = selectedZone_gl.getArea().getShape().getBounds();
            textlineImg = bi.getSubimage(rect.x, rect.y, rect.width, rect.height);
            showSelectedTranscription();

        } else {
            textlineImg = null;
            transcrTextArea.setBackground(Color.GRAY);
        }
        repaint();
    }

    @Override
    public void mousePressedEvent(MouseEvent me) {
    }

    @Override
    public void mouseReleasedEvent(MouseEvent me) {
    }

    @Override
    public void paintPanel(Graphics2D g) {
        g.setColor(Color.BLACK);
        paintTextZones(g, null);
        paintDecorationZones(g);

        //ToDO(Fotini) this feature can be enabled or disabled (Making a small image of the currently selected Text line so that the eye does not have to move too far when transcribing.)
        if (selectedZone_gl == null) {
            textlineImg = null;
            repaint();
//            g.dispose();
//            g.drawImage(null, 0, 0, this);
        } else if (textlineImg!=null){
            Rectangle rect1 = selectedZone_gl.getArea().getShape().getBounds();
            double zoomf = gui.getZoom();
            int newH = getPanel().getBounds().height - textlineImg.getHeight() / 2;
            //ToDO(Fotini) the rectangle does not follow the scale of zooming, i dont know what is more useful
            g.setColor(Color.BLACK);
            g.fillRect(0, newH - 7, textlineImg.getWidth(),
                    textlineImg.getHeight() + newH);
            g.drawImage(
                    textlineImg,
                    0,
                    newH,
                    textlineImg.getWidth(),
                    textlineImg.getHeight() + newH,
                    0,
                    0,
                    (int) (rect1.width / zoomf),
                    (int) ((rect1.height) / zoomf),
                    Color.black,
                    this);
        }

        repaint();

        if (gui.page.getTextZones().size() > 0) {
            gui.unlockTabs(8);
        }
    }

    private void formFocusGained(java.awt.event.FocusEvent evt) {
        logger.trace(Thread.currentThread().getStackTrace()[1].getMethodName());

        divListModel.clear();

        initializeAbL();
        initializeFont();

        for (TeiDiv fDiv : gui.getTei().getText().getBody().getDivs()) {
            divListModel.addElement(fDiv);
            if (fDiv.getId() == null) {
                logger.warn("Div without ID");
            }
        }

        gui.updateSharedPanelItems();
        try {
            bi = gui.getImage();
            setImage(bi);
            getPanel().setZoom(gui.getZoom());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Could not load the image from the selected graphic\n" + gui.graphic.getURL() + "\n"
                    + "Reason given:\n" + e.getMessage(), "Loading error",
                    JOptionPane.ERROR_MESSAGE
            );
            gui.tabbedPane.setSelectedIndex(7);
        }
    }

    void button1Clicked(java.awt.event.ActionEvent ev) {
        JOptionPane.showMessageDialog(null, "You pressed a button");
    }

    private void initializeField(JTextField tfield, String toolTip) {
        bottomPanel.add(tfield);
        //tfield.setToolTipText(toolTip);
        Font f = new Font("Junicode", Font.PLAIN, 20);
        //set the font to the JTextField
        tfield.setFont(f);
        //change a JTextField font color
        tfield.setForeground(Color.BLACK);
        tfield.setBackground(Color.GRAY);
        tfield.setBounds(80, 20, 250, 50);

//        tfield.setText("\u0292");
        bottomPanel.add(tfield);
    }
    //initializeButton(saveTrans, "Save transcription", TranscriptionPanel.this::saveTransClicked);
    //initializeRButton(fontJC, "Set font to Junicode", TranscriptionPanel.this::fontJCClicked);

    private void initializeRButton(JRadioButton tRbutton, String toolTip, ActionListener act) {

        tRbutton.setToolTipText(toolTip);
//        tRbutton.setSize(20, 41);
//        tRbutton.setBorderPainted(false);
//        tRbutton.setFocusPainted(false);
//        tRbutton.setContentAreaFilled(false);

        tRbutton.addActionListener(act);
        bottomPanel.add(tRbutton);
    }

    private void initializeButton(JButton tbutton, String toolTip, ActionListener act, Image img) {
        bottomPanel.add(tbutton);
        if (img != null) {
            tbutton.setIcon(new ImageIcon(img));
        }
        tbutton.setToolTipText(toolTip);
        if (img!=null) {
            tbutton.setSize(20, 41);
            tbutton.setBorderPainted(false);
            tbutton.setFocusPainted(false);
            tbutton.setContentAreaFilled(false);
        }
//        Font f = new Font("Verdana", Font.BOLD, 20);
//        //set the font to the JTextField
//        tfield.setFont(f);
//        //change a JTextField font color
//        tfield.setForeground(Color.BLUE);
//        tfield.setBackground(Color.ORANGE);
//        tfield.setBounds(80, 20, 250, 50);
        tbutton.addActionListener(act);
        bottomPanel.add(tbutton);
    }

    private void createTextZone(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "You pressed a button");
    }

    private void showSelectedTranscription() {
        if (selectedZone_gl == null) {
            return;
        }
        TeiAb ab = selectedZone_gl.getTranscription();
        if (ab == null) {
            transcrTextArea.setText("");
            transcrTextArea.setBackground(Color.ORANGE);
            System.out.println("No transcription available");
            return;
        }
        transcrTextArea.setText(ab.getTagSoup());
        transcrTextArea.setBackground(Color.GRAY);

//        Rectangle rect= selectedZone_gl.getArea().getShape().getBounds();
//        textlineImg=bi.getSubimage(rect.x, rect.y, rect.width, rect.height);
    }

    public void saveTransClicked(ActionEvent evt) {
        if (selectedZone_gl == null) {
            JOptionPane.showMessageDialog(null, "Select a text line please", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Making sure it does have a transcription
        selectedZone_gl.initializeTranscription();
        TeiAb trans = selectedZone_gl.getTranscription();

        TranscriptionManager tm = new TranscriptionManager();
        TeiAb newAb = null;
        try {
            newAb = tm.getStructuredAB(trans.getParent(), transcrTextArea.getText());
        } catch (JDOMException ex) {
            JOptionPane.showMessageDialog(null, "Invalid XML content.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Input/output error.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (InvalidTranscriptionException ex) {
            JOptionPane.showMessageDialog(null, "The transcription is not valid.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        newAb.setFacs(trans.getFacs());

        trans.getParent().replaceAb(trans, newAb);
        selectedZone_gl.setTranscription(newAb);
    }

    public void delTransClicked(ActionEvent evt) {
        transcrTextArea.setBackground(new Color(170, 104, 102));
        transcrTextArea.setText("");

        if (selectedZone_gl == null) {
            return;
        }
        TeiAb trans = selectedZone_gl.getTranscription();
        trans.clearContent();
    }

    public void saveTransClicked2(ActionEvent evt) {
        // fAb_gl.getTagSoup();

        TranscriptionManager tm = new TranscriptionManager();
        gui.getTei().getText().getBody().getDivs();

        try {
            TeiAb ab = tm.getStructuredAB(fDivDiv_gl, transcrTextArea.getText());
            fDivDiv_gl.removeAb(selectedZone_gl);
            fDivDiv_gl.addAb(ab);
            ab.setFacs(selectedZone_gl);
            logger.debug("saveTransClicked: Selected <Ab> is " + selectedZone_gl.toString() + "=" + ab.getTagSoup());
        } catch (JDOMException ex) {
            JOptionPane.showMessageDialog(null, "Invalid XML content.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Input/output error.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidTranscriptionException ex) {
            JOptionPane.showMessageDialog(null, "The transcription is not valid.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fontJCClicked(ActionEvent evt) {
        Font f = new Font("Junicode", Font.PLAIN, 20);
        transcrTextArea.setFont(f);
        transcrTextArea.setForeground(Color.BLACK);
//                transcrTextArea.setText("Junicode");
        repaint();
    }

    public void fontTNRClicked(ActionEvent evt) {
        Font f = new Font("Times New Roman", Font.PLAIN, 20);
        transcrTextArea.setFont(f);
        transcrTextArea.setForeground(Color.BLUE);
//                transcrTextArea.setText("Times New Roman");
        repaint();
    }

    public void vKeyboardClicked(ActionEvent evt) {
        DialogVirtualKeyboardReal dialog = new DialogVirtualKeyboardReal(new javax.swing.JFrame(), false);

        dialog.show(transcrTextArea, -1, transcrTextArea.getHeight());
//                            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    //@Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
        dialog.setVisible(true);
        dialog.setTextComponent(transcrTextArea);

//        final JOptionPane vKeyboardPane = new JOptionPane(
//                "Showing the virtual keyboard tool.\n" +
//                        "ToDO.\n\n" +
//                        "ToDO \n",
//                JOptionPane.PLAIN_MESSAGE);
//
//        final JDialog vKeyboardDialog = new JDialog(gui, "Virtual Keyboard");
//        vKeyboardDialog.setModalityType(JDialog.ModalityType.MODELESS);
//        vKeyboardDialog.setContentPane(vKeyboardPane);
//        vKeyboardDialog.pack();
//        vKeyboardDialog.setLocationRelativeTo(gui);
//        vKeyboardDialog.setVisible(true);
//        
//        vKeyboardPane.addPropertyChangeListener(
//                new PropertyChangeListener() {
//                    public void propertyChange(PropertyChangeEvent e) {
//                        String prop = e.getPropertyName();
//
//                        if (vKeyboardDialog.isVisible()
//                                && (e.getSource() == vKeyboardPane)
//                                && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
//                            vKeyboardDialog.dispose(); //setVisible(false);
//                        }
//                    }
//                });
    }
    
    public void runOcrOnArea(ActionEvent evt) {
        System.out.println("Selectedzones:");
        for (TeiZone z : super.getSelectedZones()) {
            System.out.println(z);
        }
        new OcrFrame(gui.getTei(), gui.page, super.getSelectedZones());
    }

    private void initializeAbL() {
        abBTN.setSelected(false);
        lBTN.setSelected(false);
        abBTN.setEnabled(false);
        lBTN.setEnabled(false);
    }

    private void initializeFont() {
        fontJC.setSelected(true);
        fontTNR.setSelected(false);
        fontJC.setEnabled(true);
        fontTNR.setEnabled(true);
    }

}
