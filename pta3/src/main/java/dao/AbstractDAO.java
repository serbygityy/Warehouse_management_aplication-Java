package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import javax.swing.*;

/**
 * <p>AbstractDAO represents a generic form of the Data Access Objects and implements the methods that retrieve/modify/add to the database</p>
 * @param <T> the class type based on the object that is to be extracted/modified into the database
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    protected int identifier;
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *<p>It creates a select query based on a field and returns it.</p>
     * @param field the field that is being tested inside the query.
     * @return the query that is to be executed on the database
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }

    /**
     * <p>It creates a select query which retrieves every instance found in the table and uses createObjects() to turn the resultSet into objects</p>
     * @return a list of objects of type T
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName() + ";";
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>It creates a query based on the createSelectQuery() with the field "id" and sets the unknown parameter ? into the integer id and then creates an object of type T</p>
     * @param id the id that is to be inserted into the query
     * @return an object of type T with the id equal to the parameter
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>It creates an insert statement based on the parameter and executes it, inserting an element into the database</p>
     * @param t the object that is to be inserted into the table
     * @return the object or a null element
     */
    public T insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertStatement(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Based on the result set, it creates objects of type T using reflection, by calling constructors, PropertyDescriptors and Methods. It uses the instance variable identifier to create a specific kind of Instance</p>
     * @param resultSet the result set created from the execution or select/insert queries.
     * @return the list of newly created objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = null;
                if(identifier == 1) {
                    instance = (T) ctor.newInstance(0, null, null, 0);
                }
                if (identifier == 2){
                    instance = (T) ctor.newInstance(null, 0.0, 0);
                }

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IntrospectionException | SQLException | InvocationTargetException | IllegalArgumentException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * <p>It creates an update query based on the parameter and executes it to update the corresponding entry in the table</p>
     * @param t the object that has new values except for the id, which is used to determine which entry of the table is going to get changed
     */
    public void update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(t);
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * <p>It creates a statement for removal based on the id of the object and executes it</p>
     * @param id the id of the object that is to be deleted
     */
    public void remove(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:remove " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * <p>It creates the insert statement that is used in the insert method, which is abstract due to the difference in the string building of each object</p>
     * @param t the object for which the statement will be created
     * @return the corresponding query
     */
    protected abstract String createInsertStatement(T t);

    /**
     *  <p>It creates the update statement that is used in the update method, which is abstract due to the difference in the string building of each object</p>
     * @param t the object that is going to be updated
     * @return the query
     */
    protected abstract String createUpdateQuery(T t);

    /**
     * <p>It calls on the findAll() method to retrieve all the elements from the table, then using reflection the column array is created with the declared fields of the object; then the data is being constructed by using reflection to invoke the getters of each field of the object</p>
     * @return a newly created JTable based on the column array and data matrix
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    public JTable getTable() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        List<T> results = findAll();
        String[] column = new String[4];
        int i = 0;
        for(Field field : results.get(0).getClass().getDeclaredFields()){
            String f = field.toGenericString();
            int indexToDelete = f.lastIndexOf('.');
            column[i] = f.substring(indexToDelete + 1);
            i++;
        }
        String[][] data = new String[results.size()][4];
        i = 0;
        for(T t : results){

            Field[] fields = type.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[j].getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = null;
                value = method.invoke(t);
                data[i][j] = value.toString();
                System.out.println(data[i][j]);
            }
            i++;
        }
        return new JTable(data, column);
    }
}