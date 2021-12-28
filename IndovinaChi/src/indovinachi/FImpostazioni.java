/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Utente
 */
public class FImpostazioni extends JFrame {

    private Condivisa cond;
    static JSlider sliderR;
    static JSlider sliderG;
    static JSlider sliderB;
    static JSlider sliderTemp;
    static JSlider sliderTent;
    JTextPane btn;
    JLabel labelR;
    JLabel labelG;
    JLabel labelB;
    FImpostazioni sl;
    static int value;
    static FImpostazioni gui;
    event e;
    int r = 30;
    int g = 204;
    int b = 68;
    Color c;

    public FImpostazioni() {
        //gui = new FImpostazioni();
    }

    public FImpostazioni(Condivisa cond) throws HeadlessException, IOException {
        this.cond = cond;

        c = new Color(r, g, b);

        this.getContentPane().setBackground(c);
        this.gui = new FImpostazioni();
        
        //this.gui = new FImpostazioni();
        //this.getContentPane().setBackground(c);
        this.setTitle("Impostazioni");
        this.getContentPane().setBackground(c);
        this.setSize(300, 275);
        this.setMinimumSize(new Dimension(300, 275));

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        sl = this;
        JLabel l = new JLabel("Colore di sfondo");
        this.add(l);

        sliderR = new JSlider(JSlider.HORIZONTAL, 0, 255, r);
        sliderG = new JSlider(JSlider.HORIZONTAL, 0, 255, g);
        sliderB = new JSlider(JSlider.HORIZONTAL, 0, 255, b);

        add(sliderR);
        labelR = new JLabel("R: " + r);
        add(labelR);
        e = new event();
        sliderR.addChangeListener(e);

        add(sliderG);
        labelG = new JLabel("G: " + g);
        add(labelG);
        e = new event();
        sliderG.addChangeListener(e);

        add(sliderB);
        labelB = new JLabel("B: " + b);
        add(labelB);
        e = new event();
        sliderB.addChangeListener(e);

        JLabel l2 = new JLabel("Nome utente: ");
        //l2.setBounds(120, 50, 100, 100);
        this.add(l2);
        btn = new JTextPane();
        btn.setText("guest");
        btn.setBackground(c);
        this.add(btn);

        sliderTemp = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        sliderTemp.setBackground(c);
        sliderTemp.setPaintLabels(true);
        sliderTemp.setMajorTickSpacing(1);
        add(sliderTemp);
        JLabel l3 = new JLabel("Tempo");
        this.add(l3);

        sliderTent = new JSlider(JSlider.HORIZONTAL, 0, 4, 0);
        sliderTent.setBackground(c);
        sliderTent.setPaintLabels(true);
        sliderTent.setMajorTickSpacing(1);
        add(sliderTent);
        JLabel l4 = new JLabel("Tentativi");
        this.add(l4);

        sliderR.setBackground(c);
        sliderG.setBackground(c);
        sliderB.setBackground(c);

        JButton btnConferma = new JButton("Conferma");
        this.add(btnConferma);

    }

    public class event implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            if (e.getSource() == sliderR) {
                r = sliderR.getValue();
                //Color r = new Color(value, 0, 0);          
                labelR.setText("R: " + r);
            } else if (e.getSource() == sliderG) {
                g = sliderG.getValue();
                labelG.setText("G: " + g);
            } else if (e.getSource() == sliderB) {
                b = sliderB.getValue();
                labelB.setText("B: " + b);
            }

            c = new Color(r, g, b);
            gui.getContentPane().setBackground(c);
            sliderR.setBackground(c);
            sliderG.setBackground(c);
            sliderB.setBackground(c);
            sliderTent.setBackground(c);
            sliderTemp.setBackground(c);
            btn.setBackground(c);
        }

    }
}
