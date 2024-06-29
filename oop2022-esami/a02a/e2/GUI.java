package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Coord> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        this.logic = new LogicImpl(size);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    Coord position = cells.get(button);
                // button.setText(""+position);
                if (logic.hit(position)) {
                    button.setText("B"); 
                    logic.disableDiagonal(position);
                    
                    // aggiorna anche logica
                }
                if (logic.isOver()) {
                    logic.clear();
                    redraw();
                }
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Coord(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void redraw(){
        for (var entry: cells.entrySet()){
            entry.getKey().setEnabled(this.logic.isAvailable(entry.getValue()));
            entry.getKey().setText(logic.isBishop(entry.getValue()) ? "B" : "");
        }
    }    
}
