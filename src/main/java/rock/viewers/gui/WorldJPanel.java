package rock.viewers.gui;

import rock.world.World;
import rock.world.objects.*;

import javax.swing.*;
import java.awt.*;

public class WorldJPanel extends JPanel {
    private static final int TILE_SIZE = 25;

    private final TextureManager textureManager;
    private final World world;

    public WorldJPanel(World world) {
        this.textureManager = new TextureManager();
        this.world = world;
        setPreferredSize(new Dimension(TILE_SIZE * world.getWidth(), TILE_SIZE * world.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                drawObject(world.getObject(x, y), g);
            }
        }

        drawPlayer(g);
    }

    private void drawObject(WorldObject object, Graphics g) {
        g.drawImage(textureManager.getImage(object),
                object.getPosition().getX() * TILE_SIZE,
                object.getPosition().getY() * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE,
                null
        );

        if (object instanceof Key) {
            String letter = String.valueOf(((Key) object).getLetter());
            g.setColor(Color.white);
            g.drawString(letter, object.getPosition().getX() * TILE_SIZE + (TILE_SIZE / 2 - 3), object.getPosition().getY() * TILE_SIZE + (TILE_SIZE / 2 + 5));
        }

        if (object instanceof Door) {
            String letter = String.valueOf(((Door) object).getLetter()).toUpperCase();
            g.setColor(Color.white);
            g.drawString(letter, object.getPosition().getX() * TILE_SIZE + (TILE_SIZE / 2 - 3), object.getPosition().getY() * TILE_SIZE + (TILE_SIZE / 2 + 5));
        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(textureManager.getPlayerImage(),
                world.getPlayer().getPosition().getX() * TILE_SIZE,
                world.getPlayer().getPosition().getY() * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE,
                null
        );
    }
}
