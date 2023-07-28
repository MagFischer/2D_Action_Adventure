package object;

import game.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends SuperObject{

    GamePanel gp;

    public OBJ_CHEST(GamePanel gp) {
        name = "Chest";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
