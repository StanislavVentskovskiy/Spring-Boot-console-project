import ua.com.foxminded.service.ApplicationMenu;
import ua.com.foxminded.service.DataInitializer;

public class Main {
    public static void main(String[] args)  {
        DataInitializer dataInitializer = new DataInitializer();
        ApplicationMenu applicationMenu = new ApplicationMenu();
        dataInitializer.initializeApplicationData();
        applicationMenu.callConsoleMenu();
   }
}
