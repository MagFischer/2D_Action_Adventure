package game;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font minecraft;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;

    public int commandNum = 0;






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

        //title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //play state
        if (gp.gameState == gp.playState) {
            //Do playstate stuff later
            gp.resumeMusic();
        }

        //pause state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            gp.stopMusic();
        }

        //dialogue state
        if (gp.gameState == gp.dialougeState) {
            drawDialougeScreen();
        }



    }

    public void drawTitleScreen() {

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 83F));
        String text = "Pimpi";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        //Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        //Main Color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Pimpi Image
        x = gp.screenWidth/2 - (gp.tileSize);
        y += gp.tileSize;
        g2.drawImage(gp.player.front, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 46F));

        text = "NEW GAME";
        x = getXforCenterText(text);
        y = gp.tileSize*8;
        g2.drawString(text, x, y);
        if (commandNum == 0 ) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "CONTINUE";
        x = getXforCenterText(text);
        y = gp.tileSize*9;
        g2.drawString(text, x, y);
        if (commandNum == 1 ) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y = gp.tileSize*10;
        g2.drawString(text, x, y);
        if (commandNum == 2 ) {
            g2.drawString(">", x-gp.tileSize, y);
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
        for (String line : currentDialogue.split("\n")) {
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
