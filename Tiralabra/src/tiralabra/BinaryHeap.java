package tiralabra;

import java.util.Arrays;

public class BinaryHeap {

    /**
     * Taulukko joka sisältää keon alkiot.
     */
    private OmaTaulukko taulukko;
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
        taulukko = new OmaTaulukko(size);
        heapSize = 0;
    }

    public int getDefaultKoko() {
        return defaultKoko;
    }

    public void setDefaultKoko(int defaultKoko) {
        this.defaultKoko = defaultKoko;
    }

    public OmaTaulukko getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(OmaTaulukko taulukko) {
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
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }
        heapSize++;
        int i = heapSize;
        while (i > 1 && taulukko.getLuku(parent(i)) > k) {
            taulukko.setLuku(i - 1, taulukko.getLuku(parent(i)));
            i = parent(i) + 1;
        }
        taulukko.setLuku(i - 1, k);
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
            return taulukko.getLuku(0);
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
        int pienin = taulukko.getLuku(0);
        taulukko.setLuku(0, taulukko.getLuku(getHeapSize() - 1));
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
            if (taulukko.getLuku(l) < taulukko.getLuku(r)) {
                pienin = l;
            } else {
                pienin = r;
            }
            if (taulukko.getLuku(i) > taulukko.getLuku(pienin)) {
                swap(i, pienin);
                heapify(pienin);
            }

        } else if (l == heapSize && taulukko.getLuku(i) > taulukko.getLuku(l)) {
            swap(i, l);
        }
    }

    /**
     * Yhdistaa binaarikeon parametrina annettavan toisen kanssa yhdeksi keoksi.
     *
     * @param toinen Yhdistettava keko.
     */
    public void merge(BinaryHeap toinen) throws HeapException {
        while (taulukko.getLength() <= heapSize + toinen.heapSize) {
            taulukko.tuplaaJaKopioi();
        }
        int aloitusKohta = heapSize;
        heapSize = heapSize + toinen.heapSize;
        for (int i = aloitusKohta; i < heapSize; i++) {
            taulukko.setLuku(i, toinen.getTaulukko().getLuku(i - aloitusKohta));
        }
        //for (int i = heapSize/2; i >= 0; i--) {
        //   heapify(i);

        buildHeap(taulukko);
    }

    /**
     * Tekee taulukkoon kekorakenteen.
     *
     * @param taulukko keonnettava taulukko.
     */
    public void buildHeap(OmaTaulukko taulukko) {
        for (int i = taulukko.getLength() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Vaihtaa taulukon kahden alkioiden paikkoja keskenään.
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        int x = taulukko.getLuku(i);
        taulukko.setLuku(i, taulukko.getLuku(j));
        taulukko.setLuku(j, x);
    }

    public static void main(String[] args) throws HeapException {
        BinaryHeap keko = new BinaryHeap(5);
        BinaryHeap toinen = new BinaryHeap(5);

        for (int i = 5; i < 10; i++) {
            keko.heapInsert(i);
        }
        for (int i = 0; i < 5; i++) {
            toinen.heapInsert(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(keko.taulukko.getLuku(i));
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print(toinen.taulukko.getLuku(i));
        }
        System.out.println();


        keko.merge(toinen);


        for (int i = 0; i < keko.getTaulukko().getLength(); i++) {
            System.out.print(keko.getTaulukko().getLuku(i));


        }

        /*BinaryHeap keko = new BinaryHeap(5);
         BinaryHeap keko1 = new BinaryHeap(20);
         for (int i = 1; i < 16; i++) {
         System.out.println("taulukon koko: " + keko.taulukko.getLength());
         keko.heapInsert(i);
         keko1.heapInsert(16 - i);
         }
         for (int i = 0; i < 15; i++) {
         System.out.print(keko.getTaulukko().getLuku(i) + " ");
         }
         System.out.println();
         for (int i = 0; i < 15; i++) {
         System.out.print(keko1.getTaulukko().getLuku(i) + " ");
         */
    }

    public static class HeapException extends Exception {

        public HeapException(String keko_on_tyhjä) {
        }
    }
}

/**
 * Taulukko, jonka pituutta voidaan tarvittaessa kasvattaa.
 *
 * @author root
 */
class OmaTaulukko {

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

    public int getLuku(int i) {
        return taulu[i];
    }

    public void setLuku(int indeksi, int luku) {
        taulu[indeksi] = luku;
    }

    public int getLength() {
        return taulu.length;
    }
}
