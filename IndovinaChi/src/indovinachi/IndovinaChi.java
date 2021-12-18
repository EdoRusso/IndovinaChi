/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 *
 * @author Utente
 */
public class IndovinaChi {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {  
         
         try {    
             FSchermataGioco gioco = new FSchermataGioco();
         } catch (HeadlessException ex) {
             Logger.getLogger(IndovinaChi.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(IndovinaChi.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    
}
