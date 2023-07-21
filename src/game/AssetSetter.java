package game;

import object.OBJ_BOOTS;
import object.OBJ_CHEST;
import object.OBJ_DOOR;
import object.OBJ_KEY;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }


    public void setObject() {
        gp.obj[0] = new OBJ_KEY();
        gp.obj[0].worldX = 15 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_CHEST();
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new OBJ_DOOR();
        gp.obj[2].worldX = 11 * gp.tileSize;
        gp.obj[2].worldY = 4 * gp.tileSize;

        gp.obj[3] = new OBJ_BOOTS();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;


    }
}