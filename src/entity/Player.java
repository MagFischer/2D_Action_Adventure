package entity;

import game.GamePanel;
import game.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;





    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

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
        worldX = gp.tileSize * 15 ;
        worldY = gp.tileSize * 10;
        speed = 4;
        direction = "front";

        //Player Status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        front = setUp("player/front");
        back = setUp("player/back");
        up1 = setUp("player/up1");
        up2 = setUp("player/up2");
        down1 = setUp("player/down1");
        down2 = setUp("player/down2");
        left = setUp("player/left");
        left1 = setUp("player/left1");
        left2 = setUp("player/left2");
        right = setUp("player/right");
        right1 = setUp("player/right1");
        right2 = setUp("player/right2");

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

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
            switch (objectName)  {
                case "Key" -> {
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSE(1);
                }
                case "Door" -> {
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.playSE(1);
                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                }
                case "Chest" -> {
                    if(hasKey > 0) {
                        gp.ui.gameFinished = true;
                        gp.playSE(3);
                        gp.stopMusic();

                    } else {
                        gp.ui.showMessage("You need a key!");
                    }
                }
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialougeState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
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
        g2.drawImage(image, screenX, screenY,null);
    }



}

