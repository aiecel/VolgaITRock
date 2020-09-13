package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public abstract class InteractiveWorldObject extends WorldObject {
    public InteractiveWorldObject(World world, Coords position) {
        super(world, position);
    }

    public abstract void interact();
}
