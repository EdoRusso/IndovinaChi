/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Utente
 */
public class FImpostazioni extends JFrame {

    private Condivisa cond;

    public FImpostazioni(Condivisa cond) throws HeadlessException, IOException {

        this.cond = cond;
        Color c = new Color(208, 230, 255);
        this.getContentPane().setBackground(c);
        //cond.makeFrameFullSize(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(this.MAXIMIZED_BOTH);
        System.out.println("Impostazioni" + getWidth());
        System.out.println("Impostazioni" + getHeight());
        this.setTitle("Impostazioni - Indovina Chi");
        this.setVisible(true);
    }

}
