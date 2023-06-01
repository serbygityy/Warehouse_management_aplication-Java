package presentation;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * <p>ProductBoxView is used to get the information for the products that are to be added, removed or updated.</p>
 */
public class ProductBoxView extends JPanel {
    private JButton button;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JLabel instrLabel;
    private JButton updateButton;
    private JButton removeButton;

    public ProductBoxView() {
        //construct components
        idField = new JTextField (5);
        idLabel = new JLabel ("Product's ID:");
        nameLabel = new JLabel ("Product's Name:");
        nameField = new JTextField (5);
        quantityLabel = new JLabel ("Product quantity:");
        quantityField = new JTextField (5);
        priceLabel = new JLabel ("Product's price:");
        priceField = new JTextField (5);
        instrLabel = new JLabel ("For removal, input only the id");
        button = new JButton ("Add Product");
        updateButton = new JButton("Update Product");
        removeButton = new JButton("Remove Product");

        //adjust size and set layout
        setPreferredSize (new Dimension (650, 215));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (idField);
        add (idLabel);
        add (nameLabel);
        add (nameField);
        add (quantityField);
        add (quantityLabel);
        add (priceField);
        add (priceLabel);
        add (instrLabel);
        add (button);
        add(updateButton);
        add(removeButton);

        //set component bounds (only needed by Absolute Positioning)
        idField.setBounds (140, 5, 125, 30);
        idLabel.setBounds (20, 5, 115, 30);
        idLabel.setForeground(Color.WHITE);
        nameLabel.setBounds (20, 40, 115, 30);
        nameLabel.setForeground(Color.WHITE);
        nameField.setBounds (140, 40, 200, 30);
        quantityLabel.setBounds (20, 75, 115, 30);
        quantityLabel.setForeground(Color.WHITE);
        quantityField.setBounds (140, 75, 200, 30);
        priceLabel.setBounds (20, 110, 115, 30);
        priceLabel.setForeground(Color.WHITE);
        priceField.setBounds (140, 110, 100, 30);
        instrLabel.setBounds (280, 5, 340, 30);
        instrLabel.setForeground(Color.WHITE);
        button.setBounds (250, 160, 185, 45);
        button.setBackground(Color.gray);
        button.setForeground(Color.black);
        updateButton.setBounds (60, 160, 185, 45);
        updateButton.setBackground(Color.gray);
        updateButton.setForeground(Color.black);
        removeButton.setBounds (440, 160, 185, 45);
        removeButton.setBackground(Color.gray);
        removeButton.setForeground(Color.black);
    }

    public void display(){
        JFrame frame = new JFrame ("Products");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }

    /**
     * <p>Used to get the data from the window to the controller</p>
     * @return returns the inputs from the user in an ArrayList containing String objects.
     */
    public ArrayList<String> getInput(){
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(idField.getText());
        inputs.add(nameField.getText());
        inputs.add(priceField.getText());
        inputs.add(quantityField.getText());
        return inputs;
    }

    public void addActionListener(ActionListener al1, ActionListener al2, ActionListener al3){
        button.addActionListener(al1);
        updateButton.addActionListener(al2);
        removeButton.addActionListener(al3);
    }
}