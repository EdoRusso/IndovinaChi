/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Utente
 */
public class FMenu extends JFrame {

    JButton bottone;

    public FMenu() {
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Finestra");
        this.setLayout(null);

        bottone = new JButton();
        bottone.setBounds(100, 100, 100, 125);

        Clicklistener click = new Clicklistener();
        bottone.addActionListener(click);

        //Image img = ImageIO.read(getClass().getResource("paolo.png"));
        //img = resizeImage((BufferedImage) img, 1, 100, 125);
        //bottone.setIcon(new ImageIcon(img));
        //bottone.setText("C");
        this.add(bottone);

        this.setVisible(true);
    }

    private class Clicklistener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bottone) {
                System.out.println("No");

            }

        }
    }

}
