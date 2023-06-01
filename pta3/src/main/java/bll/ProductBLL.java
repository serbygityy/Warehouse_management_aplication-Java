package bll;

import dao.ProductDAO;
import model.Product;
import presentation.AllView;
import presentation.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * <p>ProductBLL contains an instance variable of a Controller object and a ProductDAO one. Having access to these 2,
 * it fetches the inputs from the controller and sends them further to the Data Access Object</p>
 */
public class ProductBLL {
    private ProductDAO productDAO;
    private Controller controller;

    /**
     * <p>It fetches the inputs from the ProductBoxView instance and creates a Product Object.
     * The latter is used in the ProductDAO insert method.</p>
     */
    public class AddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getProductBox().getInput();
            Product product = new Product(Integer.parseInt(inputs.get(0)),inputs.get(1), Double.parseDouble(inputs.get(2)), Integer.parseInt(inputs.get(3)));
            productDAO.insert(product);
        }
    }

    /**
     * <p>It retrieves the inputs from the ProductBoxView object and instantiates a client with those inputs.
     * The Product object is then used in the update method of the ProductDAO object</p>
     */
    public class UpdateProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getProductBox().getInput();
            Product product = new Product(Integer.parseInt(inputs.get(0)),inputs.get(1), Double.parseDouble(inputs.get(2)), Integer.parseInt(inputs.get(3)));
            productDAO.update(product);
        }
    }

    /**
     * <p>It retrieves the first input from the ProductBoxView object, which is actually the id of the product.
     * Tis id is then used in the remove method of the ProductDAO object</p>
     */
    public class RemoveProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = controller.getProductBox().getInput();
            productDAO.remove(Integer.parseInt(inputs.get(0)));
        }
    }

    /**
     * <p>It creates a new instance of AllView and it calls upon its constructor, having as parameter a JTable returned
     * from the getTable method from ProductDAO</p>
     */
    public class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                AllView allView = new AllView(productDAO.getTable());
            } catch (IllegalAccessException | IntrospectionException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ProductBLL(ProductDAO productDAO, Controller controller) {
        this.productDAO = productDAO;
        this.controller = controller;
        controller.addActionListener('p', new ProductBLL.AddProductListener(), new ProductBLL.UpdateProductListener(), new ProductBLL.RemoveProductListener());
        controller.addViewListener('p', new ViewListener());
    }
}