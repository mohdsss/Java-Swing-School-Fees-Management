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
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        File outputDir = new File("assets/screenshots");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // 1. student.java (Main Dashboard with open Menu)
        student frameStudent1 = new student();
        try {
            JMenu menu1 = getField(frameStudent1, "jMenu1", JMenu.class);
            if (menu1 != null) {
                menu1.setSelected(true);
                menu1.getPopupMenu().setVisible(true);
            }
        } catch (Exception ignored) {}
        captureFrameSwing(frameStudent1, "student_menu_open.png");
        frameStudent1.dispose();

        // 2. student.java (Main Dashboard with Fees Menu)
        student frameStudent2 = new student();
        try {
            JMenu menu2 = getField(frameStudent2, "jMenu2", JMenu.class);
            if (menu2 != null) {
                menu2.setSelected(true);
                menu2.getPopupMenu().setVisible(true);
            }
        } catch (Exception ignored) {}
        captureFrameSwing(frameStudent2, "fees_menu_open.png");
        frameStudent2.dispose();

        // 3. New_Student.java (Student Registration Form filled with Dummy Data)
        New_Student frameNewStudent = new New_Student();
        setFieldValue(frameNewStudent, "n", "Rahul Sharma");
        setFieldValue(frameNewStudent, "cl", "10");
        setFieldValue(frameNewStudent, "cl1", "Delhi");
        setAreaValue(frameNewStudent, "ad", "123 Park Street, Connaught Place, New Delhi");
        captureFrameSwing(frameNewStudent, "new_student_form.png");
        frameNewStudent.dispose();

        // 4. fees.java (Total Fees Setup with Dropdown)
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
        captureFrameSwing(frameFees, "fees_dropdown_open.png");
        frameFees.dispose();

        // 5. Instalment.java (Fee Payment with Dropdown)
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
        captureFrameSwing(frameInstalment, "installment_dropdown_open.png");
        frameInstalment.dispose();

        // 6. show.java (SQL JOIN Table filled with Dummy Data)
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
        captureFrameSwing(frameShow, "show_join_table.png");
        frameShow.dispose();

        // 7. tale.java (Student Management Table filled with Dummy Data)
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
        captureFrameSwing(frameTale, "student_table_view.png");
        frameTale.dispose();

        // 8. up.java (Student Update Form filled with Dummy Data)
        up frameUp = new up();
        setFieldValue(frameUp, "n", "Rahul Sharma");
        setFieldValue(frameUp, "cl", "10");
        setFieldValue(frameUp, "cl1", "Delhi");
        setAreaValue(frameUp, "ad", "123 Park Street, Connaught Place, New Delhi");
        captureFrameSwing(frameUp, "student_update_form.png");
        frameUp.dispose();

        System.out.println("ALL_BRIGHT_COLOR_SCREENSHOTS_SUCCESSFUL");
        System.exit(0);
    }

    private static void captureFrameSwing(JFrame frame, String fileName) {
        try {
            frame.pack();
            int w = Math.max(frame.getWidth(), 650);
            int h = Math.max(frame.getHeight(), 450);
            frame.setSize(w, h);

            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();

            // Fill window background with clean light gray color
            g2d.setColor(new Color(240, 240, 240));
            g2d.fillRect(0, 0, w, h);

            // Enable text anti-aliasing for clear fonts
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Render all components cleanly
            frame.printAll(g2d);
            g2d.dispose();

            File outFile = new File("assets/screenshots/" + fileName);
            ImageIO.write(img, "png", outFile);
            System.out.println("Saved bright colorful screenshot: " + outFile.getAbsolutePath());
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
