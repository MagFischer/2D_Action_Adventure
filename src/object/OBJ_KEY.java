package object;

import game.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_KEY extends SuperObject {

    GamePanel gp;

    public OBJ_KEY(GamePanel gp) {
        name = "Key";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
