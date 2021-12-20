/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Utente
 */
public class FMenu extends JFrame implements ActionListener {

    Condivisa cond;
    FSchermataGioco fGioco;
    private JButton gioca;
    private JButton impostazioni;
    private JButton esci;

    public static void main(String[] args) throws HeadlessException, IOException {
        new FMenu();

    }

    public FMenu() throws HeadlessException, IOException {
        cond = new Condivisa();
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Finestra");
        this.setLayout(null);

        gioca = new JButton();
        impostazioni = new JButton();
        esci = new JButton();
        gioca.setBounds(100, 100, 100, 125);
        gioca.setText("Gioco");

        impostazioni.setBounds(200, 100, 100, 125);
        impostazioni.setText("Impostazioni");

        esci.setBounds(300, 100, 100, 125);
        esci.setText("Esci");
        //Clicklistener click = new Clicklistener();
        gioca.addActionListener(this);
        impostazioni.addActionListener(this);
        esci.addActionListener(this);
        //Image img = ImageIO.read(getClass().getResource("paolo.png"));
        //img = resizeImage((BufferedImage) img, 1, 100, 125);
        //bottone.setIcon(new ImageIcon(img));
        //bottone.setText("C");
        this.add(gioca);
        this.add(impostazioni);
        this.add(esci);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gioca) {
            System.out.println("No");
            //cond.getGioco().setVisible(true);
            
            try {
                fGioco = new FSchermataGioco();
                fGioco.setVisible(true);
            } catch (HeadlessException ex) {
                Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);

        }else if(e.getSource() == impostazioni){
        }else if(e.getSource() == esci){
            System.exit(0);
        }
    }

}
