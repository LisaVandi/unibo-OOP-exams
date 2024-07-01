package a02b.e2;

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
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        main.add(BorderLayout.SOUTH, new JButton("Check > Restart"));

        this.logic = new LogicImpl(size);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                if (logic.star(position)) {
                    button.setText("*");
                    updateGUI();
                } else if (logic.unstar(position)) {
                    button.setText(" ");
                    updateGUI();
                }
                if (logic.isOver()) {
                    logic.reset();
                    resetGUI();
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

    private void updateGUI() {
        for (JButton button : cells.keySet()) {
            Coord coord = cells.get(button);
            if (logic.getSelectedCells().contains(coord)) {
                button.setEnabled(false);
            }
        }
    }

    private void resetGUI() {
        for (JButton button : cells.keySet()) {
            button.setEnabled(true);
            button.setText(" ");
        }
    }
}
