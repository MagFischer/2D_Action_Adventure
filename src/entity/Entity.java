package entity;

import game.GamePanel;
import game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage front, back, up1, up2, down1, down2, left, left1, left2, right, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionON = false;
    public int actionLockCounter;
    String[] dialouge = new String[20];
    int dialougeIndex = 0;

    //Character Status
    public int maxLife;
    public int life;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() { }

    public void speak() {

        if (dialouge[dialougeIndex] == null) {
            dialougeIndex = 0;
        }
        gp.ui.currentDialogue = dialouge[dialougeIndex];
        dialougeIndex++;

        switch (gp.player.direction) {
            case "up" -> direction = "front";
            case "down" -> direction = "back";
            case "left1" -> direction = "right";
            case "right1" -> direction = "left";

        }
    }

    public void update() {

        setAction();
        collisionON = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);


        //if collision is false, player can move
        if (!collisionON) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left1" -> worldX -= speed;
                case "right1" -> worldX += speed;
            }
        }


        spriteCounter++;
        if (spriteCounter > 9) {
            if (spriteNum == 1) {
                spriteNum = 2;

            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left1" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right1" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                case "front" -> image = front;
                case "back" -> image = back;
                case "left" -> image = left;
                case "right" -> image = right;

            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }


    public BufferedImage setUp(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}
