import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.service.ApplicationMenu;
import ua.com.foxminded.service.DataInitializer;
import java.io.IOException;
import java.util.concurrent.CompletionException;

public class Main {
    public static void main(String[] args) throws IOException, DAOException, CompletionException {
        DataInitializer dataInitializer = new DataInitializer();
        ApplicationMenu applicationMenu = new ApplicationMenu();

        dataInitializer.initializeApplicationData();
        applicationMenu.callConsoleMenu();
    }
}
