package rock.world;

import rock.world.objects.WorldObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorldImpl implements World {
    private final int width;
    private final int height;

    private Player player;
    private Collection<WorldObject> objects;

    public WorldImpl(int width, int height) {
        this(width, height, null, new ArrayList<>());
    }

    public WorldImpl(int width, int height, Player player, Collection<WorldObject> objects) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.objects = objects;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        if (isOnMap(player.getPosition())) {
            this.player = player;
        }
    }

    @Override
    public void addObject(WorldObject object) {
        if (isOnMap(object.getPosition())) {
            objects.add(object);
        }
    }

    public WorldObject getObject(Coords position) {
        if (!isOnMap(position)) return null;

        return objects.stream()
                .filter(object -> object.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void removeObject(Coords position) {
        if (!isOnMap(position)) return;
        objects.remove(getObject(position));
    }

    @Override
    public Object clone() {
        WorldImpl cloneWorld;

        try {
            cloneWorld = (WorldImpl) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneWorld = new WorldImpl(width, height);
        }

        //clone player
        cloneWorld.setPlayer(
                new Player(
                        cloneWorld,
                        player.getPosition(),
                        new ArrayList<>(player.getKeys()),
                        player.getHealth(),
                        player.getDistanceTravelled()
                )
        );

        //clone objects
        cloneWorld.objects = new ArrayList<>();
        for (WorldObject object : objects) {
            try {
                cloneWorld.addObject(object.cloneToWorld(cloneWorld));
            } catch (CloneNotSupportedException e) {
                System.err.println("Can't clone'" + object + "'");
            }
        }

        return cloneWorld;
    }

    private boolean isOnMap(Coords coords) {
        return coords.getX() >= 0 && coords.getY() >= 0 && coords.getX() < width && coords.getY() < height;
    }
}
