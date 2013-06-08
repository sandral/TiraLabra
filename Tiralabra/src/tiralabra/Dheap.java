/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 *
 * @author root
 */
public class Dheap implements Heap {

    /**
     * Taulukko joka sisältää keon alkiot
     */
    private OmaTaulukko taulukko;
    /**
     * Kertoo kuinka paljon keossa on alkioita
     */
    private int heapSize;
    /**
     * Kertoo keon paikkaisuuden, oltava vähintään 2
     */
    private int d;
    /**
     * Asetetaan taulukon kooksi
     */
    private static int koko = 10;

    /**
     * Konstruktori
     *
     * @param size
     * @param d
     */
    public Dheap(int d) {
        if (d < 2) {
            throw new IllegalArgumentException("d:n oltava vähintään 2");
        }
        this.d = d;
        taulukko = new OmaTaulukko(koko);
        heapSize = 0;
    }

    public OmaTaulukko getTaulukko() {
        return taulukko;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getD() {
        return d;
    }

    public static int getKoko() {
        return koko;
    }

    /**
     * Tarkistaa, onko keko tyhjä
     *
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Lisaa kekoon parametrina annettavan luvun
     *
     * @param lisattava
     */
    public void insert(int lisattava) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }
        if (isEmpty()) {
            taulukko.setLuku(0, lisattava);
            heapSize++;
        } else {
            heapSize++;
            taulukko.setLuku(heapSize - 1, lisattava);
            kuljetaYlos(heapSize - 1);
        }
    }

    /**
     * Apumetodi metodille insert, vie lisätyn alkion oikealle paikalleen
     *
     * @param i
     */
    private void kuljetaYlos(int i) {
        if (i != 0) {
            int vanhempi = parent(i);
            if (taulukko.getLuku(vanhempi) > taulukko.getLuku(i)) {
                vaihdaKeskenaan(vanhempi, i);
                kuljetaYlos(vanhempi);

            }

        }
    }

    /**
     * Palauttaa keon pienimmän alkion
     *
     * @return
     * @throws tiralabra.Dheap.DaryHeapException
     */
    public int min() throws Exception {
        if (isEmpty()) {
            throw new Exception("Tyhjä keko");
        }
        return taulukko.getLuku(0);
    }

    public int parent(int indeksi) {
        return (indeksi - 1) / d;
    }

    /**
     * Palauttaa listan parametrina annetun indeksin lasten indekseistä.
     *
     * @param indeksi
     * @return
     */
    public int[] children(int indeksi) {
        int[] lapset = new int[d];
        for (int i = 1; i <= d; i++) {
            lapset[i - 1] = indeksi * d + i;
        }
        return lapset;
    }

    /**
     * Palauttaa ja poistaa keon pienimmän alkion
     *
     * @return
     * @throws tiralabra.Dheap.DaryHeapException
     */
    public int deleteMin() throws Exception {
        if (isEmpty()) {
            throw new Exception("keko on tyhja");
        }
        int pienin = min();
        taulukko.setLuku(0, taulukko.getLuku(heapSize - 1));
        heapSize--;
        kuljetaAlas(0);

        return pienin;
    }

    /**
     * Apumetodi, vaihtaa indekseissä i ja j sijaitsevien alkioiden paikat
     * taulukossa.
     *
     * @param i
     * @param j
     */
    private void vaihdaKeskenaan(int i, int j) {
        int x = taulukko.getLuku(i);
        taulukko.setLuku(i, taulukko.getLuku(j));
        taulukko.setLuku(j, x);
    }

    /**
     * Apumetodi deleteMin:lle, korjaa kekorakenteen, kun pienin on poistettu
     *
     * @param i
     */
    private void kuljetaAlas(int i) {
        int pienin = taulukko.getLuku(i);
        while (i < heapSize) {
            int pieninLapsi = i;
            int[] lapset = children(i);
            for (int j = 0; j < lapset.length; j++) {
                if (lapset[j] < heapSize && taulukko.getLuku(pieninLapsi) > taulukko.getLuku(lapset[j])) {
                    pieninLapsi = lapset[j];
                }
            }
            vaihdaKeskenaan(pieninLapsi, i);
            i++;
        }

    }
}
