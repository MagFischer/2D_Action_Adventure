package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends SuperObject{

    public OBJ_CHEST() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
