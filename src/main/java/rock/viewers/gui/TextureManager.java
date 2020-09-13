package rock.viewers.gui;

import rock.world.objects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureManager {
    public BufferedImage playerImage;
    public BufferedImage floorImage;
    public BufferedImage wallImage;
    public BufferedImage fireImage;
    public BufferedImage healthKitImage;
    public BufferedImage exitImage;
    public BufferedImage keyImage;
    public BufferedImage doorImage;

    public TextureManager() {
        try {
            playerImage = ImageIO.read(new File("textures/player.png"));
            floorImage = ImageIO.read(new File("textures/floor.png"));
            wallImage = ImageIO.read(new File("textures/wall.png"));
            fireImage = ImageIO.read(new File("textures/fire.png"));
            healthKitImage = ImageIO.read(new File("textures/healthkit.png"));
            exitImage = ImageIO.read(new File("textures/exit.png"));
            keyImage = ImageIO.read(new File("textures/key.png"));
            doorImage = ImageIO.read(new File("textures/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public BufferedImage getImage(WorldObject object) {
        if (object instanceof Floor) return floorImage;
        if (object instanceof Fire) return fireImage;
        if (object instanceof HealthKit) return healthKitImage;
        if (object instanceof Exit) return exitImage;
        if (object instanceof Key) return keyImage;
        if (object instanceof Door) return doorImage;
        return wallImage;
    }
}
