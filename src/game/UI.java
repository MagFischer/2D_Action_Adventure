package game;


import object.OBJ_KEY;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font minecraft;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialouge;




    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/Minecraft.ttf");
            minecraft = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (IOException | FontFormatException ignored) {

        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(minecraft);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        //playstate
        if (gp.gameState == gp.playState) {
            //Do playstate stuff later
            gp.resumeMusic();
        }

        //pausestate
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            gp.stopMusic();
        }
        if (gp.gameState == gp.dialougeState) {
            drawDialougeScreen();
        }

    }

    public void drawDialougeScreen() {

        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialouge.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = getXforCenterText(text);

        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getXforCenterText(String text) {

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }
}
