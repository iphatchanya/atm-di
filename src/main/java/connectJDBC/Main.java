package connectJDBC;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("config-jdbc.xml");
        ATMSimulator atmSimulator = context.getBean("Simulator", ATMSimulator.class);

        atmSimulator.run();

    }
}