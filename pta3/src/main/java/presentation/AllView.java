package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * <p>AllView is the GUI Class that is used to see the products or clients in a JTable. It has as an instance variable a JTable that is to be displayed.</p>
 */
public class AllView extends JFrame {

    private JTable table;

    public AllView(JTable table){
        this.table = table;
        add(new JScrollPane(table));
        setBackground(Color.darkGray);
        setLocation(0,0);
        setSize(1650,1080);
        setVisible(true);
    }
}