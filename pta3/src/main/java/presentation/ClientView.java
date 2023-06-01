package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * <p>ClientView is the GUI Window set for configuring the clients in the database, as having buttons for adding, removing, updating or viewing all clients</p>
 */
public class ClientView extends JPanel {
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton backButton;
    private JFrame frame = new JFrame ("Clients");

    public ClientView() {
        //construct components
        addButton = new JButton ("Add Client");
        editButton = new JButton ("Edit Client");
        deleteButton = new JButton ("Delete Client");
        viewButton = new JButton ("View All Clients");
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

    /**
     * <p>Adds the ActionListeners for the Add/Update/Remove/Back buttons</p>
     * @param actionListener the ActionListener that opens the ClientBoxView instance
     * @param backListener the ActionListener that opens the StartView instance
     */
    public void addActionListeners(ActionListener actionListener, ActionListener backListener){
        addButton.addActionListener(actionListener);
        editButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        backButton.addActionListener(backListener);
    }

    public void close(){
        frame.setVisible(false);
    }

    /**
     * <p>Adds an action listener to the button that will show all clients from the database.</p>
     * @param a the ActionListener to be added
     */
    public void addViewListener(ActionListener a){

        viewButton.addActionListener(a);
    }
}