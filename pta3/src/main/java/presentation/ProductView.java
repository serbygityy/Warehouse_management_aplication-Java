package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>ProductView is the GUI Window set for configuring the products in the database, as having buttons for adding, removing, updating or viewing all products</p>
 */
public class ProductView extends JPanel {
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton backButton;
    private JFrame frame = new JFrame ("Products");

    public ProductView() {
        //construct components
        addButton = new JButton ("Add Product");
        editButton = new JButton ("Edit Product");
        deleteButton = new JButton ("Delete Product");
        viewButton = new JButton ("View All Products");
        backButton = new JButton("Go back");

        //adjust size and set layout
        setPreferredSize (new Dimension (327, 360));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (addButton);
        add (editButton);
        add (deleteButton);
        add (viewButton);
        add (backButton);

        //set component bounds (only needed by Absolute Positioning)
        addButton.setBounds (60, 20, 215, 45);
        addButton.setBackground(Color.gray);
        addButton.setForeground(Color.black);
        editButton.setBounds (60, 90, 215, 45);
        editButton.setBackground(Color.gray);
        editButton.setForeground(Color.black);
        deleteButton.setBounds (60, 160, 215, 45);
        deleteButton.setBackground(Color.gray);
        deleteButton.setForeground(Color.black);
        viewButton.setBounds (60, 230, 215, 45);
        viewButton.setBackground(Color.gray);
        viewButton.setForeground(Color.black);
        backButton.setBounds(60, 300, 215, 45);
        backButton.setBackground(Color.gray);
        backButton.setForeground(Color.black);
    }

    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible (true);
    }

    public void addActionListeners(ActionListener actionListener, ActionListener backListener){
        addButton.addActionListener(actionListener);
        editButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        backButton.addActionListener(backListener);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void close(){
        frame.setVisible(false);
    }

    public void addViewListener(ActionListener a){

        viewButton.addActionListener(a);
    }
}