package a01a.e2;

public interface Logic {
    boolean star(Coord coord);
    boolean unstar(Coord coord);
    boolean isDiagonal(Coord c1, Coord c2, Coord c3);
    boolean isOver();
}
