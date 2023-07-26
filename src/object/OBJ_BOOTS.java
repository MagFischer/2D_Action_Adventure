package object;

import game.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends SuperObject {

    GamePanel gp;

    public OBJ_BOOTS(GamePanel gp) {
        name = "Boots";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

