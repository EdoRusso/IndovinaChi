/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.HeadlessException;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edo
 */
public class InviaRiceviThread extends Thread {

    private DatagramSocket peer;
    private boolean ifConnesso;
    private FSchermataGioco frameGrafico;
    private String nicknameMittente;
    private String nicknameDestinatario;
    private String ipDestinatarioTmp;
    private String ipDestinatarioConnesso;
    private Condivisa cond;
    public InviaRiceviThread(FSchermataGioco frameGrafico) throws SocketException {
        ifConnesso = false;
        peer = new DatagramSocket(2003);
        this.frameGrafico = frameGrafico;
        nicknameDestinatario = "unknown";

    }

    public InviaRiceviThread(Condivisa cond) {
        this.cond = cond;
        cond.setInviaRicevi(this);
    }

    public void setIpDestinatario(String ipDestinatario) {
        this.ipDestinatarioTmp = ipDestinatario;
    }

    public void setNicknameMittente(String nickname) {
        this.nicknameMittente = nickname;
    }

    public void setipDestinatario(String ipDestinatario) {
        this.ipDestinatarioTmp = ipDestinatario;
    }

    public String getNicknameDestinatario() {
        return nicknameDestinatario;
    }

    String ricevi() throws IOException {
        byte[] bufferRisposta = new byte[1500];
        DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
        peer.receive(pacchettoRisposta);
        ipDestinatarioTmp = pacchettoRisposta.getAddress().toString().substring(1);
        return new String(bufferRisposta);
    }

    synchronized void invia(String messaggio) throws UnknownHostException, IOException {
        byte[] di = messaggio.getBytes();
        DatagramPacket pacchettoInvia = new DatagramPacket(di, di.length);
        InetAddress A = InetAddress.getByName(ipDestinatarioTmp);
        pacchettoInvia.setAddress(A);
        pacchettoInvia.setPort(2003);
        peer.send(pacchettoInvia);
    }

    @Override
    public void run() {
        String messRicevuto = "";
        while (true) {
            try {
                //trovo l'ip del destinatario
                messRicevuto = ricevi();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            System.out.println(messRicevuto);
            String[] vettElementi = messRicevuto.split(";");
            System.out.println(vettElementi[0]);
            //controllo richiesta di connessione se non esistente
            if (vettElementi[0].equals("c") && ifConnesso == false) {
                //richiesta di connessione
                int scelta = JOptionPane.showConfirmDialog(frameGrafico, vettElementi[1] + " vuole connettersi", "Richiesta connessione...", JOptionPane.YES_NO_OPTION);
                if (scelta == 0) {
                    nicknameDestinatario = vettElementi[1];
                    System.out.println(vettElementi[1]);
                    try {
                        invia("y;" + nicknameMittente);
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    frameGrafico.nomeAvversario.setText(nicknameDestinatario);
                    ifConnesso = true;
                    cond.setTempo(Integer.parseInt(vettElementi[2]));
                    cond.setTentativi(Integer.parseInt(vettElementi[3])); 
//                    try {
//                        frameGrafico=new FSchermataGioco(cond);
//                    } catch (HeadlessException ex) {
//                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//                    }
                } else {
                    try {
                        invia("n;");
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            } else if (vettElementi[0].equals("m") && ifConnesso == true && ipDestinatarioConnesso == ipDestinatarioTmp) {
                //visualizzo nel frame grafico il messaggio 
                frameGrafico.chat.append("<" + nicknameDestinatario + "> " + vettElementi[1] + "\n");

            } //ricevo un messaggio senza una connessione/con destinatario differente,invio n; 
            else if (vettElementi[0].equals("e") && ifConnesso == true && ipDestinatarioConnesso == ipDestinatarioTmp) {
                System.out.println("Complimenti hai vinto" + vettElementi[1]);
                //frameGrafico.inizio();
                frameGrafico.dispose();
                cond.getMenu().setVisible(true);
            } //ricevo una chiusura connessione  senza una connessione/con destinatario differente
            else if (vettElementi[0].equals("y") && ifConnesso == false) {
                try {
                    //per confermare la connessione
                    invia("y;");
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                nicknameDestinatario = vettElementi[1];
                ipDestinatarioConnesso = ipDestinatarioTmp;
                ifConnesso = true;
            } else if (vettElementi[0].equals("f") && ifConnesso == true) {
                frameGrafico.setTurno();
                for (int i = 1; i < vettElementi.length; i++) {
                    try {
                        frameGrafico.setPedinaAvversario(Integer.parseInt(vettElementi[i]));
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            } else if (vettElementi[0].equals("t") && ifConnesso == true) {
                if (frameGrafico.getNomePersonaggio().equals(vettElementi[1])) {
                    try {
                        invia("e;w;");
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        invia("h;");
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(InviaRiceviThread.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }

                }
            } else if (vettElementi[0].equals("h") && ifConnesso == true) {
                frameGrafico.setTentativiAvversario();
            }

        }
    }
}
