package wkog.view;

import javax.swing.*;

public class TeacherView {
    private static final int SCREEN_WIDTH_APPLICATIONVIEW = 1600;
    private static final int SCREEN_HEIGHT_APPLICATIONVIEW = 1000;

    public TeacherView() {
        JFrame frame = new JFrame("Student Management - Teacher");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH_APPLICATIONVIEW, SCREEN_HEIGHT_APPLICATIONVIEW);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
