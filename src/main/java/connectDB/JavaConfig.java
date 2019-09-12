package connectDB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public DataSource dataSource() {
        return new DataSource("atm.db");
    }

    @Bean
    public Bank bank() {
        return new Bank(dataSource());
    }

    @Bean
    public ATM atm() {
        return new ATM(bank());
    }

    @Bean
    public ATMSimulator atmSimulator() {
        return new ATMSimulator(atm());
    }
}
