package com.mycompany.school1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class TestRender {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            New_Student frame = new New_Student();
            frame.setSize(750, 480);
            frame.validate();

            int w = frame.getWidth();
            int h = frame.getHeight();

            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();

            // Fill background white
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, w, h);

            // Paint content pane
            frame.getContentPane().printAll(g2d);
            g2d.dispose();

            File file = new File("assets/screenshots/test_render.png");
            ImageIO.write(img, "png", file);

            // Check non-white/non-black pixel count
            int samplePixel = img.getRGB(w/2, h/2);
            System.out.println("TEST_RENDER_SUCCESS: " + file.getAbsolutePath() + " pixel=" + Integer.toHexString(samplePixel));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
