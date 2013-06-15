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
 
public class OmaTaulukko2 {

    private Integer[] taulu;

    /**
     * Konstruktori
     *
     * @param koko
     */
    public OmaTaulukko2(int koko) {
        taulu = new Integer[koko];
    }

    /**
     * Kaksinkertaistaa taulukon pituuden ja kopioi alkuperäisen taulukon
     * sisällön uuteen pidempään taulukkoon.
     */
    public void tuplaaJaKopioi() {
        Integer[] uusi = new Integer[2 * taulu.length];
        for (Integer i = 0; i < taulu.length; i++) {
            uusi[i] = taulu[i];
        }
        taulu = uusi;
    }

    public Integer getLuku(Integer i) {
        return taulu[i];
    }

    public void setLuku(int indeksi, Integer luku) {
        taulu[indeksi] = luku;
    }

    public int getLength() {
        return taulu.length;
    }
}
