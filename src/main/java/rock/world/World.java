package rock.world;

import rock.world.objects.WorldObject;

public interface World extends Cloneable {
    int getWidth();

    int getHeight();

    Player getPlayer();

    void setPlayer(Player player);

    WorldObject getObject(Coords position);

    default WorldObject getObject(int x, int y) {
        return getObject(new Coords(x, y));
    }

    void addObject(WorldObject object);

    void removeObject(Coords position);

    default void removeObject(int x, int y) {
        removeObject(new Coords(x, y));
    }

    Object clone();
}