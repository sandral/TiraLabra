/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 * Taulukko, jonka pituutta voidaan tarvittaessa kasvattaa.
 *
 * @author root
 */
 
public class OmaTaulukko {

    private int[] taulu;

    /**
     * Konstruktori
     *
     * @param koko
     */
    public OmaTaulukko(int koko) {
        taulu = new int[koko];
    }

    /**
     * Kaksinkertaistaa taulukon pituuden ja kopioi alkuperäisen taulukon
     * sisällön uuteen pidempään taulukkoon.
     */
    public void tuplaaJaKopioi() {
        int[] uusi = new int[2 * taulu.length];
        for (int i = 0; i < taulu.length; i++) {
            uusi[i] = taulu[i];
        }
        taulu = uusi;
    }

    public int get(int i) {
        return taulu[i];
    }

    public void set(int indeksi, int i) {
        taulu[indeksi] = i;
    }

    public int getLength() {
        return taulu.length;
    }
}
