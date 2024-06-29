package a01b.e2;

import java.util.List;
import java.util.Set;

public interface Logic {
    void star(Coord coord);
    void unstar(Coord coord);
    boolean isOver();
    boolean isClicked(Coord coord);
    Set<Coord> getClickedCells();
    boolean isEmpty(Coord coord);
    List<Coord> getDiagonalCoords(Coord coord);
}
