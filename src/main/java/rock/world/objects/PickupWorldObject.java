package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public abstract class PickupWorldObject extends InteractiveWorldObject {
    public PickupWorldObject(World world, Coords position) {
        super(world, position);
    }

    @Override
    public void interact() {
        onPickup();

        //remove object from the world and replace it with a floor
        getWorld().removeObject(getPosition());
        getWorld().addObject(new Floor(getWorld(), getPosition()));
    }

    public abstract void onPickup();
}
