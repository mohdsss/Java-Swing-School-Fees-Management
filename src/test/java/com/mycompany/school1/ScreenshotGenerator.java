package com.mycompany.school1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.Field;

public class ScreenshotGenerator {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        File outputDir = new File("assets/screenshots");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try {
            Robot robot = new Robot();

            // 1. student.java - Student Menu Dropdown Open
            student frameStudent1 = new student();
            frameStudent1.pack();
            frameStudent1.setSize(600, 400);
            frameStudent1.setLocationRelativeTo(null);
            frameStudent1.setVisible(true);
            Thread.sleep(700);
            JMenu menu1 = getField(frameStudent1, "jMenu1", JMenu.class);
            if (menu1 != null) {
                menu1.doClick();
            }
            Thread.sleep(500);
            captureRobot(robot, frameStudent1, "student_menu_open.png");
            frameStudent1.dispose();

            // 2. student.java - Fees Menu Dropdown Open
            student frameStudent2 = new student();
            frameStudent2.pack();
            frameStudent2.setSize(600, 400);
            frameStudent2.setLocationRelativeTo(null);
            frameStudent2.setVisible(true);
            Thread.sleep(700);
            JMenu menu2 = getField(frameStudent2, "jMenu2", JMenu.class);
            if (menu2 != null) {
                menu2.doClick();
            }
            Thread.sleep(500);
            captureRobot(robot, frameStudent2, "fees_menu_open.png");
            frameStudent2.dispose();

            // 3. New_Student.java (Student Registration Form)
            New_Student frameNewStudent = new New_Student();
            setFieldValue(frameNewStudent, "n", "Rahul Sharma");
            setFieldValue(frameNewStudent, "cl", "10");
            setFieldValue(frameNewStudent, "cl1", "Delhi");
            setAreaValue(frameNewStudent, "ad", "123 Park Street, Connaught Place, New Delhi");
            frameNewStudent.pack();
            frameNewStudent.setSize(750, 480);
            frameNewStudent.setLocationRelativeTo(null);
            frameNewStudent.setVisible(true);
            Thread.sleep(800);
            captureRobot(robot, frameNewStudent, "new_student_form.png");
            frameNewStudent.dispose();

            // 4. fees.java (Total Fees Setup with Dropdown Open)
            fees frameFees = new fees();
            JComboBox<String> cmFees = getField(frameFees, "cm", JComboBox.class);
            if (cmFees != null) {
                cmFees.removeAllItems();
                cmFees.addItem("Rahul Sharma");
                cmFees.addItem("Priya Singh");
                cmFees.addItem("Aman Verma");
                cmFees.setSelectedItem("Rahul Sharma");
            }
            setFieldValue(frameFees, "tx", "50000");
            frameFees.pack();
            frameFees.setSize(650, 400);
            frameFees.setLocationRelativeTo(null);
            frameFees.setVisible(true);
            Thread.sleep(700);
            if (cmFees != null) {
                cmFees.showPopup();
            }
            Thread.sleep(500);
            captureRobotWithPopup(robot, frameFees, "fees_dropdown_open.png");
            frameFees.dispose();

            // 5. Instalment.java (Fee Payment with Dropdown Open)
            Instalment frameInstalment = new Instalment();
            JComboBox<String> cbInst = getField(frameInstalment, "cb", JComboBox.class);
            if (cbInst != null) {
                cbInst.removeAllItems();
                cbInst.addItem("Rahul Sharma");
                cbInst.addItem("Priya Singh");
                cbInst.addItem("Aman Verma");
                cbInst.setSelectedItem("Rahul Sharma");
            }
            setLabelValue(frameInstalment, "lb2", "50000"); // Total Fees
            setLabelValue(frameInstalment, "lb1", "35000"); // Due Fees
            setFieldValue(frameInstalment, "tf", "15000");  // Installment
            frameInstalment.pack();
            frameInstalment.setSize(650, 480);
            frameInstalment.setLocationRelativeTo(null);
            frameInstalment.setVisible(true);
            Thread.sleep(700);
            if (cbInst != null) {
                cbInst.showPopup();
            }
            Thread.sleep(500);
            captureRobotWithPopup(robot, frameInstalment, "installment_dropdown_open.png");
            frameInstalment.dispose();

            // 6. show.java (SQL JOIN Table)
            show frameShow = new show();
            JTable tableShow = getField(frameShow, "jTable1", JTable.class);
            if (tableShow != null) {
                String mode[] = {"rid", "fid", "name", "class", "Address", "city", "TotalFees", "Feesinstalment"};
                DefaultTableModel md = new DefaultTableModel(mode, 0);
                md.addRow(new Object[]{"1", "1", "Rahul Sharma", "10", "123 Park Street, Connaught Place", "Delhi", "50000", "15000"});
                md.addRow(new Object[]{"2", "2", "Priya Singh", "12", "456 Link Road, Andheri West", "Mumbai", "60000", "20000"});
                md.addRow(new Object[]{"3", "3", "Aman Verma", "9", "789 M.I. Road, Raja Park", "Jaipur", "45000", "10000"});
                tableShow.setModel(md);
            }
            frameShow.pack();
            frameShow.setSize(800, 450);
            frameShow.setLocationRelativeTo(null);
            frameShow.setVisible(true);
            Thread.sleep(800);
            captureRobot(robot, frameShow, "show_join_table.png");
            frameShow.dispose();

            // 7. tale.java (Student Management Table View)
            tale frameTale = new tale();
            JTable tableTale = getField(frameTale, "jTable1", JTable.class);
            if (tableTale != null) {
                String mode[] = {"id", "Name", "Class", "Address", "City", "TotalFees"};
                DefaultTableModel md = new DefaultTableModel(mode, 0);
                md.addRow(new Object[]{"1", "Rahul Sharma", "10", "123 Park Street, Connaught Place", "Delhi", "50000"});
                md.addRow(new Object[]{"2", "Priya Singh", "12", "456 Link Road, Andheri West", "Mumbai", "60000"});
                md.addRow(new Object[]{"3", "Aman Verma", "9", "789 M.I. Road, Raja Park", "Jaipur", "45000"});
                tableTale.setModel(md);
                tableTale.setRowSelectionInterval(0, 0);
            }
            setFieldValue(frameTale, "re", "Rahul Sharma");
            frameTale.pack();
            frameTale.setSize(850, 500);
            frameTale.setLocationRelativeTo(null);
            frameTale.setVisible(true);
            Thread.sleep(800);
            captureRobot(robot, frameTale, "student_table_view.png");
            frameTale.dispose();

            // 8. up.java (Student Update Form)
            up frameUp = new up();
            setFieldValue(frameUp, "n", "Rahul Sharma");
            setFieldValue(frameUp, "cl", "10");
            setFieldValue(frameUp, "cl1", "Delhi");
            setAreaValue(frameUp, "ad", "123 Park Street, Connaught Place, New Delhi");
            frameUp.pack();
            frameUp.setSize(700, 480);
            frameUp.setLocationRelativeTo(null);
            frameUp.setVisible(true);
            Thread.sleep(800);
            captureRobot(robot, frameUp, "student_update_form.png");
            frameUp.dispose();

            System.out.println("ALL_NATIVE_ROBOT_SCREENSHOTS_SUCCESSFUL");
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void captureRobot(Robot robot, JFrame frame, String fileName) {
        try {
            Point p = frame.getLocationOnScreen();
            Dimension d = frame.getSize();
            Rectangle rect = new Rectangle(p.x, p.y, d.width, d.height);
            BufferedImage img = robot.createScreenCapture(rect);
            File outFile = new File("assets/screenshots/" + fileName);
            ImageIO.write(img, "png", outFile);
            System.out.println("Robot captured: " + outFile.getAbsolutePath() + " [" + d.width + "x" + d.height + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void captureRobotWithPopup(Robot robot, JFrame frame, String fileName) {
        try {
            Point p = frame.getLocationOnScreen();
            Dimension d = frame.getSize();
            int extraHeight = 120; // Expanded combobox popup
            Rectangle rect = new Rectangle(p.x, p.y, d.width, d.height + extraHeight);
            BufferedImage img = robot.createScreenCapture(rect);
            File outFile = new File("assets/screenshots/" + fileName);
            ImageIO.write(img, "png", outFile);
            System.out.println("Robot captured popup: " + outFile.getAbsolutePath() + " [" + d.width + "x" + (d.height + extraHeight) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T getField(Object obj, String fieldName, Class<T> type) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return (T) f.get(obj);
        } catch (Exception e) {
            return null;
        }
    }

    private static void setFieldValue(Object obj, String fieldName, String value) {
        JTextField tf = getField(obj, fieldName, JTextField.class);
        if (tf != null) tf.setText(value);
    }

    private static void setAreaValue(Object obj, String fieldName, String value) {
        JTextArea ta = getField(obj, fieldName, JTextArea.class);
        if (ta != null) ta.setText(value);
    }

    private static void setLabelValue(Object obj, String fieldName, String value) {
        JLabel lb = getField(obj, fieldName, JLabel.class);
        if (lb != null) lb.setText(value);
    }
}
