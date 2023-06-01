package bll;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.*;
import presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * <p>OrderBLL contains an instance variable of a Controller object, a ClientDAO, a ProductDAO and an OrderDAO. Having access to these 4,
 * it fetches the inputs from the controller and sends them further to the Data Access Objects</p>
 */
public class OrderBLL {
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private Controller control;

    /**
     * <p>It firstly gets the inputs from the GUI window and places them in an ArrayList.Then a Product object and a Client
     * one are instantiated, being used the findById methods from ProductDAO and ClientDAO. After that, it is checked if the
     * quantity desired is in stock (if not, a message dialog will show up, warning the user). If it is correct, the quantity
     * of the product is decremented and updated using the update method from ProductDAO. An Order object is instantiated using the
     * fields of the Client and Product objects and is inserted using the insert method from OrderDAO. Finally, it creates
     * the bill for the corresponding order</p>
     */
    public class CreateOrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = control.getOrderView().getInput();
            Product product = productDAO.findById(Integer.parseInt(inputs.get(0)));
            Client client = clientDAO.findById(Integer.parseInt(inputs.get(1)));

            if(product.getQuantity() < Integer.parseInt(inputs.get(2))){
                control.printMessage("Insufficient quantity. Try again");
                return;
            }

            product.setQuantity(product.getQuantity() - Integer.parseInt(inputs.get(2)));
            productDAO.update(product);
            DecimalFormat df = new DecimalFormat("0.00");

            Order order = new Order();
            order.setQuantity(Integer.parseInt(inputs.get(2)));
            order.setClientId(client.getId());
            order.setProductId(product.getId());
            order.setPrice(product.getPrice());
            double total = product.getPrice() * order.getQuantity();
            String totalStr = df.format(total);
            order.setTotal(Double.parseDouble(totalStr));
            orderDAO.insert(order);

            try {
                createBill(order, client, product);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public OrderBLL(OrderDAO orderDAO, ClientDAO clientDAO, ProductDAO productDAO, Controller control) {
        this.orderDAO = orderDAO;
        this.clientDAO = clientDAO;
        this.productDAO = productDAO;
        this.control = control;
        control.addOrderListener(new CreateOrderListener());
    }

    /**
     * It creates the bill as a text file, using a Random object to generate a random number identifier for the bill,
     * a File and BufferedWriter objects to actually create the bill. In the BufferedWriter it is added the date of the
     * completion of the order, the name of the client, the name of the product, the quantity bought, the price per unit
     * of the product and the total that was paid.
     * @param order the Order object that represents said bill
     * @param client the Client object that corresponds to the buyer
     * @param product the Product object whose 'subset' was bought
     * @throws IOException in case of an exception using the File and BufferedWriter
     */
    public void createBill(Order order,Client client, Product product) throws IOException {
        Random rand = new Random();
        int billId = rand.nextInt();
        String name = "bill" + billId + ".txt";
        File file = new File(name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        writer.write(df.format(now) + "\n");
        writer.write("Client's name: "+ client.getName() + "\n");
        writer.write("Product bought: " + product.getName() + "\n");
        writer.write("Quantity bought: " + order.getQuantity() + "\n");
        writer.write("Price per piece: " + product.getPrice() + "\n");
        writer.write("Total paid: " + order.getTotal());
        writer.flush();
        writer.close();
    }

}