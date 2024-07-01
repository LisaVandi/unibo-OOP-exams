package a03a.e2;

public interface Logic {

    enum Turn {
        HUMAN, 
        COMPUTER
    } 

    Turn gameTurn();
    /*
     * l'utente sceglie una posizione di destinazione 
     * per la sua torre clickando sulla casella d'arrivo
     */
    boolean moveHuman(Coord coord);
    
    /*
     * se può mangiare la torre dell'umano, la mangia, si stampi su console "computer vince" e si resetti il gioco
     * altrimenti si muove in modo random (per direzione e per numero di caselle). 
     * Turno ripassa all'umano.
     */
    void moveComputer();
    
    boolean validCell(Coord coord);
    
    /*
     * se muove mangiando l'altra torre, ossia ci si va sopra, 
     * allora si stampi su console "umano vince" e si resetti il gioco
     */
    void resetGame();
}
