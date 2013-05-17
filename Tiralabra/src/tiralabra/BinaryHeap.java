package tiralabra;

import java.util.Arrays;

public class BinaryHeap {

    /**
     * Taulukko joka sisältää keon alkiot.
     */
    private int[] taulukko;
    /**
     * Kertoo kuinka paljon keossa on alkioita.
     */
    private int heapSize;
    /**
     * Asettaa taulukon kooksi 10, jos käyttäjän antama koko on negatiivinen.
     */
    private int defaultKoko = 10;

    /**
     * Konstruktori
     *
     * @param size Luo keon, johon mahtuu näin monta alkiota.
     */
    public BinaryHeap(int size) {
        if (size <= 0) {
            size = defaultKoko;
        }
        taulukko = new int[size];
        heapSize = 0;
    }

    public int getDefaultKoko() {
        return defaultKoko;
    }

    public void setDefaultKoko(int defaultKoko) {
        this.defaultKoko = defaultKoko;
    }

    public int[] getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(int[] taulukko) {
        this.taulukko = taulukko;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    /**
     * Tarkistaa on keko tyhjä
     *
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Lisää parametrina annettavan luvun kekoon.
     *
     * @param k lisättävä luku
     */
    public void heapInsert(int k) {
        heapSize++;
        int i = heapSize;
        while (i > 1 && taulukko[parent(i)] > k) {
            taulukko[i - 1] = taulukko[parent(i)];
            i = parent(i) + 1;
        }
        taulukko[i - 1] = k;
    }

    /**
     * Palauttaa keon pienimmän alkion.
     *
     * @return
     * @throws tiralabra.BinaryHeap.HeapException ilmoittaa virheestä, jos keko
     * on tyhjä.
     */
    public int heapMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        } else {
            return taulukko[0];
        }
    }

    /**
     * Palauttaa keon pienimmän alkion ja poistaa sen keosta
     *
     * @return
     * @throws tiralabra.BinaryHeap.HeapException ilmoittaa virheestä, jos keko
     * on tyhjä.
     */
    public int heapDelMin() throws HeapException {
        if (isEmpty()) {
            throw new HeapException("tyhjä Keko");
        }
        int pienin = taulukko[0];
        taulukko[0] = taulukko[getHeapSize() - 1];
        heapSize--;
        heapify(0);
        return pienin;
    }

    /**
     * Palauttaa parametrina annetun indeksin kohdalla olevan solmun vasemman
     * lapsen indeksin.
     *
     * @param i
     * @return
     */
    public int left(int i) {
        return 2 * (i) + 1;
    }

    /**
     * Palauttaa paratmerina annetun indeksin kohdalla olevan solmun oikean
     * lapsen indeksin.
     *
     * @param i
     * @return
     */
    public int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * Palauttaa parametrina annetun indeksin kohdalla olevan solmun vanhemman
     * indeksin.
     *
     * @param i
     * @return
     */
    public int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Korjaa kekorakenteen, jos se on indeksin i kohdalta rikki.
     *
     * @param i
     */
    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int pienin;
        if (r < heapSize) {
            if (taulukko[l] < taulukko[r]) {
                pienin = l;
            } else {
                pienin = r;
            }
            if (taulukko[i] > taulukko[pienin]) {
                swap(i, pienin);
                heapify(pienin);
            }
        } else if (l == heapSize && taulukko[i] > taulukko[l]) {
            swap(i, l);
        }
    }

    /**
     * Yhdistaa binaarikeon parametrina annettavan toisen kanssa yhdeksi keoksi.
     *
     * @param toinen Yhdistettava keko.
     */
    public void merge(BinaryHeap toinen) throws HeapException {
        //BinaryHeap yhdistetty = new BinaryHeap(heapSize + toinen.heapSize);
        int[] uusi = new int[heapSize + toinen.getHeapSize()];
        for (int i = 0; i < heapSize; i++) {
            uusi[i] = heapDelMin();
        }
        for (int i = heapSize; i < uusi.length; i++) {
            uusi[i] = toinen.heapDelMin();
        }
        buildHeap(uusi);
        //luodaan uusi taulukko, johon kopioidaan molemmat keot ja suoritetaan buildheap.
    }

    /**
     * Tekee taulukkoon kekorakenteen.
     *
     * @param taulukko keonnettava taulukko.
     */
    public void buildHeap(int[] taulukko) {
        for (int i = taulukko.length / 2; i > 0; i--) {
            heapify(i);
        }
    }

    public static void main(String[] args) {
        BinaryHeap keko = new BinaryHeap(20);
        BinaryHeap keko1 = new BinaryHeap(20);
        for (int i = 1; i < 16; i++) {
            keko.heapInsert(i);
            keko1.heapInsert(16 - i);
        }
        for (int i = 0; i < 15; i++) {
            System.out.print(keko.getTaulukko()[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 15; i++) {
            System.out.print(keko1.getTaulukko()[i] + " ");
        }
        
    }
    

    /**
     * Vaihtaa taulukon kahden alkioiden paikkoja keskenään.
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        int x = taulukko[i];
        taulukko[i] = taulukko[j];
        taulukko[j] = x;
    }

    public static class HeapException extends Exception {

        public HeapException(String keko_on_tyhjä) {
        }
    }
}
