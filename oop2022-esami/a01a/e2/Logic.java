package a01a.e2;

import java.util.Set;

public interface Logic {
    boolean isEmpty(Coord coord);
    boolean isClicked(Coord coord);
    void star(Coord coord);
    void unstar(Coord coord);
    boolean isOver();
    Set<Coord> getClickedCoords();
    boolean isDiagonal(Coord c1, Coord c2, Coord c3);
}
