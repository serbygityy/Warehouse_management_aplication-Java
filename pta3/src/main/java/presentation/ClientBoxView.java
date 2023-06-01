package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>ClientBoxView is used to get the information for the clients that are to be added, removed or updated.</p>
 */
public class ClientBoxView extends JPanel {
    private JTextField idField;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel instrLabel;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JFrame frame = new JFrame ("Add/Update Clients");

    public ClientBoxView() {
        //construct components
        idField = new JTextField (5);
        idLabel = new JLabel ("Client's ID:");
        nameLabel = new JLabel ("Client's Name:");
        nameField = new JTextField (5);
        addressLabel = new JLabel ("Client's Address:");
        addressField = new JTextField (5);
        ageLabel = new JLabel ("Client's Age:");
        ageField = new JTextField (5);
        instrLabel = new JLabel ("For removal, input only the id");
        addButton = new JButton ("Add Client");
        updateButton = new JButton("Update Client");
        removeButton = new JButton("Remove Client");

        //adjust size and set layout
        setPreferredSize (new Dimension (650, 215));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (idField);
        add (idLabel);
        add (nameLabel);
        add (nameField);
        add (addressLabel);
        add (addressField);
        add (ageLabel);
        add (ageField);
        add (instrLabel);
        add (addButton);
        add(updateButton);
        add(removeButton);

        //set component bounds (only needed by Absolute Positioning)
        idField.setBounds (140, 5, 125, 30);
        idLabel.setBounds (20, 5, 115, 30);
        idLabel.setForeground(Color.WHITE);
        nameLabel.setBounds (20, 40, 115, 30);
        nameLabel.setForeground(Color.WHITE);
        nameField.setBounds (140, 40, 200, 30);
        addressLabel.setBounds (20, 75, 115, 30);
        addressLabel.setForeground(Color.WHITE);
        addressField.setBounds (140, 75, 200, 30);
        ageLabel.setBounds (20, 110, 115, 30);
        ageLabel.setForeground(Color.WHITE);
        ageField.setBounds (140, 110, 100, 30);
        instrLabel.setBounds (280, 5, 325, 30);
        instrLabel.setForeground(Color.WHITE);
        addButton.setBounds (250, 160, 185, 45);
        addButton.setBackground(Color.gray);
        addButton.setForeground(Color.black);
        updateButton.setBounds (60, 160, 185, 45);
        updateButton.setBackground(Color.gray);
        updateButton.setForeground(Color.black);
        removeButton.setBounds (440, 160, 185, 45);
        removeButton.setBackground(Color.gray);
        removeButton.setForeground(Color.black);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }

    public void close(){
        frame.setVisible(false);
    }

    /**
     * <p>Used to get the data from the window to the controller</p>
     * @return returns the inputs from the user in an ArrayList containing String objects.
     */
    public ArrayList<String> getInput(){
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(idField.getText());
        inputs.add(nameField.getText());
        inputs.add(addressField.getText());
        inputs.add(ageField.getText());
        return inputs;
    }

    /**
     * <p>Adds the ActionListener objects set as parameters to the JButtons of the interface</p>
     * @param al1 the ActionListener for the "Add Client" Button
     * @param al2 the ActionListener for the "Update Client" Button
     * @param al3 the ActionListener for the "Remove Client" Button
     */
    public void addActionListener(ActionListener al1, ActionListener al2, ActionListener al3){
        addButton.addActionListener(al1);
        updateButton.addActionListener(al2);
        removeButton.addActionListener(al3);
    }
}
