package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public class Fire extends WorldObject implements Damaging {
    private final int damage;

    public Fire(World world, Coords position, int damage) {
        super(world, position);
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
