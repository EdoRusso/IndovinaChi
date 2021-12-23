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
public class FMenu extends JFrame {

    Condivisa cond;
    FSchermataGioco fGioco;
    FImpostazioni fImpostazioni;
    private final JLabel gioca;
    private final JLabel impostazioni;
    private final JLabel esci;
    private Image img;

    public FMenu(Condivisa cond) throws HeadlessException, IOException {
        this.cond = cond;
        //this.setUndecorated(true);
        // x-> 1936 y-> 1056
        //Label 600 200
        // Bottone dimensione 100 125
        // 19.36 8.448      

        this.setTitle("Menu - Indovina Chi");
        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setExtendedState(this.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Colore di sfondo
        Color c = new Color(208, 230, 255);
        this.getContentPane().setBackground(c);

        this.setLayout(null);

        //Imposto la finestra non ridimensionabile
        this.setMinimumSize(new Dimension(cond.getWidth(), cond.getHeight()));
        System.out.println("Frame Size = " + this.getSize());

        //Label creazione gioca
        gioca = new JLabel();
        gioca.setBounds(650, 100, 600, 200);
        img = ImageIO.read(getClass().getResource("Bottoni/play.png"));
        //img = cond.resizeImage((BufferedImage) img, 1, 600, 200);
        gioca.setIcon(new ImageIcon(img));
        this.add(gioca);
        gioca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("true");
                try {
                    fGioco = new FSchermataGioco(cond);
                    fGioco.setVisible(true);
                    cond.getMenu().setVisible(false);

                } catch (HeadlessException | IOException ex) {
                    Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("E dai");
            }
        });

        //Label creazione impostazioni
        impostazioni = new JLabel();
        impostazioni.setBounds(650, 350, 600, 200);
        img = ImageIO.read(getClass().getResource("Bottoni/settings.png"));
        // img = cond.resizeImage((BufferedImage) img, 1, 600, 200);
        impostazioni.setIcon(new ImageIcon(img));
        this.add(impostazioni);
        impostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("true");

                try {
                    fImpostazioni = new FImpostazioni(cond);
                    fImpostazioni.setVisible(true);
                    cond.getMenu().setVisible(false);
                } catch (HeadlessException | IOException ex) {
                    Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("E dai");
            }
        });
        //Label creazione esci
        esci = new JLabel();
        esci.setBounds(650, 600, 600, 200);
        img = ImageIO.read(getClass().getResource("Bottoni/exit.png"));
        //img = cond.resizeImage((BufferedImage) img, 1, 600, 200);
        esci.setIcon(new ImageIcon(img));
        this.add(esci);
        esci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.exit(0);
                System.out.println("E dai");

            }
        });

        this.setVisible(true);


    }

}
