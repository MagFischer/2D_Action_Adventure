package entity;


import game.GamePanel;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;



    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 32;
        solidArea.width = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 7 ;
        worldY = gp.tileSize * 3;
        speed = 4;
        direction = "front";
    }

    public void getPlayerImage() {
        try {
            front = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/front.png"));
            back = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/back.png"));
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/up2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/down2.png"));
            left = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/left2.png"));
            right = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            }
            if (keyH.downPressed == true) {
                direction = "down";

            }
            if (keyH.leftPressed == true) {
                direction = "left1";

            }
            if (keyH.rightPressed == true) {
                direction = "right1";


            }


            //check tile collision
            collisionON = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

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




        } else {
            if (Objects.equals(direction, "down")) {
                direction = "front";
            } else if (Objects.equals(direction, "up")) {
                direction = "back";
            } else if (Objects.equals(direction, "left1")) {
                direction = "left";
            } else if (Objects.equals(direction, "right1")) {
                direction = "right";
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key" -> {
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    gp.playSE(1);
                }
                case "Door" -> {
                    if(hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("Key: " + hasKey);
                    }
                }
                case "Boots" -> {
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;


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

