package dao;

import model.Client;

/**
 * <p>ClientDAO extends AbstractDAO with the type parameter Client, having the inherited methods to access the database.</p>
 */
public class ClientDAO extends AbstractDAO<Client>{

    public ClientDAO() {
        this.identifier = 1;
    }

    /**
     * <p>It creates the insert statement based on the declared fields of the Client object</p>
     * @param client the client that is to be inserted in the table
     * @return the query created
     */
    protected String createInsertStatement(Client client){
        String sb = "INSERT INTO schooldb.client " +
                " VALUES (" +
                client.getId() + ",'" +
                client.getName() + "', '" +
                client.getAddress() + "', " +
                client.getAge() +
                ");";
        return sb;
    }

    /**
     * <p>It creates the update query based on the declared fields of the Client object</p>
     * @param client the client that is to be updated in the table
     * @return the query created
     */
    @Override
    protected String createUpdateQuery(Client client) {
        String sb = "UPDATE schooldb.client SET name='" + client.getName() + "', address='" + client.getAddress()
                + "', age=" + client.getAge() + " WHERE id=" + client.getId();
        return sb;
    }

}