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
import javax.swing.JLabel;

/**
 *
 * @author Utente
 */
public class FSchermataGioco extends JFrame implements ActionListener {

    JButton bottone;
    //boolean paolo = true;
    Condivisa cond;

    Image img;
    private JButton[] arrayBottoni;
    private JLabel[] arrayLabel;
    // Clicklistener click;

    public FSchermataGioco() throws HeadlessException, IOException {
        cond = new Condivisa();
        System.out.println("Dentro1");
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c = new Color(208, 230, 255);
        this.getContentPane().setBackground(c);
        this.setTitle("Finestra");
        this.setLayout(null);

        //Vettore di bottoni
        arrayBottoni = new JButton[24];
        //Vettore di label
        arrayLabel = new JLabel[24];
        //Posizione dei bottoni(local) e delle label(opponent)
        int posXopponent = -25;
        int posXlocal = 1095;
        //posizione della y che è uguale per entrambi
        int posY = 100;
        //contatore per posizionare le label e bottoni
        int cont = 0;
        for (int i = 0; i < arrayBottoni.length; i++) {
            arrayBottoni[i] = new JButton();
            arrayLabel[i] = new JLabel();
            //System.out.println("sono dentro");
            if (cont >= 6) {
                posXlocal = 1200;
                posXopponent = 80;
                posY += 130;
                cont = 1;
            } else {
                posXlocal += 105;
                posXopponent += 105;
                cont++;
            }
            //arrayBottoni[i].setText(i + ""); 
            //vado ad inserire nelle label le sagome
            img = ImageIO.read(getClass().getResource("sagoma.png"));
            img = cond.resizeImage((BufferedImage) img, 1, 100, 125);
            arrayLabel[i].setIcon(new ImageIcon(img));
            //bottone.setIcon(new ImageIcon(img));
            arrayLabel[i].setBounds(posXopponent, posY, 100, 125);
            this.add(arrayLabel[i]);
            //vado a inserire nei bottoni le immagini delle persone 
            img = ImageIO.read(getClass().getResource(cond.persona[i].getPercorso()));
            img = cond.resizeImage((BufferedImage) img, 1, 100, 125);
            arrayBottoni[i].setIcon(new ImageIcon(img));
            //bottone.setIcon(new ImageIcon(img));
            arrayBottoni[i].setBounds(posXlocal, posY, 100, 125);
            this.add(arrayBottoni[i]);
            arrayBottoni[i].addActionListener(this);
        }
        System.out.println("Dentro2");
        //ultimo
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < arrayBottoni.length; i++) {
            //System.out.println("sono dentro pt2");
            if (e.getSource() == arrayBottoni[i] && !cond.persona[i].isAttivo()) {
                try {
                    img = ImageIO.read(getClass().getResource("sagoma.png"));
                    img = cond.resizeImage((BufferedImage) img, 1, 100, 125);
                    arrayBottoni[i].setIcon(new ImageIcon(img));
                    cond.persona[i].setAttivo(true);
                    System.out.println(cond.persona[i].getNome());

                } catch (IOException ex) {
                    Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //System.out.println(e.getSource());    }
    }
}
