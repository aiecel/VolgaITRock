package rock.pathfinding;

import rock.world.Coords;
import rock.world.Direction;
import rock.world.Player;
import rock.world.World;
import rock.world.objects.InteractiveWorldObject;
import rock.world.objects.WorldObject;

import java.util.*;
import java.util.List;

public class Head implements Cloneable {
    private final World world;
    private final Collection<Coords> visitedCoords;
    private final List<Direction> directions;

    public Head(World world) {
        this(world, new ArrayList<>(), new ArrayList<>());
    }

    public Head(World world, Collection<Coords> visitedCoords, List<Direction> directions) {
        this.world = world;
        this.visitedCoords = visitedCoords;
        this.visitedCoords.add(world.getPlayer().getPosition());
        this.directions = directions;
    }

    public World getWorld() {
        return world;
    }

    public Collection<Coords> getVisitedCoords() {
        return visitedCoords;
    }

    public void clearVisitedPoints() {
        this.visitedCoords.clear();
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void movePlayer(Direction direction) {
        //if the object in the movement direction is interactive then clear visited points
        WorldObject object = world.getObject(world.getPlayer().getPosition().add(direction.getCoords()));
        if (object instanceof InteractiveWorldObject) {
            clearVisitedPoints();
        }

        visitedCoords.add(world.getPlayer().getPosition().add(direction.getCoords()));
        world.getPlayer().move(direction);
        directions.add(direction);
    }

    public Map<Direction, WorldObject> getNeighbourObjects() {
        Map<Direction, WorldObject> neighbourObjects = new HashMap<>();
        Player player = world.getPlayer();

        WorldObject worldObject;
        for (Direction direction : Direction.directionSet()) {
            worldObject = world.getObject(player.getPosition().add(direction.getCoords()));

            if (worldObject != null) {
                neighbourObjects.put(direction, worldObject);
            }
        }

        return neighbourObjects;
    }

    @Override
    public Head clone() {
        return new Head((World) world.clone(), new ArrayList<>(visitedCoords), new ArrayList<>(directions));
    }
}
