package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends SuperObject {

    public OBJ_BOOTS() {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

