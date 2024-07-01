package a01c.e2;

import java.util.*;

public class LogicImpl implements Logic {
    private final int size;
    private final List<Coord> selectedCells = new ArrayList<>();
    private boolean isCompleted = false;
    private Coord firstCell;
    private Coord secondCell;
    private int clickCount = 0;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean isClicked() {
        return !selectedCells.isEmpty();
    }

    @Override
    public List<Coord> selectCell(Coord cell) {
        if (isCompleted) {
            return Collections.emptyList();
        }

        clickCount++;
        if (clickCount == 1) {
            firstCell = cell;
            selectedCells.add(cell);
        } else if (clickCount == 2) {
            secondCell = cell;
            selectedCells.add(cell);
        } else {
            int minX = Math.min(firstCell.row(), secondCell.row());
            int minY = Math.min(firstCell.col(), secondCell.col());
            int maxX = Math.max(firstCell.row(), secondCell.row());
            int maxY = Math.max(firstCell.col(), secondCell.col());

            // Extend rectangle on each subsequent click
            if (clickCount > 3) {
                minX = Math.max(0, minX - 1);
                minY = Math.max(0, minY - 1);
                maxX = Math.min(size - 1, maxX + 1);
                maxY = Math.min(size - 1, maxY + 1);
            }

            List<Coord> newCells = new ArrayList<>();
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    newCells.add(new Coord(i, j));
                }
            }

            // Check if the entire grid is covered
            if (newCells.size() == size * size) {
                isCompleted = true;
            }

            selectedCells.clear();
            selectedCells.addAll(newCells);

            return newCells;
        }

        return List.of(cell);
    }
}
