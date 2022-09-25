import ua.com.foxminded.service.ApplicationMenu;
import ua.com.foxminded.service.Init;
import ua.com.foxminded.service.InvalidUserInputException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidUserInputException {
        Init init = new Init();
        ApplicationMenu applicationMenu = new ApplicationMenu();

        init.initializeApplicationData();
        applicationMenu.callConsoleMenu();
    }
}
