/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indovinachi;

/**
 *
 * @author Utente
 */
public class JPersona {

    private final String nome;
    private String percorso;
    private boolean attivo;
    private boolean eliminato;

    public JPersona(String nome, String percorso) {
        this.nome = nome;
        this.percorso = percorso;
        this.attivo = false;
        this.eliminato = false;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }

    public String getNome() {
        return nome;
    }

    public String getPercorso() {
        return percorso;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public boolean isEliminato() {
        return eliminato;
    }

    public void setEliminato(boolean eliminato) {
        this.eliminato = eliminato;
    }

}
