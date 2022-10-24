package ua.com.foxminded.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ua.com.foxminded.service.ApplicationMenu;
import ua.com.foxminded.service.DataInitializer;

@SpringBootApplication
@ComponentScan(basePackages = "ua.com.foxminded")
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
