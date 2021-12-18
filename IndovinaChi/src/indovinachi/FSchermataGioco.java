/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Utente
 */
public class FSchermataGioco extends JFrame {

    JButton bottone;
    boolean paolo = true;
    Condivisa cond;
    Image img;
    private JButton[] arrayBottoni;
    Clicklistener click;

    public FSchermataGioco() throws HeadlessException, IOException {
        cond = new Condivisa();
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c = new Color(208, 230, 255);
        this.getContentPane().setBackground(c);
        this.setTitle("Finestra");
        this.setLayout(null);
        click = new Clicklistener();

//        bottone = new JButton();
//        img = ImageIO.read(getClass().getResource("PersonaggiIndovinaChi/paolo.png"));
//        img = resizeImage((BufferedImage) img, 1, 100, 125);
//        bottone.setIcon(new ImageIcon(img));
//        bottone.setBounds(100, 100, 100, 125);
//        bottone.addActionListener(click);
        //     this.add(bottone);
        // this.setVisible(true);
        arrayBottoni = new JButton[24];

        //Vettore di persone
        int posX = 1095;
        int posY = 100;

        int y = 0;
        for (int i = 0; i < arrayBottoni.length; i++) {
            arrayBottoni[i] = new JButton();
            System.out.println("sono dentro");

            if (y >= 6) {
                posX = 1200;
                posY += 130;
                y = 1;
            } else {
                posX += 105;
                y++;
            }

            //arrayBottoni[i].setText(i + "");          
            img = ImageIO.read(getClass().getResource(cond.persona[i].getPercorso()));
            img = resizeImage((BufferedImage) img, 1, 100, 125);
            arrayBottoni[i].setIcon(new ImageIcon(img));
            //bottone.setIcon(new ImageIcon(img));
            arrayBottoni[i].setBounds(posX, posY, 100, 125);

            this.add(arrayBottoni[i]);
            arrayBottoni[i].addActionListener(click);
        }

        //ultimo
        this.setVisible(true);
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }

    private class Clicklistener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == bottone && paolo) {
//                System.out.println("Paolo");
//                paolo = false;
//
//                    Image img;
//                try {
//                    img = ImageIO.read(getClass().getResource("sagoma.png"));
//                    img = resizeImage((BufferedImage) img, 1, 100, 125);
//                    bottone.setIcon(new ImageIcon(img));
//                } catch (IOException ex) {
//                    java.util.logging.Logger.getLogger(FSchermataGioco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                }
            for (int i = 0; i < arrayBottoni.length; i++) {
                System.out.println("sono dentro pt2");
                if (e.getSource() == arrayBottoni[i] && !cond.persona[i].isAttivo()) {
                    try {
                        img = ImageIO.read(getClass().getResource("sagoma.png"));
                        img = resizeImage((BufferedImage) img, 1, 100, 125);
                        arrayBottoni[i].setIcon(new ImageIcon(img));
                        cond.persona[i].setAttivo(true);
                    } catch (IOException ex) {
                        Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            System.out.println(e.getSource());
        }

    }
}
