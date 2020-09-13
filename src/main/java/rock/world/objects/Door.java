package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public class Door extends InteractiveWorldObject {
    private final char letter;

    public Door(World world, Coords position, char letter) {
        super(world, position);
        setWalkable(false);
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public void interact() {
        if (getWorld().getPlayer().hasKey(letter)) {
            setWalkable(true);
        }
    }
}