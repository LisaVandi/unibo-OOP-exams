package a01c.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            var coord = cells.get(jb);
            var selectedCells = logic.selectCell(coord);

            if (!selectedCells.isEmpty()) {
                for (var cell : selectedCells) {
                    for (var entry : cells.entrySet()) {
                        if (entry.getValue().equals(cell)) {
                            entry.getKey().setText("0");
                        }
                    }
                }
            }

            if (logic.isClicked() && selectedCells.size() == size * size) {
                JOptionPane.showMessageDialog(this, "All cells are selected, closing application.");
                System.exit(0);
            }
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
    
}
