package wkog.controller;

import wkog.model.UserAccount;
import wkog.view.LoginView;

import java.io.File;

public class LoginController {
    private static final UserAccountController userAccountController = new UserAccountController();

    public void Login(){
        long lengthData = 0;
        try {
            File file = new File("src/main/java/wkog/file/user.ser");
            lengthData = file.length();
//            System.out.println(file.length());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (lengthData != 0 && userAccountController.readUserToFile() != null) {
            String username = userAccountController.readUserToFile().getUsername();
            String password = userAccountController.readUserToFile().getPassword();
            UserAccount userAccount = new UserAccount(username, password);
            String result = userAccountController.checkUserLogin(userAccount);
            new LoginView().resultLogin(result, userAccount);
        } else {
            new LoginView().showLoginview();
        }
    }
}
