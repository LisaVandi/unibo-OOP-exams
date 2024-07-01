package a02b.e2;

import java.util.Set;

public interface Logic {
    Set<Coord> getSelectedCells();
    boolean star(Coord coord);
    boolean unstar(Coord coord);
    boolean checkThreeStarsDiagonal(Coord c1, Coord c2, Coord c3);
    boolean isOver();
    void reset();
}
