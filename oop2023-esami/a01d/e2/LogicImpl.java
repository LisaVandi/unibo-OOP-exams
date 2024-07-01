package a01d.e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;

public class LogicImpl implements Logic {
    private int size;
    private Coord center;
    private boolean clicked;
    private Set<Coord> selectedCells = new HashSet<>();

    public LogicImpl(final int size) {
        this.size = size;
        this.clicked = false;
    }

    @Override
    public boolean hasClicked() {
        return clicked;
    }

    @Override
    public void disableCoord(Coord clickedCoord) {
        // This method might not be needed depending on the GUI implementation
    }

    @Override
    public boolean hasFinished() {
        return selectedCells.stream().anyMatch(coord -> !isCellWithinBounds(coord));
    }

    @Override
    public void selectCenter(Coord center) {
        this.center = center;
        updateSelectedCells();
        this.clicked = true;
    }

    @Override
    public void moveSquare(Direction direction) {
        switch (direction) {
            case UP: center = new Coord(center.row(), center.col() - 1); break;
            case DOWN: center = new Coord(center.row(), center.col() + 1); break;
            case LEFT: center = new Coord(center.row() - 1, center.col()); break;
            case RIGHT: center = new Coord(center.row() + 1, center.col()); break;
        }
        updateSelectedCells();
    }

    @Override
    public boolean isCellWithinBounds(Coord cell) {
        return cell.row() >= 0 && cell.row() < size && cell.col() >= 0 && cell.col() < size;
    }

    @Override
    public void reset() {
        clicked = false;
        selectedCells.clear();
    }

    @Override
    public Set<Coord> getSelectedCells() {
        return selectedCells;
    }

    private void updateSelectedCells() {
        selectedCells.clear();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                Coord coord = new Coord(center.row() + i, center.col() + j);
                if (isCellWithinBounds(coord)) {
                    selectedCells.add(coord);
                }
            }
        }
    }

    public int getSize() {
        return this.size;    
    }
}
