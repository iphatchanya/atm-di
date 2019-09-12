package connectDB;

import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Data

public class DataSource {

    private String filename;

    /**
     * @param filename the name of the customer file
     */
    public DataSource(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the customer numbers and pins
     * and initializes the bank accounts.
     */
    public Map<Integer, Customer> readCustomers() throws ClassNotFoundException, SQLException {
        Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:" + filename;
        Connection connection = DriverManager.getConnection(dbURL);

        if(connection != null) {
            String query = "select * from atmTable";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int number = resultSet.getInt(1);
                int pin = resultSet.getInt(2);
                double currentBalance = resultSet.getDouble(3);
                Customer c = new Customer(number, pin, currentBalance);
                customers.put(c.getCustomerNumber(), c);
            }

        }
        connection.close();
        return customers;
    }
}