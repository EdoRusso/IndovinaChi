/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Utente
 */
public class Condivisa {
    public JPersona[] persona;
    public String[] avversario;
    //FMenu menu;
    //FSchermataGioco gioco;
    public Condivisa() throws HeadlessException, IOException {

        persona = new JPersona[24];
        avversario =new String[24];
        //imposto nome,percorso di ogni singola persona
        persona[0] = new JPersona("susanna", "PersonaggiIndovinaChi/susanna.png");
        persona[1] = new JPersona("alfredo", "PersonaggiIndovinaChi/alfredo.png");
        persona[2] = new JPersona("filippo", "PersonaggiIndovinaChi/filippo.png");
        persona[3] = new JPersona("chiara", "PersonaggiIndovinaChi/chiara.png");
        persona[4] = new JPersona("paolo", "PersonaggiIndovinaChi/paolo.png");
        persona[5] = new JPersona("giuseppe", "PersonaggiIndovinaChi/giuseppe.png");
        persona[6] = new JPersona("samuele", "PersonaggiIndovinaChi/samuele.png");
        persona[7] = new JPersona("giorgio", "PersonaggiIndovinaChi/giorgio.png");
        persona[8] = new JPersona("anita", "PersonaggiIndovinaChi/anita.png");
        persona[9] = new JPersona("manuele", "PersonaggiIndovinaChi/manuele.png");
        persona[10] = new JPersona("marco", "PersonaggiIndovinaChi/marco.png");
        persona[11] = new JPersona("riccardo", "PersonaggiIndovinaChi/riccardo.png");
        persona[12] = new JPersona("tommaso", "PersonaggiIndovinaChi/tommaso.png");
        persona[13] = new JPersona("alessandro", "PersonaggiIndovinaChi/alessandro.png");
        persona[14] = new JPersona("carlo", "PersonaggiIndovinaChi/carlo.png");
        persona[15] = new JPersona("ernesto", "PersonaggiIndovinaChi/ernesto.png");
        persona[16] = new JPersona("guglielmo", "PersonaggiIndovinaChi/guglielmo.png");
        persona[17] = new JPersona("maria", "PersonaggiIndovinaChi/maria.png");
        persona[18] = new JPersona("roberto", "PersonaggiIndovinaChi/roberto.png");
        persona[19] = new JPersona("pietro", "PersonaggiIndovinaChi/pietro.png");
        persona[20] = new JPersona("anna", "PersonaggiIndovinaChi/anna.png");
        persona[21] = new JPersona("giacomo", "PersonaggiIndovinaChi/giacomo.png");
        persona[22] = new JPersona("davide", "PersonaggiIndovinaChi/davide.png");
        persona[23] = new JPersona("bernardo", "PersonaggiIndovinaChi/bernardo.png");
        
        //Imposto tutti gli avversari come sagome dato che non posso vedere le pedine dello sfidante
        for (int i = 0; i < avversario.length; i++) {
            avversario[i]="sagoma.png";
        }
        
    }


    public void setAvversario(int indice) {
        avversario[indice]="sagomaX.png";
        
    }
    
        public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }
}
