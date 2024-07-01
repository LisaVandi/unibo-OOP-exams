package a04.e2;

public class Test {

    /*
     * L'applicazione si comporti complessivamente come segue:
     * 1) all'inizio si posizionano R e K in modo random su due celle diverse
     * 2) l'utente clicka una posizione valida d'arrivo per la sua torre R
     * -- se è valida (ossia corrisponde ad un movimento in orizzontale o verticale di quante caselle si vuole,
     *    ma senza saltare oltre il re) allora la torre si trasferisce lì
     * -- se la torre si mette al posto del re, ossia lo mangia, si scriva "Vittoria" su console e si ricomincia 
     *    la partita d'accapo, ossia dal punto 1)
     * 3) mossa la torre, il computer muove subito il re K, come segue:
     * -- le sue mosse possibili sono verso una casella adiacente in orizzontale, verticale o diagonale
     * -- se il re può mangiare la torre muovendosi, allora la mangia: in tal caso si chiuda l'applicazione
     * -- altrimenti il re si muove in modo random in una delle celle consentite
     * 4) si torna al punto 2
     */

    public static void main(String[] args) throws java.io.IOException {
        new GUI(4); 
    }
}
