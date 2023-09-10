package wkog.controller;

import wkog.entities.UserAccount;
import wkog.model.UserAccountDAO;
import wkog.view.LoginView;

import java.io.File;

public class LoginController {
    private static final UserAccountDAO userAccountDAO = new UserAccountDAO();

    public void Login(){
        long lengthData = 0;
        try {
            File file = new File("src/main/java/wkog/file/user.ser");
            lengthData = file.length();
            System.out.println(file.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (lengthData != 0 && userAccountDAO.readUserToFile() != null) {
            String username = userAccountDAO.readUserToFile().getUsername();
            String password = userAccountDAO.readUserToFile().getPassword();
            UserAccount userAccount = new UserAccount(username, password);
            String result = userAccountDAO.checkUserLogin(userAccount);
            new LoginView().resultLogin(result, userAccount);
        } else {
            new LoginView().showLoginview();
        }
    }
}
