/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import static indovinachi.FImpostazioni.sliderB;
import static indovinachi.FImpostazioni.sliderG;
import static indovinachi.FImpostazioni.sliderR;
import static indovinachi.FImpostazioni.sliderTemp;
import static indovinachi.FImpostazioni.sliderTent;
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
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

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
    private JLabel labelLogo;
    private boolean turnoIniziale;
    public FMenu(Condivisa cond) throws HeadlessException, IOException {
        System.out.println("FMenu: Dentro al menu");
        System.out.println(cond.getWidth()+" "+cond.getHeight());
        turnoIniziale=false;
        this.cond = cond;
        fImpostazioni = new FImpostazioni(cond);
        cond.setMenu(this);

        // x-> 1936 y-> 1056
        //Label Lx->600 Ly->200
        // Bottone dimensione 100 125
        //Pos Label
        //x/2-Lx/2 
        //LPy1=300 -> 1056/300->3.52
        //LPy2=500 -> 1056/500->2.112
        //LPy3=700 -> 1056/700->1.508
        this.setTitle("Menu - Indovina Chi");
        //Elimina i bordi
        setUndecorated(true);
        //Imposto la finestra al massimo
        setExtendedState(this.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Colore di sfondo        
        Color c = cond.getColore();
        this.getContentPane().setBackground(c);

        this.setLayout(null);

        //Imposto la finestra non ridimensionabile
        this.setMinimumSize(new Dimension(cond.getWidth(), cond.getHeight()));

        //Label creazione gioca
        gioca = new JLabel();
        
        gioca.setBounds((cond.getWidth() / 2) - 300, (int) (cond.getHeight() / 3.52), 600, 200);
        //gioca.setHorizontalAlignment(gioca.CENTER);
        img = ImageIO.read(getClass().getResource("Bottoni/play.png"));
        //img = cond.resizeImage((BufferedImage) img, 1, 600, 200);
        gioca.setIcon(new ImageIcon(img));
        this.add(gioca);
        gioca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("FMenu: Gioca");
                try {

                    fGioco = new FSchermataGioco(cond);
                    fGioco.setVisible(true);
                    turnoIniziale=true;
                    cond.getMenu().setVisible(false);
                    cond.getInviaRicevi().invia("c;"+cond.getNomeUtente()+";"+cond.getTempo()+";"+cond.getTentativi()+";");
                    
                } catch (HeadlessException | IOException ex) {
                    Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("FMenu: E dai");
            }
        });

        //Label creazione impostazioni
        impostazioni = new JLabel();
        impostazioni.setBounds((cond.getWidth() / 2) - 300, (int) (cond.getHeight() / 2.112), 600, 200);
        img = ImageIO.read(getClass().getResource("Bottoni/settings.png"));
        // img = cond.resizeImage((BufferedImage) img, 1, 600, 200);
        impostazioni.setIcon(new ImageIcon(img));
        this.add(impostazioni);
        impostazioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("FMenu: Impostazioni");

                try {

                    if (!fImpostazioni.isVisible()) {
                        fImpostazioni.setVisible(true);                      
                        System.out.println("fImpostazioni " + fImpostazioni.isVisible());
                    }

                    //cond.getMenu().setVisible(false);
                } catch (HeadlessException ex) {
                    Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("E dai");
            }
        });
        //Label creazione esci
        esci = new JLabel();
        esci.setBounds((cond.getWidth() / 2) - 300, (int) (cond.getHeight() / 1.508), 600, 200);
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
        labelLogo = new JLabel();
        labelLogo.setBounds((cond.getWidth() / 2) - 150, (int) (cond.getHeight() / 100), 300, 300);
        img = ImageIO.read(getClass().getResource("Bottoni/LogoIndovinaChi.png"));
        labelLogo.setIcon(new ImageIcon(img));
        this.add(labelLogo);
        this.setVisible(true);

    }

    public boolean isTurnoIniziale() {
        return turnoIniziale;
    }
    
}
