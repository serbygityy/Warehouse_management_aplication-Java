package main;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import presentation.*;

/**
 * <p>Main starts the whole program, instantiating the needed objects for the GUI and business logic objects</p>
 */
public class Main {
    public static void main(String[] args){
        StartView start = new StartView();
        ClientView client = new ClientView();
        OrderView order = new OrderView();
        ProductView product = new ProductView();
        ClientBoxView cBox = new ClientBoxView();
        ProductBoxView pBox = new ProductBoxView();
        Controller control = new Controller(start, product, order, client, cBox, pBox);
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        ClientBLL cb = new ClientBLL(clientDAO, control);
        ProductBLL pb = new ProductBLL(productDAO, control);
        OrderBLL ob = new OrderBLL(orderDAO, clientDAO, productDAO, control);
    }
}