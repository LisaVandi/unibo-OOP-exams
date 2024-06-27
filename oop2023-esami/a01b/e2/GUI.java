package a01b.e2;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Coord> cells = new HashMap<>(); // modified
    private final Logic logic; // added

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        // added
        this.logic = new LogicImpl(size); 
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
        	//jb.setText(String.valueOf(cells.get(jb))); // modified
            Coord coord = cells.get(jb);
            logic.clickCell(coord);
            updateButtons();
            if (logic.isBound())  {
                System.exit(0);
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(pos.toString());
                this.cells.put(jb, new Coord(j, i)); // modified
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void updateButtons() {
        for (Map.Entry<JButton, Coord> entry : cells.entrySet()) {
            var coord = entry.getValue();
            var jb = entry.getKey();
            if (logic.isClicked(coord)) {
                jb.setText(String.valueOf(logic.getClickedCells().get(coord)));
            } else {
                jb.setText("");
            }
        }
    }
    
}
