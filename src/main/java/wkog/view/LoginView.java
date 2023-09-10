package wkog.view;

import wkog.entities.UserAccount;
import wkog.font.FontService;
import wkog.model.UserAccountDAO;
import wkog.view.paint.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LoginView implements ActionListener {
    private static final int SCREEN_WIDTH_LOGINVIEW = 1100;
    private static final int SCREEN_HEIGHT_LOGINVIEW = 739;
    private static final FontService fontService = new FontService();
    private static final UserAccountDAO userAccountDAO = new UserAccountDAO();
    private static int numberOfConsecutiveIncorrectLoginAttempts = 0;
    private final JFrame frame;
    private final JTextField usernameInputFormUsernameCenterFormLogin;
    private final JPasswordField passwordInputFormUsernameCenterFormLogin;
    private final JButton buttonLogin;
    private final JButton buttonResetPassword;
    private final JCheckBox checkBoxRememberUser;
    private final JLabel titleUsernameIsEmptyCenterFormLogin;
    private final JLabel titlePasswordIsEmptyCenterFormLogin;

    public LoginView() {
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1, true);
        Border roundedBorder15 = new RoundedBorder(border, 15);
        Border roundedBorder10 = new RoundedBorder(border, 10);
        ImageIcon backgroundImageLogin = new ImageIcon("src/main/java/wkog/images/backgrounds/backgroundLogin.png");
        ImageIcon logoColorSizeMiniImageLogin = new ImageIcon("src/main/java/wkog/images/logos/logoColorSizeMini.png");
        ImageIcon usernameImageLogin = new ImageIcon("src/main/java/wkog/images/icons/username.png");
        ImageIcon passwordImageLogin = new ImageIcon("src/main/java/wkog/images/icons/password.png");
        ImageIcon checkIconImageRememberUser = new ImageIcon("src/main/java/wkog/images/icons/check.png");
        ImageIcon uncheckIconImageRememberUser = new ImageIcon("src/main/java/wkog/images/icons/uncheck.png");

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Student Management Application");
        frame.setIconImage(logoColorSizeMiniImageLogin.getImage());
        frame.setSize(SCREEN_WIDTH_LOGINVIEW, SCREEN_HEIGHT_LOGINVIEW);
        frame.setResizable(false);
        frame.setLayout(null);

        Image tempBackground = backgroundImageLogin.getImage().getScaledInstance(550, 700, Image.SCALE_SMOOTH);
        backgroundImageLogin = new ImageIcon(tempBackground);
        JLabel backgroundLoginForm = new JLabel();
        backgroundLoginForm.setBounds(0, 0, 550, 700);
        backgroundLoginForm.setIcon(backgroundImageLogin);

        JLabel firstTextLogin = new JLabel("Wukong University");
        firstTextLogin.setBounds((550 / 2 - (400 / 2)), (700 / 2 - 50 / 2) - 50, 400, 50);
        firstTextLogin.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        firstTextLogin.setForeground(Color.WHITE);
        firstTextLogin.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel secondTextLogin = new JLabel("Join the world's leading school!");
        secondTextLogin.setBounds((550 / 2 - (400 / 2)), (700 / 2 - 30 / 2) - 10, 400, 30);
        secondTextLogin.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        secondTextLogin.setForeground(Color.WHITE);
        secondTextLogin.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formLogin = new JPanel();
        formLogin.setBounds(550, 0, 550, 739);
        formLogin.setLayout(null);
        formLogin.setBackground(new Color(248, 248, 248));

        JPanel iconFrameHeaderFormLogin = new JPanel();
        iconFrameHeaderFormLogin.setBounds((550 / 2 - (90 / 2)), (700 / 2 - 90 / 2) - 230, 90, 90);
        iconFrameHeaderFormLogin.setBackground(Color.WHITE);
        iconFrameHeaderFormLogin.setLayout(null);
        iconFrameHeaderFormLogin.setBorder(roundedBorder15);

        Image tempIcon = logoColorSizeMiniImageLogin.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel iconHeaderFormLogin = new JLabel();
        iconHeaderFormLogin.setIcon(new ImageIcon(tempIcon));
        iconHeaderFormLogin.setBackground(null);
        iconHeaderFormLogin.setBounds(25, 25, 40, 40);

        JLabel titleHeaderFormLogin = new JLabel("Hello! Welcome back");
        titleHeaderFormLogin.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 18f));
        titleHeaderFormLogin.setBounds((550 / 2 - (400 / 2)), (700 / 2 - 30 / 2) - 150, 400, 30);
        titleHeaderFormLogin.setHorizontalAlignment(SwingConstants.CENTER);
        titleHeaderFormLogin.setForeground(new Color(120, 120, 120));

        JLabel titleUsernameCenterFormLogin = new JLabel("Username");
        titleUsernameCenterFormLogin.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 14f));
        titleUsernameCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 30 / 2) - 95, 360, 30);
        titleUsernameCenterFormLogin.setHorizontalAlignment(SwingConstants.LEFT);
        titleUsernameCenterFormLogin.setForeground(new Color(120, 120, 120));

        titleUsernameIsEmptyCenterFormLogin = new JLabel("Username field is empty!");
        titleUsernameIsEmptyCenterFormLogin.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 12f));
        titleUsernameIsEmptyCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 30 / 2) - 95, 360, 30);
        titleUsernameIsEmptyCenterFormLogin.setHorizontalAlignment(SwingConstants.RIGHT);
        titleUsernameIsEmptyCenterFormLogin.setForeground(Color.RED);
        titleUsernameIsEmptyCenterFormLogin.setVisible(false);


        JPanel formUsernameCenterFormLogin = new JPanel();
        formUsernameCenterFormLogin.setBackground(Color.WHITE);
        formUsernameCenterFormLogin.setBorder(roundedBorder10);
        formUsernameCenterFormLogin.setLayout(null);
        formUsernameCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 45 / 2) - 60, 360, 45);

        Image tempUsernameImageLogin = usernameImageLogin.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        usernameImageLogin = new ImageIcon(tempUsernameImageLogin);
        JLabel iconFormUsernameCenterFormLogin = new JLabel(usernameImageLogin);
        iconFormUsernameCenterFormLogin.setBackground(null);
        iconFormUsernameCenterFormLogin.setBounds((45 / 2 - 20 / 2), (45 / 2 - 20 / 2), 20, 20);

        usernameInputFormUsernameCenterFormLogin = new JTextField();
        usernameInputFormUsernameCenterFormLogin.setBackground(null);
        usernameInputFormUsernameCenterFormLogin.setBorder(null);
        usernameInputFormUsernameCenterFormLogin.setBounds((45 / 2 - 20 / 2) + 40, (45 / 2 - 20 / 2), 300, 20);


        JLabel titlePasswordCenterFormLogin = new JLabel("Password");
        titlePasswordCenterFormLogin.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 14f));
        titlePasswordCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 30 / 2) - 5, 360, 30);
        titlePasswordCenterFormLogin.setHorizontalAlignment(SwingConstants.LEFT);
        titlePasswordCenterFormLogin.setForeground(new Color(120, 120, 120));

        titlePasswordIsEmptyCenterFormLogin = new JLabel("Password field is empty!");
        titlePasswordIsEmptyCenterFormLogin.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 12f));
        titlePasswordIsEmptyCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 30 / 2) - 5, 360, 30);
        titlePasswordIsEmptyCenterFormLogin.setHorizontalAlignment(SwingConstants.RIGHT);
        titlePasswordIsEmptyCenterFormLogin.setForeground(Color.RED);
        titlePasswordIsEmptyCenterFormLogin.setVisible(false);

        JPanel formPasswordCenterFormLogin = new JPanel();
        formPasswordCenterFormLogin.setBackground(Color.WHITE);
        formPasswordCenterFormLogin.setBorder(roundedBorder10);
        formPasswordCenterFormLogin.setLayout(null);
        formPasswordCenterFormLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 45 / 2) + 30, 360, 45);

        Image tempPasswordImageLogin = passwordImageLogin.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        passwordImageLogin = new ImageIcon(tempPasswordImageLogin);
        JLabel iconFormPasswordCenterFormLogin = new JLabel(passwordImageLogin);
        iconFormPasswordCenterFormLogin.setBackground(null);
        iconFormPasswordCenterFormLogin.setBounds((45 / 2 - 20 / 2), (45 / 2 - 20 / 2), 20, 20);

        passwordInputFormUsernameCenterFormLogin = new JPasswordField();
        passwordInputFormUsernameCenterFormLogin.setBackground(null);
        passwordInputFormUsernameCenterFormLogin.setBorder(null);
        passwordInputFormUsernameCenterFormLogin.setBounds((45 / 2 - 20 / 2) + 40, (45 / 2 - 20 / 2), 300, 20);

        Image tempUncheckIconImageRememberUser = uncheckIconImageRememberUser.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image tempCheckIconImageRememberUser = checkIconImageRememberUser.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        uncheckIconImageRememberUser = new ImageIcon(tempUncheckIconImageRememberUser);
        checkIconImageRememberUser = new ImageIcon(tempCheckIconImageRememberUser);
        checkBoxRememberUser = new JCheckBox("Remember me");
        checkBoxRememberUser.setBackground(null);
        checkBoxRememberUser.setBorder(null);
        checkBoxRememberUser.setFont(fontService.montserrat().deriveFont(Font.PLAIN, 12f));
        checkBoxRememberUser.setForeground(new Color(120, 120, 120));
        checkBoxRememberUser.setFocusable(false);
        checkBoxRememberUser.setIcon(uncheckIconImageRememberUser);
        checkBoxRememberUser.setSelectedIcon(checkIconImageRememberUser);
        checkBoxRememberUser.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 30 / 2) + 80, 120, 30);

        buttonLogin = new JButton();
        buttonLogin.setText("Login");
        buttonLogin.setFocusable(false);
        buttonLogin.setBorder(roundedBorder10);
        buttonLogin.setFont(fontService.montserrat().deriveFont(Font.BOLD, 14f));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setBackground(new Color(99, 131, 250));
        buttonLogin.setBounds((550 / 2 - (360 / 2)), (700 / 2 - 45 / 2) + 135, 360, 45);
        buttonLogin.addActionListener(this::actionPerformed);

        buttonResetPassword = new JButton();
        buttonResetPassword.setBackground(null);
        buttonResetPassword.setText("Reset password!");
        buttonResetPassword.setForeground(new Color(99, 131, 250));
        buttonResetPassword.setFocusable(false);
        buttonResetPassword.setBorder(null);
        buttonResetPassword.setBorderPainted(false);
        buttonResetPassword.setOpaque(false);
        buttonResetPassword.setBounds((550 / 2 - (360 / 2)) + 260, (700 / 2 - 30 / 2) + 85, 100, 20);
        buttonResetPassword.addActionListener(this::actionPerformed);

        iconFrameHeaderFormLogin.add(iconHeaderFormLogin);

        formUsernameCenterFormLogin.add(iconFormUsernameCenterFormLogin);
        formUsernameCenterFormLogin.add(usernameInputFormUsernameCenterFormLogin);
        formPasswordCenterFormLogin.add(iconFormPasswordCenterFormLogin);
        formPasswordCenterFormLogin.add(passwordInputFormUsernameCenterFormLogin);

        formLogin.add(checkBoxRememberUser);
        formLogin.add(titleUsernameCenterFormLogin);
        formLogin.add(titleUsernameIsEmptyCenterFormLogin);
        formLogin.add(formUsernameCenterFormLogin);
        formLogin.add(titlePasswordCenterFormLogin);
        formLogin.add(titlePasswordIsEmptyCenterFormLogin);
        formLogin.add(formPasswordCenterFormLogin);
        formLogin.add(titleHeaderFormLogin);
        formLogin.add(iconFrameHeaderFormLogin);
        formLogin.add(buttonResetPassword);
        formLogin.add(buttonLogin);

        frame.add(formLogin);
        frame.add(firstTextLogin);
        frame.add(secondTextLogin);
        frame.add(backgroundLoginForm);
        frame.setLocationRelativeTo(null);
    }

    public void showLoginview() {
        frame.setVisible(true);
    }

    public void hideLoginView() {
        frame.setVisible(false);
    }


    public void blockSpamLogin() {
        if (numberOfConsecutiveIncorrectLoginAttempts >= 3) {
            Random random = new Random();
            JOptionPane.showMessageDialog(frame, "Đăng nhập quá nhanh, vui lòng thử lại sau ít phút nữa!!!", "Oops, error!", JOptionPane.ERROR_MESSAGE);
            buttonLogin.setEnabled(false);
            int delayTimer = random.nextInt(5000, 10000);
            Timer pauseTheLoginFunction = new Timer(delayTimer, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonLogin.setEnabled(true);
                    ((Timer) e.getSource()).stop();
                    numberOfConsecutiveIncorrectLoginAttempts = 0;
                }
            });
            pauseTheLoginFunction.start();
        }
    }

    public void resultLogin(String resultMessenge, UserAccount userAccount) {
        if (resultMessenge.equals("success")) {
            if (checkBoxRememberUser.isSelected()) {
                userAccountDAO.writeUserToFile(userAccount);
            }
            frame.setVisible(false);
            switch (userAccount.getLevelAccount()) {
                case 1:
                    new AdminView();
                    break;
                case 3:
                    new TeacherView();
                    break;
                case 4:
                    new StudentView();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } else {
            if (numberOfConsecutiveIncorrectLoginAttempts < 3) {
                JOptionPane.showMessageDialog(frame, "Thông tin tài khoản hoặc mật khẩu không chính xác!!", "Oops, error!", JOptionPane.ERROR_MESSAGE);
            }
            numberOfConsecutiveIncorrectLoginAttempts++;
        }
    }

    public void checkFieldIsEmpty() {
        Timer checkFieldIsEmpty = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleUsernameIsEmptyCenterFormLogin.setVisible(usernameInputFormUsernameCenterFormLogin.getText().isBlank());
                titlePasswordIsEmptyCenterFormLogin.setVisible(passwordInputFormUsernameCenterFormLogin.getText().isBlank());
                if (!usernameInputFormUsernameCenterFormLogin.getText().isBlank() && !passwordInputFormUsernameCenterFormLogin.getText().isBlank()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        checkFieldIsEmpty.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            this.checkFieldIsEmpty();
            if (usernameInputFormUsernameCenterFormLogin.getText().isBlank() || passwordInputFormUsernameCenterFormLogin.getText().isBlank()) {
                return;
            }
            this.blockSpamLogin();
            userAccountDAO.clearUserToFile();
            String username = usernameInputFormUsernameCenterFormLogin.getText();
            String password = passwordInputFormUsernameCenterFormLogin.getText();
            UserAccount userAccount = new UserAccount(username, password);
            String result = userAccountDAO.checkUserLogin(userAccount);
            this.resultLogin(result, userAccount);
        }
        if (e.getSource() == buttonResetPassword) {
            passwordInputFormUsernameCenterFormLogin.setText(null);
        }

    }
}
