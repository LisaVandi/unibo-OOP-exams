package a01d.e2;

import java.util.Set;

public interface Logic {
    boolean hasClicked();
    void disableCoord(Coord clickedCoord);
    boolean hasFinished();
    void selectCenter(Coord center);
    void moveSquare(Direction direction);
    boolean isCellWithinBounds(Coord cell);
    void reset();
    Set<Coord> getSelectedCells();
}
