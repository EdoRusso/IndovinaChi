/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Utente
 */
public final class FSchermataGioco extends JFrame implements ActionListener {

    JButton bottone;
    Condivisa cond;
    //Btn 100x125

    Image img;
    private JButton[] arrayBottoni;
    private JLabel[] arrayLabel;

    JButton tenta;
    JButton arrenditi;
    JButton passaTurno;
    boolean passaTurnoAvversario;

    public FSchermataGioco(Condivisa cond) throws HeadlessException, IOException {
        this.cond = cond;
        passaTurnoAvversario = true;
        cond.setGioco(this);
        System.out.println("FSchermataGioco: Dentro gioco");
        this.setTitle("Gioco - Indovina chi");

        //Elimina i bordi della finestra
        setUndecorated(true);
        //Imposto la finestra al massimo
        setExtendedState(FStart.MAXIMIZED_BOTH);
        //Se clicco la X si chiuderà automaticamente il programma
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Colore di sfondo        
        Color c = cond.getColore();
        this.getContentPane().setBackground(c);

        this.setLayout(null);
        //Bottone passaTurno
        passaTurno = new JButton("passaTurno");
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        passaTurno.setBounds((cond.getWidth() / 2) - 100, 750, 200, 100);
        this.add(passaTurno);
        passaTurno.addActionListener(this);
        //Bottone arrenditi
        arrenditi = new JButton("arrenditi");
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        arrenditi.setBounds((cond.getWidth() / 2) - 500, 750, 200, 100);
        this.add(arrenditi);
        arrenditi.addActionListener(this);
        //Bottone tenta
        tenta = new JButton("tenta");
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        tenta.setBounds((cond.getWidth() / 2) + 300, 750, 200, 100);
        this.add(tenta);
        tenta.addActionListener(this);
        //Vettore di bottoni
        arrayBottoni = new JButton[24];
        //Vettore di label
        arrayLabel = new JLabel[24];

        //Posizione dei bottoni(local) e delle label(opponent)
        //50
        int posXopponent = (int) (cond.getHeight() / 21.12);
        //1936-675
        int posXlocal = (int) (cond.getWidth() - (cond.getWidth() / 2.868));
        //posizione della y che è uguale per entrambi
        //150
        int posY = (int) (cond.getHeight() / 21.12);

        //contatore per posizionare le label e bottoni
        int cont = 1;
        for (int i = 0; i < arrayBottoni.length; i++) {
            arrayBottoni[i] = new JButton();
            arrayLabel[i] = new JLabel();
            System.out.println("FSchermataGioco: sono dentro " + i);

            if (cont >= 6) {

                aggiungi(i, posXlocal, posXopponent, posY);
                posXlocal = (int) (cond.getWidth() - (cond.getWidth() / 2.868));
                //50
                posXopponent = (int) (cond.getHeight() / 21.12);;

                posY += cond.getHeight() / 8.123;
                cont = 1;

            } else {
                aggiungi(i, posXlocal, posXopponent, posY);

                posXlocal += cond.getWidth() / 18.438;
                posXopponent += cond.getWidth() / 18.438;
                cont++;
            }

        }
        System.out.println("FSchermataGioco: Dentro2");
        this.setVisible(true);

    }

    //Immagine sagoma/personaggio
    //x->1936/100 y=1056/125
    public void aggiungi(int i, int posXlocal, int posXopponent, int posY) throws IOException {
        //Vado ad inserire nelle label le sagome
        img = ImageIO.read(getClass().getResource("Bottoni/sagoma.png"));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        arrayLabel[i].setIcon(new ImageIcon(img));
        //bottone.setIcon(new ImageIcon(img));
        arrayLabel[i].setBounds(posXopponent, posY, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        this.add(arrayLabel[i]);
        //vado a inserire nei bottoni le immagini delle persone 
        img = ImageIO.read(getClass().getResource(cond.persona[i].getPercorso()));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        arrayBottoni[i].setIcon(new ImageIcon(img));
        arrayBottoni[i].setBounds(posXlocal, posY, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        this.add(arrayBottoni[i]);
        arrayBottoni[i].addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("FSchermata:------------------------------------------" + passaTurnoAvversario);
        if (passaTurnoAvversario) {
            if (e.getSource() == passaTurno) {
                System.out.println("FSchermataGioco: Passa Turno");
                passaTurnoAvversario = false;
                for (int i = 0; i < arrayBottoni.length; i++) {
                    if (cond.persona[i].isAttivo()) {
                        {
                            try {
                                img = ImageIO.read(getClass().getResource("Bottoni/sagomaO.jpg"));
                                img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
                                arrayBottoni[i].setIcon(new ImageIcon(img));
                                cond.persona[i].setEliminato(true);
                                System.out.println("FSchermataGioco: " + cond.persona[i].getNome());
                                this.repaint();
                            } catch (IOException ex) {

                                Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
            } else if (e.getSource() == arrenditi) {
                System.out.println("FSchermataGioco: Arrenditi");
                //invia("e;r;")
            } else if (e.getSource() == tenta) {
                System.out.println("FSchermataGioco: Tenta");
                //invia("t;"+labelMessaggio.getText()+";");
            } else {
                for (int i = 0; i < arrayBottoni.length; i++) {

                    if (e.getSource() == arrayBottoni[i]) {
                        if (cond.persona[i].isAttivo()) {
                            {
                                try {
                                    img = ImageIO.read(getClass().getResource(cond.persona[i].getPercorso()));
                                    img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
                                    arrayBottoni[i].setIcon(new ImageIcon(img));
                                    cond.persona[i].setAttivo(false);
                                    System.out.println("FSchermataGioco: " + cond.persona[i].getNome());
                                } catch (IOException ex) {

                                    Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        } else {
                            try {
                                img = ImageIO.read(getClass().getResource("Bottoni/sagoma.png"));
                                img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
                                arrayBottoni[i].setIcon(new ImageIcon(img));
                                cond.persona[i].setAttivo(true);
                                System.out.println("FSchermataGioco: " + cond.persona[i].getNome());

                            } catch (IOException ex) {
                                Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                }
            }

        }
    }
}
