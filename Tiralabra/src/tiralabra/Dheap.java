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
     * Konstruktori luo uuden tyhjän keon, jonka paikkaisuus on vähintään 2
     *
     * @param d parametri, joka kertoo keon paikkaisuuden, oltava vähintään 2
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
     * @return palauttaa true, jos keon koko on 0
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Lisaa kekoon parametrina annettavan luvun
     *
     * @param lisattava kekoon lisättävä luku
     */
    public void insert(int lisattava) {
        if (heapSize == taulukko.getLength()) {
            taulukko.tuplaaJaKopioi();
        }
        if (isEmpty()) {
            taulukko.set(0, lisattava);
            heapSize++;
        } else {
            heapSize++;
            taulukko.set(heapSize - 1, lisattava);
            kuljetaYlos(heapSize - 1);
        }
    }

    /**
     * Apumetodi metodille insert, vie lisätyn alkion oikealle paikalleen
     * taulukossa, jos keko rakenne on mennyt indeksin i kohdalta rikki
     * @param i indeksi, jonka kohdalta kekorakenne on mennyt rikki
     */
    private void kuljetaYlos(int i) {
        if (i != 0) {
            int vanhempi = parent(i);
            if (taulukko.get(vanhempi) > taulukko.get(i)) {
                vaihdaKeskenaan(vanhempi, i);
                kuljetaYlos(vanhempi);

            }

        }
    }

    /**
     * Palauttaa keon pienimmän alkion
     *
     * @return keon pienin alkio
     */
    public int min() { 
        return taulukko.get(0);
    }
    /**
     * Palauttaa alkion vanhemman indeksin.
     * @param indeksi missa alkio jonka vanhempi halutaan tietaa
     * sijaitsee
     * @return vanhemman indeksi
     */
    public int parent(int indeksi) {
        return (indeksi - 1) / d;
    }

    
    /**
     * Palauttaa parametrina annetun indeksin ensimmäisen lapsen indeksin
     *
     * @param indeksi alkion, jonka lapset halutaan tietää, indeksi
     * @return ensimmäisen lapsen indeksi
     */
    
    public int firstChild(int indeksi) {
            return indeksi * d + 1;
    }
    

    /**
     * Palauttaa ja poistaa keon pienimmän alkion
     *
     * @return keon pienin alkio
     */
    public int deleteMin() {
        int pienin = min();
        taulukko.set(0, taulukko.get(heapSize - 1));
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
        int x = taulukko.get(i);
        taulukko.set(i, taulukko.get(j));
        taulukko.set(j, x);
    }

    /**
     * Korjaa kekorakenteen, jos se on mennyt rikki indeksin i kohdalta
     *
     * @param i indeksi, jonka kohdalta kekorakenne on mennyt rikki
     */
    private void kuljetaAlas(int i) {
        while (i < heapSize) {
            if (firstChild(i) >= heapSize) {
                break;
            }
            
            int pieninLapsi = firstChild(i);
            for (int j = firstChild(i)+1; j < Math.min(firstChild(i) + d, heapSize); j++) {
                if (taulukko.get(pieninLapsi) > taulukko.get(j)) {
                    pieninLapsi = j;
                }
            }
            vaihdaKeskenaan(pieninLapsi, i);
            i = pieninLapsi;
        }

    }

    /**
     * Pienentää parametrina annettavassa taulukon kohdassa olevan solmun
     * avainta jos se on suurempi kuin luku.
     *
     * @param kohta taulukonindeksi, missä pienennettävä luku sijaitsee
     * @param luku lukuarvo, joksi alkiota pienennetään
     */
    public void decreaseKey(int kohta, int luku) {
        if (taulukko.get(kohta) > luku) {
            taulukko.set(kohta, luku);
            kuljetaYlos(kohta);
        }
    }

    /**
     * Kasvattaa parametrina annettavassa taulukonkohtassa olevan solmun avainta
     * jos sen on pienempi kuin annettu luku
     *
     * @param kohta taulukonindeksi, missä suurennettava luku sijaitsee
     * @param luku lukuarvo, joksi alkiota suurennetaan
     */
    public void increaseKey(int kohta, int luku) {
        if (taulukko.get(kohta) < luku) {
            taulukko.set(kohta, luku);
            kuljetaAlas(kohta);
        }
    }

    /**
     * Poistaa ja palauttaa parametrina annettavassa taulukonkohdassa olevan
     * luvun
     *
     * @param indeksi taulukonkohta, missä poistettava alkio sijaitsee
     * @return taulukonkohdassa indeksi sijaitseva alkio
     */
    public int delete(int indeksi) {
        decreaseKey(indeksi, min() - 1);
        return deleteMin();
        
    }

    
}
