package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

public class Wall extends WorldObject {
    public Wall(World world, Coords position) {
        super(world, position);
        setWalkable(false);
    }
}
