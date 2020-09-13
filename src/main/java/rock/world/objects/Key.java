package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public class Key extends PickupWorldObject {
    private final char letter;

    public Key(World world, Coords position, char letter) {
        super(world, position);
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public void onPickup() {
        //add key to player
        getWorld().getPlayer().addKey(letter);
    }
}