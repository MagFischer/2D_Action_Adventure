package entity;

import game.GamePanel;

import java.util.Random;

public class NPC_Scoops extends Entity {

    public NPC_Scoops(GamePanel gp) {
        super(gp);

        direction = "front";
        speed = 1;

        getImage();
    }


    public void getImage() {

        front = setUp("npc/scoops_front");
        back = setUp("npc/scoops_back");
        up1 = setUp("npc/scoops_up1");
        up2 = setUp("npc/scoops_up2");
        down1 = setUp("npc/scoops_down1");
        down2 = setUp("npc/scoops_down2");
        left = setUp("npc/scoops_left");
        left1 = setUp("npc/scoops_left1");
        left2 = setUp("npc/scoops_left2");
        right = setUp("npc/scoops_right");
        right1 = setUp("npc/scoops_right1");
        right2 = setUp("npc/scoops_right2");

    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 &&  i <= 50) {
                direction = "down";
            }
            if (i > 50  && i <= 75) {
                direction = "left1";
            }
            if (i > 75) {
                direction = "right1";
            }

            actionLockCounter = 0;
        }
    }



}
