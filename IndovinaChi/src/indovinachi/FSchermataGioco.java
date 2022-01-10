/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author Utente
 */
public final class FSchermataGioco extends JFrame implements ActionListener {

    JButton bottone;
    Condivisa cond;
    //Btn 100x125
    String personaggioScelto;
    Image img;
    private JButton[] arrayBottoni;
    private JLabel[] arrayLabel;

    JButton tenta;
    JButton arrenditi;
    JButton passaTurno;
    JButton inviaMessaggio;
    boolean turno;
    String nomePersonaggioUtente;
    String nomePersonaggioAvversario;
    JLabel nomeAvversario;
    JLabel nomeUtente;
    JLabel nTentativiUtente;
    JLabel nTentativiAvversario;

    JTextArea chat;
    JTextPane messaggio;
    JLabel personaggio;
    int tentativi;
    int tentativiAvversario;

    public FSchermataGioco(Condivisa cond) throws HeadlessException, IOException {
        tentativi = cond.getTentativi();
        this.cond = cond;
        turno = true;
        cond.setGioco(this);
        System.out.println("FSchermataGioco: Dentro gioco");
        this.setTitle("Gioco - Indovina chi");
        nomePersonaggioAvversario = "avversario";
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

        //Label personaggio scelto
        int random = (int) (Math.random() * 24);
        System.out.println("FSchermataGioco: " + random);
        personaggio = new JLabel();
        img = ImageIO.read(getClass().getResource(cond.persona[random].getPercorso()));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        personaggio.setIcon(new ImageIcon(img));
        //personaggio.setBounds((int) (cond.getWidth() / 2) - (int) (cond.getWidth() / 19.36) / 2, ((int) (cond.getHeight() / 1.47)) + 100, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        personaggio.setBounds((int) (cond.getWidth() / 2) - (int) (cond.getWidth() / 19.36) / 2, (int) (cond.getHeight() / 42.24) - 5, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        personaggio.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        nomePersonaggioUtente = cond.persona[random].getNome();
        this.add(personaggio);

        //Bottone passaTurno
        passaTurno = new JButton();
        img = ImageIO.read(getClass().getResource("Bottoni/round.jpg"));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        passaTurno.setIcon(new ImageIcon(img));
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        passaTurno.setBounds((cond.getWidth() / 2) - (int) (cond.getWidth() / 9.68) / 2, (int) (cond.getHeight() / 1.32), (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        this.add(passaTurno);
        passaTurno.addActionListener(this);

        //Bottone arrenditi
        arrenditi = new JButton();
        img = ImageIO.read(getClass().getResource("Bottoni/quit.jpg"));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        arrenditi.setIcon(new ImageIcon(img));
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        //arrenditi.setBounds((cond.getWidth() / 2) - 500, 750, (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        arrenditi.setBounds((int) (cond.getWidth() / 5.3) - ((int) (cond.getWidth() / 9.68) / 2), (int) (cond.getHeight() / 1.32), (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        this.add(arrenditi);
        arrenditi.addActionListener(this);

        //Bottone tenta
        tenta = new JButton();
        //passaTurno.setBounds((cond.getWidth()/2)-(int)((cond.getWidth()/19.36)), 600, 50, 300);
        //tenta.setBounds((cond.getWidth() / 2) + 300, 750, (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        tenta.setBounds((int) (cond.getWidth() / 1.232) - (int) (cond.getWidth() / 9.68) / 2, (int) (cond.getHeight() / 1.32), (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        img = ImageIO.read(getClass().getResource("Bottoni/try.jpg"));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 9.68), (int) (cond.getHeight() / 10.56));
        tenta.setIcon(new ImageIcon(img));
        this.add(tenta);
        tenta.addActionListener(this);
        if (cond.getTentativi() > 0) {
            //label nTentativi avversario
            nTentativiAvversario = new JLabel(tentativiAvversario + "/" + cond.getTentativi());
            nTentativiAvversario.setFont(new Font("Bungee Regular", Font.BOLD, 36));
            nTentativiAvversario.setBounds((int) (cond.getWidth() / 38.72), (int) (cond.getHeight() / 10.56), 50, 30);
            this.add(nTentativiAvversario);

            //label nTentativi utente
            nTentativiUtente = new JLabel(tentativi + "/" + cond.getTentativi());
            nTentativiUtente.setFont(new Font("Bungee Regular", Font.BOLD, 36));
            nTentativiUtente.setBounds((int) (cond.getWidth() / 1.054), (int) (cond.getHeight() / 10.56), 50, 30);
            this.add(nTentativiUtente);
        }

        //label nome utente
        String utente = cond.getNomeUtente();
        nomeUtente = new JLabel();
        nomeUtente.setText(utente);
        nomeUtente.setHorizontalAlignment(nomeUtente.CENTER);
        nomeUtente.setBounds((int) (cond.getWidth() / 1.417), (int) (cond.getHeight() / 10.56), 415, 30);
        nomeUtente.setFont(new Font("Bungee Regular", Font.BOLD, 24));
        this.add(nomeUtente);

        //label nome avversario
        nomeAvversario = new JLabel(nomePersonaggioAvversario);
        nomeAvversario.setHorizontalAlignment(nomeAvversario.CENTER);
        nomeAvversario.setBounds((int) (cond.getWidth() / 12.49), (int) (cond.getHeight() / 10.56), 415, 30);
        nomeAvversario.setFont(new Font("Bungee Regular", Font.BOLD, 24));
        this.add(nomeAvversario);

        //bottone invia messaggio
        inviaMessaggio = new JButton("invia");
        inviaMessaggio.setBounds((int) (cond.getWidth() / 1.76), (int) (cond.getHeight() / 1.552), (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 21.12));
        this.add(inviaMessaggio);
        inviaMessaggio.addActionListener(this);

        //textarea per chat
        chat = new JTextArea();
        chat.setBounds((int) (cond.getWidth() / 2.688), (int) (cond.getHeight() / 7.04), (int) (cond.getWidth() / 4.033), (int) (cond.getHeight() / 2.011));
        chat.setFont(new Font("Bungee Regular", Font.BOLD, 12));
        chat.setEditable(false);
        chat.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        this.add(chat);

        //textpane per il messaggio
        messaggio = new JTextPane();
        messaggio.setBounds((int) (cond.getWidth() / 2.688), (int) (cond.getHeight() / 1.552), (int) (cond.getWidth() / 5.531), (int) (cond.getHeight() / 21.12));
        messaggio.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        messaggio.setFont(new Font("Bungee Regular", Font.BOLD, 16));
        this.add(messaggio);

        //Vettore di bottoni
        arrayBottoni = new JButton[24];
        //Vettore di label
        arrayLabel = new JLabel[24];

        //Posizione dei bottoni(local) e delle label(opponent)
        //Distanza da sinistra 50
        int posXopponent = (int) (cond.getHeight() / 21.12);
        //1936-675
        int posXlocal = (int) (cond.getWidth() - (cond.getWidth() / 2.868));
        //posizione della y che è uguale per entrambi
        //Distanza dall'alto 150       
        int posY = (int) (cond.getHeight() / 7.04);
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
        arrayLabel[i].setBounds(posXopponent, posY, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        this.add(arrayLabel[i]);
        //vado ad inserire nei bottoni le immagini delle persone 
        img = ImageIO.read(getClass().getResource(cond.persona[i].getPercorso()));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        arrayBottoni[i].setIcon(new ImageIcon(img));
        arrayBottoni[i].setBounds(posXlocal, posY, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        this.add(arrayBottoni[i]);
        arrayBottoni[i].addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("FSchermata:------------------------------------------" + turno);
        if (turno) {
            if (e.getSource() == passaTurno) {
                System.out.println("FSchermataGioco: Passa Turno");
                turno = false;
                String stringaEliminati="";
                for (int i = 0; i < arrayBottoni.length; i++) {
                    if (cond.persona[i].isAttivo()) {
                        {
                            try {
                                img = ImageIO.read(getClass().getResource("Bottoni/sagomaO.jpg"));
                                img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
                                arrayBottoni[i].setIcon(new ImageIcon(img));
                                cond.persona[i].setEliminato(true);
                                stringaEliminati+=i+";";
                                System.out.println("FSchermataGioco: " + cond.persona[i].getNome());
                                this.repaint();
                            } catch (IOException ex) {

                                Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                }
                try {
                    cond.inviaRicevi.invia("f;"+stringaEliminati);
                } catch (IOException ex) {
                    Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == arrenditi) {
                System.out.println("FSchermataGioco: Arrenditi");
                try {
                    cond.inviaRicevi.invia("e;a;");
                } catch (IOException ex) {
                    Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
                cond.getMenu().setVisible(true);
                cond.setInizio();

                //invia("e;r;")
            } else if (e.getSource() == tenta) {
                System.out.println("FSchermataGioco: Tenta");
                if (tentativi > 0) {
                    //invia comando t + nome del tentativo
                    try {

                        cond.inviaRicevi.invia("t;" + this.inviaMessaggio.getText() + ";");
                    } catch (IOException ex) {
                        Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.chat.append("<" + nomeUtente + "> " + "Ha tentato con: " + this.messaggio.getText());
                    messaggio.setText("");
                    tentativi--;
                    nTentativiUtente.setText(tentativi + "/" + cond.getTentativi());
                } else if (tentativi == 0 && !(cond.getTentativi() == 0)) {

                    System.out.println("FShcermataGioco: tenativi finiti");
                }
                //invia("t;"+messaggio.getText()+";");
                //messaggio.setText("");
                //messaggioBool=true;
            } else if (e.getSource() == inviaMessaggio) {
                chat.append("<" + cond.getNomeUtente() + "> " + messaggio.getText() + "\n");
                try {
                    cond.inviaRicevi.invia("d;" + messaggio.getText() + ";");
                } catch (IOException ex) {
                    Logger.getLogger(FSchermataGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
                messaggio.setText("");

                //messaggioBool=true;
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

    public void setTurno() {
        this.turno = !turno;
    }

    public void setPedinaAvversario(int i) throws IOException {
        img = ImageIO.read(getClass().getResource("Bottoni/sagomaX.png"));
        img = Condivisa.resizeImage((BufferedImage) img, 1, (int) (cond.getWidth() / 19.36), (int) (cond.getHeight() / 8.448));
        arrayLabel[i].setIcon(new ImageIcon(img));
    }

    public String getNomePersonaggio() {
        return nomePersonaggioUtente;
    }

    public void setTentativiAvversario() {
        tentativiAvversario--;
    }

    public JTextArea getChat() {
        return chat;
    }

    public int getTentativi() {
        return tentativi;
    }

    public int getTentativiAvversario() {
        return tentativiAvversario;
    }

    public void setNomePersonaggioAvversario(String nomePersonaggioAvversario) {
        this.nomePersonaggioAvversario = nomePersonaggioAvversario;
    }

}
