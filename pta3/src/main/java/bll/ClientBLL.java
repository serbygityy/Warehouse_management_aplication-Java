package bll;

import dao.ClientDAO;
import model.Client;
import presentation.AllView;
import presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * <p>ClientBLL contains an instance variable of a Controller object and a ClientDAO one. Having access to these 2,
 * it fetches the inputs from the controller and sends them further to the Data Access Object</p>
 */
public class ClientBLL {

    private ClientDAO clientDAO;
    private Controller controller;

    /**
     * <p>It fetches the inputs from the ClientBoxView instance and creates a Client Object.
     * The latter is used in the ClientDAO insert method.</p>
     */
    public class AddClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getClientBox().getInput();
            Client client = new Client(Integer.parseInt(inputs.get(0)),inputs.get(1),inputs.get(2),Integer.parseInt(inputs.get(3)));
            clientDAO.insert(client);
        }
    }

    /**
     * <p>It retrieves the inputs from the ClientBoxView object and instantiates a client with those inputs.
     * The Client object is then used in the update method of the ClientDAO object</p>
     */
    public class UpdateClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getClientBox().getInput();
            Client client = new Client(Integer.parseInt(inputs.get(0)),inputs.get(1),inputs.get(2),Integer.parseInt(inputs.get(3)));
            clientDAO.update(client);
        }
    }

    /**
     * <p>It retrieves the first input from the ClientBoxView object, which is actually the id of the client.
     * Tis id is then used in the remove method of the ClientDAO object</p>
     */
    public class RemoveClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getClientBox().getInput();
            clientDAO.remove(Integer.parseInt(inputs.get(0)));
        }
    }

    /**
     * <p>It creates a new instance of AllView and it calls upon its constructor, having as parameter a JTable returned
     * from the getTable method from ClientDAO</p>
     */
    public class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                AllView allView = new AllView(clientDAO.getTable());
            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ClientBLL(ClientDAO clientDAO, Controller controller) {
        this.clientDAO = clientDAO;
        this.controller = controller;
        controller.addActionListener('c', new AddClientListener(), new UpdateClientListener(), new RemoveClientListener());
        controller.addViewListener('c', new ViewListener());
    }

}