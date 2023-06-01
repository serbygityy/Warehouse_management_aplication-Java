package presentation;

import dao.OrderDAO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>OrderView represents the GUI interface that is used to create an order, having the user input the id of the product, the client and the quantity desired</p>
 */
public class OrderView extends JPanel {
    private JComboBox<String> prodField;
    private JLabel instrLabel;
    private JComboBox<String> clientField;
    private JTextField quantField;
    private JLabel clientLabel;
    private JLabel quantLabel;
    private JLabel prodLabel;
    private JButton orderButton;
    private JFrame frame = new JFrame ("Create an order!");

    public OrderView() {
        //construct preComponents

        //construct components
        prodField = new JComboBox<>(OrderDAO.getProductID());
        instrLabel = new JLabel ("Create an order below!");
        clientField = new JComboBox<>(OrderDAO.getClientsID());
        quantField = new JTextField (5);
        clientLabel = new JLabel ("Choose the ID of the client:");
        quantLabel = new JLabel ("Write the quantity:");
        prodLabel = new JLabel ("Choose the ID of the product:");
        orderButton = new JButton ("Place Order");

        //adjust size and set layout
        setPreferredSize (new Dimension (410, 245));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (prodField);
        add (instrLabel);
        add (clientField);
        add (quantField);
        add (clientLabel);
        add (quantLabel);
        add (prodLabel);
        add (orderButton);

        prodField.setBounds (195, 60, 185, 25);
        instrLabel.setBounds (5, 20, 295, 25);
        instrLabel.setForeground(Color.white);
        clientField.setBounds (195, 100, 185, 25);
        quantField.setBounds (195, 140, 185, 25);
        clientLabel.setBounds (5, 100, 170, 25);
        clientLabel.setForeground(Color.white);
        quantLabel.setBounds (5, 140, 170, 25);
        quantLabel.setForeground(Color.white);
        prodLabel.setBounds (5, 60, 175, 25);
        prodLabel.setForeground(Color.white);
        orderButton.setBounds (90, 185, 210, 45);
        orderButton.setBackground(Color.gray);
        orderButton.setForeground(Color.black);
    }


    public void display(){
        frame.setBackground(Color.darkGray);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible (true);
    }

    /**
     * <p>This method retrieves the inputs from the JTextFields and return them</p>
     * @return an ArrayList of String objects containing the inputs
     */
    public ArrayList<String> getInput(){
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(prodField.getSelectedItem().toString());
        inputs.add(clientField.getSelectedItem().toString());
        inputs.add(quantField.getText());
        return inputs;
    }

    public void addActionListeners(ActionListener actionListener){
        orderButton.addActionListener(actionListener);
    }
}