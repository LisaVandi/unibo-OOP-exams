package a04.e2;

public interface Logic {
    void resetGame();
    boolean isValidMove(Coord src, Coord dst);
    boolean moveRook(Coord coord);
    void moveKing();

}
