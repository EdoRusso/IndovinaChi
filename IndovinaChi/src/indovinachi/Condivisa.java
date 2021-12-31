/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Utente
 */
public class Condivisa {

    public JPersona[] persona;
    public String[] avversario;
    public int Width;
    public int Height;

    public FImpostazioni impostazioni;
    public FMenu menu;
    public FSchermataGioco gioco;
    public FStart start;

    public Color c;

    public int tempo;
    public int tentativi;
    public String nomeUtente;
    
    public Condivisa() throws HeadlessException, IOException {
        System.out.println("Condivisa: dentro condivisa");
        Width = 0;
        Height = 0;
        persona = new JPersona[24];
        avversario = new String[24];
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
            avversario[i] = "Bottoni/sagoma.png";
        }

    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public void setAvversario(int indice) {
        avversario[indice] = "Bottoni/sagomaX.png";

    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height) {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }

    public FImpostazioni getImpostazioni() {
        return impostazioni;
    }

    public FMenu getMenu() {
        return menu;
    }

    public FSchermataGioco getGioco() {
        return gioco;
    }

    public FStart getStart() {
        return start;
    }

    public void setImpostazioni(FImpostazioni impostazioni) {
        this.impostazioni = impostazioni;
    }

    public void setMenu(FMenu menu) {
        this.menu = menu;
    }

    public void setGioco(FSchermataGioco gioco) {
        this.gioco = gioco;
    }

    public void setStart(FStart start) {
        this.start = start;
    }

    public Color getColore() {
        return c;
    }

    public void setColore(Color c) {
        this.c = c;
    }

    public void caricaFile(String file) throws IOException {

        FileWriter myWriter = new FileWriter("fileImpostazioni.txt");
        myWriter.write(file);
        myWriter.close();
        System.out.println("Condivisa: FileScritto");
    }

    public void leggiFile() throws FileNotFoundException, IOException {
        System.out.println("Condivisa: Dentro leggiFile");
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("fileImpostazioni.txt"));
        String line = reader.readLine();
        System.out.println("c-> " + line + "\n");
        // read next line
        //line = reader.readLine();
        String[] split = line.split(";");
        System.out.println("R " + split[0]+" "+Integer.parseInt(split[0]));
        System.out.println("G " + split[1]+" "+Integer.parseInt(split[1]));
        System.out.println("B " + split[2]+" "+Integer.parseInt(split[2]));
        
        Color colore = new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        setColore(colore);
        
        setNomeUtente(split[3]);
        setTempo(Integer.parseInt(split[4]));
        setTentativi(Integer.parseInt(split[5]));
        
        reader.close();
    }

    public int getTempo() {
        return tempo;
    }

    public int getTentativi() {
        return tentativi;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setTentativi(int tentativi) {
        this.tentativi = tentativi;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

}
