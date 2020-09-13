package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public class HealthKit extends PickupWorldObject {
    public HealthKit(World world, Coords position) {
        super(world, position);
    }

    @Override
    public void onPickup() {
        //heal player
        getWorld().getPlayer().heal();
    }
}
