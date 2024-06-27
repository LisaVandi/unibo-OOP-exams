package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>(); // modified 
    private final Logic logic;
    private Coord coord;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);

        this.logic = new LogicImpl(size);

        ActionListener al = e -> {
            JButton jb = (JButton)e.getSource();
        	//jb.setText(String.valueOf(cells.get(jb)));
            if (!logic.hasClicked(coord)) {
                if (!logic.isAdjWithNumber(coord)) {
                    logic.setCellNumber(coord);
                    jb.setText(String.valueOf(logic.getCellNumber(coord)));
                } else {
                    logic.movement();
                    updateButtons();
                    if (logic.isOutOfBounds()) {
                        logic.exit();
                        System.exit(0);
                    }
                }
            } else {
                logic.movement();
                updateButtons();
                if (logic.isOutOfBounds()) {
                    logic.exit();
                    System.exit(0);
                }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb1 = new JButton(pos.toString());
                this.cells.put(jb1, new Coord(j, i));
                // jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    };
        
    }

    private void updateButtons() {
        for (var entry : cells.entrySet()) {
            Coord coord = entry.getValue();
            if (logic.hasClicked(coord)) {
                entry.getKey().setText(String.valueOf(logic.getCellNumber(coord)));
            } else {
                entry.getKey().setText("");
            }
        }
    }
}
