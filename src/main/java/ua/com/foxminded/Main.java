package ua.com.foxminded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.foxminded.util.ApplicationMenu;
import ua.com.foxminded.util.DataInitializer;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    DataInitializer dataInitializer;

    @Autowired
    ApplicationMenu applicationMenu;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
   }

    public void run(String[] args){
        dataInitializer.initializeApplicationData();
        applicationMenu.callConsoleMenu();
   }
}
