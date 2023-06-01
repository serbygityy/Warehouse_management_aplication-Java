package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * <p>OrderDAO extends AbstractDAO with the parameter Order</p>
 */
public class OrderDAO extends AbstractDAO<Order>{
    /**
     * <p>It creates the insert statement based on the declared fields of the Order object</p>
     * @param order the order that is to be inserted in the table
     * @return the query created
     */
    @Override
    protected String createInsertStatement(Order order) {
        String sb = "INSERT INTO schooldb.orderr (clientId, productId, price, quantity, total) VALUES (";
        sb += order.getClientId() + ", " + order.getProductId() + ", " + order.getPrice() + ", "
                + order.getQuantity() + ", " + order.getTotal() + ");";
        return sb;
    }

    /**
     * <p>It creates the update query based on the declared fields of the Order object</p>
     * @param order the order that is to be updated in the table
     * @return the query created
     */
    @Override
    protected String createUpdateQuery(Order order) {
        return null;
    }

    /**
     * <p>It retrieves all the ids from the Client table and turns them from ResultSet to String objects</p>
     * @return the array of ids collected from the database
     */
    public static String[] getClientsID(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id from Client";
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return turnIntoList(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING , "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *  <p>It retrieves all the ids from the Product table and turns them from ResultSet to String objects</p>
     * @return the array of ids collected from the database
     */
    public static String[] getProductID(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id from Product";
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return turnIntoList(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING , "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>It turns an object of ResultSet type to an array of String objects, using the getString() method</p>
     * @param rs the result set created from the execution of queries
     * @return the array of String objects
     * @throws SQLException due to the use of ResultSet
     */
    public static String[] turnIntoList(ResultSet rs) throws SQLException {
        int size = 0;
        ArrayList<String> out = new ArrayList<>();
        while(rs.next()){
            out.add(rs.getString(1));
            size++;
        }
        String[] arr = new String[size];
        return out.toArray(arr);
    }
}