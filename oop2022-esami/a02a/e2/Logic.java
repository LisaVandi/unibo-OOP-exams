package a02a.e2;

public interface Logic {
    boolean hit(Coord coord);
    void disableDiagonal(Coord coord);
    boolean isBishop(Coord coord);
    boolean isOver();
    void clear();
    boolean isAvailable(Coord coord);
}
