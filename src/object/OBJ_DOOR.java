package object;

import game.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DOOR extends SuperObject {

    GamePanel gp;

    public OBJ_DOOR(GamePanel gp) {
        name = "Door";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
