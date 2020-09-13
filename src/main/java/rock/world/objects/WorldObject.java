package rock.world.objects;

import rock.world.Coords;
import rock.world.World;

import java.util.Objects;

public abstract class WorldObject implements Cloneable {
    private World world;
    private final Coords position;
    private boolean isWalkable;

    public WorldObject(World world, Coords position) {
        this.world = world;
        this.position = new Coords(position);
        this.isWalkable = true;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Coords getPosition() {
        return position;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public WorldObject cloneToWorld(World newWorld) throws CloneNotSupportedException {
        WorldObject clone = (WorldObject) this.clone();
        clone.world = newWorld;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldObject object = (WorldObject) o;
        return isWalkable == object.isWalkable &&
                Objects.equals(world, object.world) &&
                Objects.equals(position, object.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, position, isWalkable);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
