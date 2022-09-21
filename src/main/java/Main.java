import ua.com.foxminded.menu.ApplicationMenu;
import ua.com.foxminded.service.Init;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Init init = new Init();
        init.initializeApplicationData();
        ApplicationMenu menu = new ApplicationMenu();
        menu.callMenu();
    }
}
