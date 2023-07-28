package game;

import entity.NPC_Scoops;
import object.OBJ_CHEST;
import object.OBJ_DOOR;
import object.OBJ_KEY;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setObject() {
        gp.obj[0] = new OBJ_KEY(gp);
        gp.obj[0].worldX = gp.tileSize*26;
        gp.obj[0].worldY = gp.tileSize*16;

        gp.obj[1] = new OBJ_DOOR(gp);
        gp.obj[1].worldX = gp.tileSize*46;
        gp.obj[1].worldY = gp.tileSize*21;

        gp.obj[3] = new OBJ_CHEST(gp);
        gp.obj[3].worldX = gp.tileSize*46;
        gp.obj[3].worldY = gp.tileSize*24;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Scoops(gp);
        gp.npc[0].worldX = gp.tileSize*25;
        gp.npc[0].worldY = gp.tileSize*15;
    }
}
