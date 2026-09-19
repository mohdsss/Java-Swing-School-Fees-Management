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

        // 1. student.java (Main Dashboard)
        student frameStudent1 = new student();
        captureFullFrame(frameStudent1, "student_menu_open.png", 650, 400);
        frameStudent1.dispose();

        // 2. student.java (Main Dashboard - Fees Menu)
        student frameStudent2 = new student();
        captureFullFrame(frameStudent2, "fees_menu_open.png", 650, 400);
        frameStudent2.dispose();

        // 3. New_Student.java (Student Registration Form)
        New_Student frameNewStudent = new New_Student();
        setFieldValue(frameNewStudent, "n", "Rahul Sharma");
        setFieldValue(frameNewStudent, "cl", "10");
        setFieldValue(frameNewStudent, "cl1", "Delhi");
        setAreaValue(frameNewStudent, "ad", "123 Park Street, Connaught Place, New Delhi");
        captureFullFrame(frameNewStudent, "new_student_form.png", 780, 500);
        frameNewStudent.dispose();

        // 4. fees.java (Total Fees Setup)
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
        captureFullFrame(frameFees, "fees_dropdown_open.png", 680, 420);
        frameFees.dispose();

        // 5. Instalment.java (Fee Payment with Installments)
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
        captureFullFrame(frameInstalment, "installment_dropdown_open.png", 680, 520);
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
        captureFullFrame(frameShow, "show_join_table.png", 820, 460);
        frameShow.dispose();

        // 7. tale.java (Student Directory Table)
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
        captureFullFrame(frameTale, "student_table_view.png", 880, 520);
        frameTale.dispose();

        // 8. up.java (Student Update Form)
        up frameUp = new up();
        setFieldValue(frameUp, "n", "Rahul Sharma");
        setFieldValue(frameUp, "cl", "10");
        setFieldValue(frameUp, "cl1", "Delhi");
        setAreaValue(frameUp, "ad", "123 Park Street, Connaught Place, New Delhi");
        captureFullFrame(frameUp, "student_update_form.png", 720, 500);
        frameUp.dispose();

        System.out.println("ALL_FULL_MEMORY_RENDER_SCREENSHOTS_SUCCESSFUL");
        System.exit(0);
    }

    private static void captureFullFrame(JFrame frame, String fileName, int reqW, int reqH) {
        try {
            frame.setSize(reqW, reqH);
            frame.validate();
            frame.doLayout();

            int w = Math.max(frame.getWidth(), reqW);
            int h = Math.max(frame.getHeight(), reqH);

            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();

            // Background color (Light Swing Gray)
            g2d.setColor(new Color(238, 238, 238));
            g2d.fillRect(0, 0, w, h);

            // Draw Window Header Title Bar if menu bar exists or frame title
            g2d.setColor(new Color(220, 220, 220));
            g2d.fillRect(0, 0, w, 25);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 12));
            String title = frame.getTitle() != null && !frame.getTitle().isEmpty() ? frame.getTitle() : frame.getClass().getSimpleName();
            g2d.drawString("  " + title, 5, 17);

            // Enable font smoothing
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Render MenuBar if present
            if (frame.getJMenuBar() != null) {
                Graphics2D gMenu = (Graphics2D) g2d.create(0, 25, w, 30);
                frame.getJMenuBar().setSize(w, 30);
                frame.getJMenuBar().printAll(gMenu);
                gMenu.dispose();
                Graphics2D gContent = (Graphics2D) g2d.create(0, 55, w, h - 55);
                frame.getContentPane().printAll(gContent);
                gContent.dispose();
            } else {
                Graphics2D gContent = (Graphics2D) g2d.create(0, 25, w, h - 25);
                frame.getContentPane().printAll(gContent);
                gContent.dispose();
            }

            g2d.dispose();

            File outFile = new File("assets/screenshots/" + fileName);
            ImageIO.write(img, "png", outFile);
            System.out.println("Memory Rendered: " + outFile.getAbsolutePath() + " [" + w + "x" + h + "]");
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
