package a01d.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        this.logic = new LogicImpl(size);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            Coord clickedCoord = cells.get(jb);
        	// jb.setText(String.valueOf(cells.get(jb)));
            if (!logic.hasClicked()) {
                logic.selectCenter(clickedCoord);
            } else if (isEdgeCell(clickedCoord)) {
                Direction direction = getDirection(clickedCoord);
                if (direction != null) {
                    logic.moveSquare(direction);
                    if (logic.hasFinished()) {
                        System.exit(0);
                    }
                }
            }
            updateGUI();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(pos.toString());
                this.cells.put(jb, new Coord(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private boolean isEdgeCell(Coord clickedCoord) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEdgeCell'");
    }

    private Direction getDirection(Coord clickedCoord) {
        if (clickedCoord.col() == 0) return Direction.UP;
        if (clickedCoord.col() == ((LogicImpl) logic).getSize() - 1) return Direction.DOWN;
        if (clickedCoord.row() == 0) return Direction.LEFT;
        if (clickedCoord.row() == ((LogicImpl) logic).getSize() - 1) return Direction.RIGHT;
        return null;
    }

    private void updateGUI() {
        for (Map.Entry<JButton, Coord> entry : cells.entrySet()) {
            Coord coord = entry.getValue();
            JButton jb = entry.getKey();
            if (logic.getSelectedCells().contains(coord)) {
                jb.setText("*");
            } else {
                jb.setText("");
            }
        }
    }
}
