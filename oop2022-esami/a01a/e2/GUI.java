package a01a.e2;

import javax.swing.*;
import java.util.*;
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
        	    var position = cells.get(button);
                // button.setText(""+position);
                Coord coord = cells.get(button); // su questa coord, faccio agire la logic
                if (logic.isEmpty(coord)) {
                    button.setText("*");
                    logic.star(coord);
                } else {
                    button.setText(" ");
                    logic.unstar(coord);
                }

                if (logic.isOver()) {
                    System.exit(0);
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
}
