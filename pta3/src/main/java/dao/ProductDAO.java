package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * <p>ProductDAO extends AbstractDAO with the type parameter Product, having the inherited methods to access the database.</p>
 */
public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO() {
        this.identifier = 2;
    }

    /**
     * <p>It creates the insert statement based on the declared fields of the Product object</p>
     * @param product the product that is to be inserted in the table
     * @return the query created
     */
    @Override
    protected String createInsertStatement(Product product) {
        String sb = "INSERT INTO schooldb.Product  VALUES (";
        sb += product.getId() + ", '"  + product.getName() + "', " + product.getPrice() + ", " + product.getQuantity() + ");";
        return sb;
    }

    /**
     * <p>It creates the update query based on the declared fields of the Product object</p>
     * @param product the product that is to be updated in the table
     * @return the query created
     */
    @Override
    protected String createUpdateQuery(Product product) {
        String sb = "UPDATE schooldb.Product SET name='" + product.getName() + "', price=" + product.getPrice()
                + ", quantity=" + product.getQuantity() + " WHERE id=" + product.getId();
        return sb;
    }

}