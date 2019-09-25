package connectJDBC;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * A bank contains customers with bank accounts.
 */
@NoArgsConstructor
@Data

public class Bank {

   private List<Customer> customers;
   private DatabaseConnector dataSource;

   /**
    * Constructs a bank with no customers.
    */
   public Bank(Connectable dataSource) {
      this.dataSource = (DatabaseConnector) dataSource;
   }

   public void initializeCustomers() {
      customers = dataSource.readCustomers();
//      for (Customer customer : customers)
//         System.out.println(customer.toString());
   }
   /**
    * Adds a customer to the bank.
    * @param c the customer to add
    */
//   public void addCustomer(Customer c) {
//      customers.put(c.getCustomerNumber(), c);
//   }

   /**
    * Finds a customer in the bank.
    * @param number a customer number
    * @return the matching customer, or null if no customer
    * matches
    */
   public Customer findCustomer(int number) {
      return customers.get(number - 1);
   }
}
