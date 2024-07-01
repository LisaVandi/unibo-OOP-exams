package a02a.e2;

import javax.swing.*;

import a02a.e2.Logic;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Coord> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);

        this.logic = new LogicImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                // button.setText(""+position);
                if (logic.putBishop(position)) {
                    updateGUI();
                    //button.setText("B");    
                }
                if (logic.reset()) {
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
        for (Map.Entry<JButton, Coord> entry : cells.entrySet()) {
            JButton button = entry.getKey();
            Coord coord = entry.getValue();
            if (logic.getSelectedCells().contains(coord)) {
                button.setEnabled(false);
            } else {
                button.setEnabled(true); // disabilito il pulsante
                button.setText(" ");
            }
        }
        for (Coord bishop : logic.getBishops()) {
            for (Map.Entry<JButton, Coord> entry : cells.entrySet()) {
                if (entry.getValue().equals(bishop)) {
                    entry.getKey().setText("B");
                }
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
