package tiralabra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import tiralabra.OmaTaulukko;

public class BinaryHeapOlioilla implements Heap {

    /**
     * Taulukko joka sisältää keon alkiot.
     */
    private OmaTaulukko2 taulukko;
    /**
     * Kertoo kuinka paljon keossa on alkioita.
     */
    private int heapSize;
    /**
     * Asettaa taulukon kooksi 10, jos käyttäjän antama koko on negatiivinen.
     */
    private static int defaultKoko = 10;

    /**
     * Konstruktori defaultKoko
     *
     * @param size Luo keon, johon mahtuu näin monta alkiota.
     */
    public BinaryHeapOlioilla(int size) {
        if (size <= 0) {
            size = defaultKoko;
        }
        taulukko = new OmaTaulukko2(size);
        heapSize = 0;
    }

    public BinaryHeapOlioilla() {
        this(50);
    }

    public int getDefaultKoko() {
        return defaultKoko;
    }

    public void setDefaultKoko(int defaultKoko) {
        this.defaultKoko = defaultKoko;
    }

    public OmaTaulukko2 getTaulukko() {
        return taulukko;
    }

    public void setTaulukko(OmaTaulukko2 taulukko) {
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
     * @return palauttaan true, jos keko on tyhjä.
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Lisää parametrina annettavan luvun kekoon.
     *
     * @param k lisättävä luku.
     */
    public void insert(int lisattava) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }
        heapSize++;
        taulukko.setLuku(heapSize - 1, lisattava);
        siftUp(heapSize - 1);
    }

    /**
     * Apumetodi heapInsertille, korjaa kekoehdon, mikäli se on mennyt rikki
     * indeksin k kohdalta.
     *
     * @param k indeksi, jonka kohdalta kekoehto on mahdollisesti mennyt rikki.
     */
    private void siftUp(int k) {
        if (k != 0) {
            int vanhempi = parent(k);
            if (taulukko.getLuku(vanhempi) > taulukko.getLuku(k)) {
                swap(vanhempi, k);
                siftUp(vanhempi);

            }

        }
    }

    /**
     * Palauttaa keon pienimmän alkion.
     *
     * @return kekotaulukon ensimmäinen, eli keon pienin alkio.
     */
    public int min() {
        return taulukko.getLuku(0);
    }

    /**
     * Palauttaa keon pienimmän alkion ja poistaa sen keosta, sekä 
     * korjaa kekoehdon, jos se on poiston jälkeen mennyt rikki.
     *
     * @return kekotaulukon ensimmäinen eli keon pienin alkio.
     */
    public int deleteMin() {
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
     * @param i indeksi, jonka vasemman lapsen indeksi halutaan tietää.
     * @return indeksin i vasemman lapsen indeksi.
     */
    public int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Palauttaa paratmerina annetun indeksin kohdalla olevan solmun oikean
     * lapsen indeksin.
     *
     * @param i indeksi, jonka oikean lapsen indeksi halutaan tietää
     * @return indeksin i oikean lapsen indeksi.
     */
    public int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * Palauttaa parametrina annetun indeksin kohdalla olevan solmun vanhemman
     * indeksin.
     *
     * @param i indeksi, jonka vanhemman indeksi halutaan tietää
     * @return indeksin i vanhemman indeksi.
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

        } else if (l == heapSize - 1 && taulukko.getLuku(i) > taulukko.getLuku(l)) {
            swap(i, l);
        }
    }

    /**
     * Yhdistaa binaarikeon parametrina annettavan toisen kanssa yhdeksi keoksi.
     *
     * @param toinen Yhdistettava keko.
     */
    public void union(BinaryHeapOlioilla toinen) {
        while (taulukko.getLength() <= heapSize + toinen.heapSize) {
            taulukko.tuplaaJaKopioi();
        }
        int aloitusKohta = heapSize;
        heapSize = heapSize + toinen.heapSize;
        for (int i = aloitusKohta; i < heapSize; i++) {
            taulukko.setLuku(i, toinen.getTaulukko().getLuku(i - aloitusKohta));
        }
        buildHeap(taulukko);
    }

    /**
     * Järjestää taulukon sisällön siten, että taulukko kuvailee keon.
     *
     * @param taulukko keonnettava taulukko.
     */
    public void buildHeap(OmaTaulukko2 taulukko) {
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

    /**
     * Pienentää parametrina annettavassa taulukon kohdassa olevan solmun
     * avainta, jos se on suurempi kuin annettu luku
     *
     * @param kohta
     * @param luku
     */
    public void decreaseKey(int kohta, int luku) {
        if (taulukko.getLuku(kohta) > luku) {
            taulukko.setLuku(kohta, luku);
            heapify(kohta);
        }
    }

    /**
     * Poistaa ja palauttaa parametrinä annettavassa taulukonkohdassa olevan
     * luvun
     *
     * @param indeksi
     * @return
     */
    public int delete(int indeksi) throws Exception {
        if (indeksi < 0) {
            throw new Exception("indeksi ei voi olla negatiivinen");
        } else {
            decreaseKey(indeksi, min() - 1);
            return deleteMin();
        }

    }

    public static void main(String[] args) throws Exception {
        /*
         for (int i = 3; i > 0; i--) {
         keko.insert(i);
         }
        
         for (int i = 0; i < 3; i++) {
         System.out.print(keko.taulukko.getLuku(i) + " ");
         }
         /*
         /*
         for (int i = 15; i > 0; i--) {
         keko.insert(i);

         for (int j = 0; j < 15 - i; j++) {
         System.out.print(keko.taulukko.getLuku(j) + " ");
         }
         System.out.println();

         }

         for (int i = 0; i < 15; i++) {
         System.out.print(keko.taulukko.getLuku(i) + " vasen: " + keko.taulukko.getLuku(keko.left(i)) + " oikea: " + keko.taulukko.getLuku(keko.right(i)));
         System.out.println();
         }

         System.out.println();

         while (!keko.isEmpty()) {
         System.out.print(keko.deleteMin() + " ");

         }
         */
        /*
         for (int i = 5; i < 8; i++) {
         keko.insert(i);
         }
         for (int i = 0; i < 5; i++) {
         toinen.insert(i);
         }
         for (int i = 0; i < 4; i++) {
         System.out.print(keko.taulukko.getLuku(i));
         }
         System.out.println();
         for (int i = 0; i < 5; i++) {
         System.out.print(toinen.taulukko.getLuku(i));
         }
         System.out.println();


         keko.union(toinen);
         System.out.println("koko nyt: " + keko.heapSize);

         for (int i = 0; i < keko.getTaulukko().getLength(); i++) {
         System.out.print(keko.getTaulukko().getLuku(i));
            

         }
         */
        /*BinaryHeap keko = new BinaryHeap(5);
         BinaryHeap keko1 = new BinaryHeap(20);
         for (int i = 1; i < 16; i++) {
         System.out.println("taulukon koko: " + keko.taulukko.getLength());
         keko.insert(i);
         keko1.insert(16 - i);
         }
         for (int i = 0; i < 15; i++) {
         System.out.print(keko.getTaulukko().getLuku(i) + " ");
         }
         System.out.println();
         for (int i = 0; i < 15; i++) {
         System.out.print(keko1.getTaulukko().getLuku(i) + " ");
         */
    }
}
