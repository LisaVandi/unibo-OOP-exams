package a01a.e2;

public interface Logic {
    // l'utente clicka su una cella qualunque della griglia
    boolean hasClicked(Coord cell);  
    // questa si numera incrementalmente
    void setCellNumber(Coord cell);
    int getCellNumber(Coord cell);
    // a patto che non ne selezioni una adiacente a una già numerata
    boolean isAdjWithNumber(Coord coord);
    // alla prima pressione su una cella adiacente a una numerata, 
    // allora tutte le celle numerate si spostano in alto-destra di una posizione 
    // ad ogni altra successiva pressione di qualunque cella, tutte le celle numerate si spostano ulteriormente in alto-destra di una posizione
    void movement();
    boolean isOutOfBounds();
    void exit();
}
