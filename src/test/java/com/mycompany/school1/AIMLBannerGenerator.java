package com.mycompany.school1;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class AIMLBannerGenerator {
    public static void main(String[] args) {
        File outputDir = new File("assets/profile_dp");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try {
            // 1. Dark Theme AI & Machine Learning Banner
            generateDarkBanner("assets/profile_dp/linkedin_banner_aiml_dark.png");

            // 2. Light Theme AI & Machine Learning Banner
            generateLightBanner("assets/profile_dp/linkedin_banner_aiml_light.png");

            System.out.println("AIML_BANNERS_GENERATED_SUCCESSFULLY");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void generateDarkBanner(String fileName) throws Exception {
        int w = 1200;
        int h = 675; // 16:9 aspect ratio
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // Enable Anti-Aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Dark Slate Gradient Background
        GradientPaint bgGradient = new GradientPaint(0, 0, new Color(15, 23, 42), w, h, new Color(30, 41, 59));
        g2d.setPaint(bgGradient);
        g2d.fillRect(0, 0, w, h);

        // Card Container
        g2d.setColor(new Color(30, 41, 59, 200));
        g2d.fillRoundRect(80, 80, w - 160, h - 160, 30, 30);
        g2d.setColor(new Color(51, 65, 85));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(80, 80, w - 160, h - 160, 30, 30);

        // Draw 5x5 Identicon on the Left
        drawIdenticon(g2d, 140, 160, 240, new Color(59, 130, 246), new Color(147, 51, 234));

        // Right Content: Text & Branding
        int textX = 430;

        // GitHub Logo Badge
        g2d.setColor(new Color(59, 130, 246));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
        g2d.drawString("GITHUB PORTFOLIO", textX, 210);

        // GitHub URL
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
        g2d.drawString("github.com/mohdsss", textX, 280);

        // Divider Line
        g2d.setColor(new Color(59, 130, 246));
        g2d.fillRect(textX, 315, 450, 4);

        // Title: AI & Machine Learning Engineer
        g2d.setColor(new Color(56, 189, 248)); // Cyan Accent
        g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
        g2d.drawString("AI & Machine Learning Engineer", textX, 375);

        // Subtitle / Specializations
        g2d.setColor(new Color(148, 163, 184));
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 22));
        g2d.drawString("Deep Learning • Computer Vision • NLP • Python", textX, 425);

        g2d.dispose();
        ImageIO.write(img, "png", new File(fileName));
    }

    private static void generateLightBanner(String fileName) throws Exception {
        int w = 1200;
        int h = 675;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Light Grey Background
        g2d.setColor(new Color(241, 245, 249));
        g2d.fillRect(0, 0, w, h);

        // White Card Container
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(80, 80, w - 160, h - 160, 30, 30);
        g2d.setColor(new Color(226, 232, 240));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(80, 80, w - 160, h - 160, 30, 30);

        // Draw 5x5 Green Identicon on Left
        drawIdenticon(g2d, 140, 160, 240, new Color(16, 185, 129), new Color(4, 120, 87));

        // Right Content
        int textX = 430;

        // GitHub Badge
        g2d.setColor(new Color(16, 185, 129));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
        g2d.drawString("GITHUB PORTFOLIO", textX, 210);

        // GitHub URL
        g2d.setColor(new Color(15, 23, 42));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
        g2d.drawString("github.com/mohdsss", textX, 280);

        // Divider
        g2d.setColor(new Color(16, 185, 129));
        g2d.fillRect(textX, 315, 450, 4);

        // Title: AI & ML Engineer
        g2d.setColor(new Color(5, 150, 105));
        g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
        g2d.drawString("AI & ML Engineer", textX, 375);

        // Subtitle
        g2d.setColor(new Color(100, 116, 139));
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 22));
        g2d.drawString("Machine Learning • Artificial Intelligence • Data Science", textX, 425);

        g2d.dispose();
        ImageIO.write(img, "png", new File(fileName));
    }

    private static void drawIdenticon(Graphics2D g2d, int x, int y, int size, Color c1, Color c2) {
        // Draw white frame background
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x - 10, y - 10, size + 20, size + 20, 20, 20);
        g2d.setColor(new Color(226, 232, 240));
        g2d.drawRoundRect(x - 10, y - 10, size + 20, size + 20, 20, 20);

        // 5x5 Grid Pattern (Identicon space invader)
        int[][] grid = {
            {1, 0, 1, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1},
            {0, 1, 1, 1, 0}
        };

        int block = size / 5;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (grid[r][c] == 1) {
                    g2d.setColor((r + c) % 2 == 0 ? c1 : c2);
                    g2d.fillRect(x + c * block, y + r * block, block, block);
                }
            }
        }
    }
}
