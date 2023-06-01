package presentation;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>The controller manages the GUI windows and retrieves/gives the necessary data from the GUI and to the business logic objects. This has as instance variables objects of each type of GUI</p>
 */
public class Controller {

    private final StartView startView;
    private final ClientView clientView;
    private final ProductView productView;
    private final OrderView orderView;
    private final ClientBoxView clientBox;
    private final ProductBoxView productBox;

    /**
     * <p>This class resolves the switch from one GUI object to another, based on the source of the ActionEvent</p>
     */
    class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(startView.getClientButton())){
                startView.close();
                clientView.display();
            }else if(e.getSource().equals(startView.getProductsButton())){
                startView.close();
                productView.display();
            }else{
                startView.close();
                orderView.display();
            }
        }
    }

    /**
     * This class acts as the function of the "Go back" button
     */
    class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(clientView.getBackButton())) {
                clientView.close();
                startView.display();
            }else{
                productView.close();
                startView.display();
            }
        }
    }

    /**
     * <p>This opens the GUI interfaces that require the inputs for the products/clients</p>
     */
    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (clientView.getAddButton().equals(source) || clientView.getEditButton().equals(source)) {
                clientBox.display();
            } else if (clientView.getDeleteButton().equals(source)) {
                clientBox.display();
            } else if (clientView.getViewButton().equals(source)) {
                System.out.println("view A");
            } else if (productView.getAddButton().equals(source)) {
                productBox.display();
            } else if (productView.getEditButton().equals(source)) {
                productBox.display();
            } else if (productView.getDeleteButton().equals(source)) {
                productBox.display();
            } else {
                System.out.println("view P");
            }
        }
    }

    public Controller(StartView start, ProductView product, OrderView order, ClientView client, ClientBoxView cBox, ProductBoxView pBox){
        startView = start;
        productView = product;
        orderView = order;
        clientView = client;
        clientBox = cBox;
        productBox = pBox;
        startView.addActionListeners(new Listener());
        clientView.addActionListeners(new ButtonListener(), new BackListener());
        productView.addActionListeners(new ButtonListener(), new BackListener());
        startView.display();
    }

    public ClientView getClientView() {
        return clientView;
    }

    public ProductView getProductView() {
        return productView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public ClientBoxView getClientBox() {
        return clientBox;
    }

    public ProductBoxView getProductBox() {
        return productBox;
    }

    public void addActionListener(char c, ActionListener al1, ActionListener al2, ActionListener al3){
        if(c == 'c'){
            clientBox.addActionListener(al1, al2, al3);
        }else{
            productBox.addActionListener(al1, al2, al3);
        }
    }

    public void addViewListener(char c, ActionListener a){
        if(c == 'c'){
            clientView.addViewListener(a);
        }else{
            productView.addViewListener(a);
        }
    }

    public void addOrderListener(ActionListener al){
        orderView.addActionListeners(al);
    }

    public void printMessage(String message){
        JOptionPane.showMessageDialog(orderView,message);
    }
}