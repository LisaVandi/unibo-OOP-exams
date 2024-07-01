package a03a.e2;

public class Test {

    /*
     * L'applicazione si comporti complessivamente come segue:
     * 1) all'inizio si posizionano in modo random le due torri ("*" quella dell'umano, "o" quella del computer)
     * 2) l'utente sceglie una posizione di destinazione per la sua torre clickando sulla casella d'arrivo
     * -- se clicka su una cella non consentita il click viene ignorato
     * -- se muove mangiando l'altra torre, ossia ci si va sopra, allora si stampi su console "umano vince" e si resetti il gioco
     * 3) fatta una mossa valida, allora muove il computer come segue:
     * -- se può mangiare la torre dell'umano, la mangia, si stampi su console "computer vince" e si resetti il gioco
     * -- altrimenti si muove in modo random (per direzione e per numero di caselle)
     * 4) il turno ripassa all'umano, tornando al passo 1) qui sopra
     */

    public static void main(String[] args) throws java.io.IOException {
        new GUI(4); 
    }
}
