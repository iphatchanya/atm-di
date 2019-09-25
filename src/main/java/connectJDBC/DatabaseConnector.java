package connectJDBC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;


import java.sql.*;
import java.util.List;

public class DatabaseConnector implements Connectable {
    private JdbcTemplate jdbcTemplate;

    public DatabaseConnector(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> readCustomers() {
        List<Customer> customers;
        String query = "SELECT * FROM CUSTOMERS";
        customers = jdbcTemplate.query(query, new CustomerRowMapper());
        return customers;
    }

    public void update(Customer customer) {
        String query = "UPDATE customers SET currentBalance = " + customer.getAccount().getBalance() + " WHERE number = " + customer.getCustomerNumber();
        jdbcTemplate.update(query);

    }


    class CustomerRowMapper implements RowMapper<Customer> {
        @Nullable
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3));
            return customer;
        }
    }
}
