/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Utente
 */
public class FStart extends JFrame {

    private static Condivisa cond;
    private final Image img;
    private static FMenu menu;
    private int width;
    private int height;

    public static void main(String[] args) throws HeadlessException, IOException {
        cond = new Condivisa();

        FStart fStart = new FStart();
    }

    public FStart() throws HeadlessException, IOException {
        cond = new Condivisa();
        cond.setStart(this);
        this.setTitle("Start - Indovina chi");

        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setExtendedState(FStart.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Colore di sfondo
        Color c = new Color(0, 0, 0);
        this.getContentPane().setBackground(c);
        
        this.setLayout(null);

        //Imposto la finestra non ridimensionabile
        this.setMinimumSize(new Dimension(width, height));
        System.out.println("Frame Size = " + this.getSize());

        //Dati relativi alla lunghezza e larghezza dello schermo
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();

        //Imposto i valori di Width e Height nella classe condivisa
        cond.setHeight(height);
        cond.setWidth(width);

        JLabel logoIndovinaChi = new JLabel();
        //Frame x:1936 y:1056
        //Logo x:900 y:845
        //LabelIcon (x/2)-450(y/2)-440 circa
        logoIndovinaChi.setBounds((width / 2) - 450, (height / 2) - 440, 900, 845);


        img = ImageIO.read(getClass().getResource("Bottoni/icona.png"));
        logoIndovinaChi.setIcon(new ImageIcon(img));
        this.add(logoIndovinaChi);

        logoIndovinaChi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("true");
                try {
                     menu = new FMenu(cond);
                     cond.setMenu(menu);
                     menu.setVisible(true);
                     cond.getStart().setVisible(false);
                } catch (HeadlessException | IOException ex) {
                    Logger.getLogger(FStart.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            }
        });

      this.setVisible(true);

    }
      
}
