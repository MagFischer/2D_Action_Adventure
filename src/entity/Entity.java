package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage front, back, up1, up2, down1, down2, left, left1, left2, right, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionON = false;


}
