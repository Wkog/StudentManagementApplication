package wkog.view;

import wkog.controller.NotificationController;
import wkog.view.custom.RoundedBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminView {
    private static final int SCREEN_WIDTH_APPLICATIONVIEW = 1400;
    private static final int SCREEN_HEIGHT_APPLICATIONVIEW = 939;
    private static NotificationController notificationController = new NotificationController();
    private static List<String> notificationList = new ArrayList<>();

    public AdminView() {
        Border border = BorderFactory.createLineBorder(Color.WHITE, 1, true);
        Border roundedBorder15 = new RoundedBorder(border, 15);
        Border roundedBorder10 = new RoundedBorder(border, 10);

        ImageIcon logoColorSizeMiniImageViewAdminPage = new ImageIcon("src/main/java/wkog/images/logos/logoColorSizeMini.png");
        ImageIcon iconAddUserImageViewAdminPage = new ImageIcon("src/main/java/wkog/images/icons/adduser.png");

        JFrame frameAdminPage = new JFrame("Student Management - Admin");
        frameAdminPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameAdminPage.setIconImage(logoColorSizeMiniImageViewAdminPage.getImage());
        frameAdminPage.setSize(SCREEN_WIDTH_APPLICATIONVIEW, SCREEN_HEIGHT_APPLICATIONVIEW);
        frameAdminPage.setResizable(false);
        frameAdminPage.setLayout(null);
        frameAdminPage.setVisible(true);


        JPanel backgroudAdminPage = new JPanel();
        backgroudAdminPage.setBounds(0,0,1400,900);
        backgroudAdminPage.setBackground(new Color(248, 248, 248));
        backgroudAdminPage.setLayout(null);

        JPanel notificationFrame = new JPanel();
        notificationFrame.setBounds(0,0,1400,20);
        notificationFrame.setBackground(Color.YELLOW);
        notificationFrame.setLayout(null);

        JLabel notificationText = new JLabel();

        JPanel frameInfoUser = new JPanel();
        frameInfoUser.setBounds(0,20,700,450);
        frameInfoUser.setBackground(null);
        frameInfoUser.setLayout(null);

        Image tempIconAddUserImageViewAdminPage = iconAddUserImageViewAdminPage.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        iconAddUserImageViewAdminPage = new ImageIcon(tempIconAddUserImageViewAdminPage);
        JLabel avatarUser = new JLabel(iconAddUserImageViewAdminPage);
        avatarUser.setBounds(20,40,100,120);
        avatarUser.setBackground(Color.WHITE);
        avatarUser.setBorder(roundedBorder10);

        JPanel frameInfoUserSelect = new JPanel();
        frameInfoUserSelect.setBounds(700,20,700,450);
        frameInfoUserSelect.setBackground(Color.BLUE);
        frameInfoUserSelect.setLayout(null);

        JLabel avatarUserSelect = new JLabel(iconAddUserImageViewAdminPage);
        avatarUserSelect.setBounds(20,40,100,120);
        avatarUserSelect.setBackground(Color.WHITE);
        avatarUserSelect.setBorder(roundedBorder10);

        notificationList.addAll(notificationController.getNotification());
        for (String i : notificationList) {
            System.out.println("Notification: "+i);
        }

        Timer animationNotification = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO document why this method is empty
            }
        });
        animationNotification.start();

        frameInfoUser.add(avatarUser);
        frameInfoUserSelect.add(avatarUserSelect);

        backgroudAdminPage.add(frameInfoUser);
        backgroudAdminPage.add(frameInfoUserSelect);

        frameAdminPage.add(notificationFrame);
        frameAdminPage.add(backgroudAdminPage);
        frameAdminPage.setLocationRelativeTo(null);

    }
}
