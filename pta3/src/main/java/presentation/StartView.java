package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartView extends JPanel {
    private JButton clientButton;
    private JButton productsButton;
    private JButton orderButton;
    private JFrame frame = new JFrame();

    /**
     * <p>StartView represents the first GUI window met at the program start up. It contains the button that selects which table of the database is to be configured.</p>
     */
    public StartView() {
        //construct components
        clientButton = new JButton ("Configure Clients");
        productsButton = new JButton ("Configure Products");
        orderButton = new JButton ("Create an order");

        //adjust size and set layout
        setPreferredSize (new Dimension (296, 210));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (clientButton);
        add (productsButton);
        add (orderButton);

        //set component bounds (only needed by Absolute Positioning)
        clientButton.setBounds (45, 20, 215, 45);
        clientButton.setBackground(Color.gray);
        clientButton.setForeground(Color.black);
        productsButton.setBounds (45, 80, 215, 45);
        productsButton.setBackground(Color.gray);
        productsButton.setForeground(Color.black);
        orderButton.setBounds (45, 140, 215, 45);
        orderButton.setBackground(Color.gray);
        orderButton.setForeground(Color.black);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Warehouse Application");
        frame.pack();
        frame.setVisible (true);
    }

    public void addActionListeners(ActionListener actionListener){
        clientButton.addActionListener(actionListener);
        productsButton.addActionListener(actionListener);
        orderButton.addActionListener(actionListener);
    }

    public void close(){
        frame.setVisible(false);
    }

    public JButton getClientButton() {
        return clientButton;
    }

    public JButton getProductsButton() {
        return productsButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }
}