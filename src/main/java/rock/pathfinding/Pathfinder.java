package rock.pathfinding;

import rock.world.Direction;
import rock.world.World;
import rock.world.objects.*;

import java.util.*;
import java.util.List;

public class Pathfinder {
    public List<Direction> findPath(World world) {
        World copyWorld = (World) world.clone();

        PriorityQueue<Head> headsQueue = new PriorityQueue<>(1, Comparator.comparingInt(h -> h.getWorld().getPlayer().getDistanceTravelled()));
        headsQueue.add(new Head(copyWorld));

        Head currentHead;
        List<Direction> availableDirections = new ArrayList<>();
        while (!headsQueue.isEmpty()) {
            currentHead = headsQueue.poll();

            //if the player is still alive on this head
            if (currentHead.getWorld().getPlayer().isAlive()) {
                //maybe we're on exit?
                if (currentHead.getWorld().getObject(currentHead.getWorld().getPlayer().getPosition()) instanceof Exit) {
                    return currentHead.getDirections();
                }

                //if we're not at the exit find directions to go
                availableDirections.clear();

                //get neighbors for the head
                Map<Direction, WorldObject> headNeighbors = currentHead.getNeighbourObjects();

                for (Direction direction : headNeighbors.keySet()) {
                    WorldObject object = headNeighbors.get(direction);

                    //we need only not visited objects
                    if (!currentHead.getVisitedCoords().contains(object.getPosition())) {
                        //if the object is walkable or interactive
                        if (object.isWalkable() || object instanceof InteractiveWorldObject) {
                            //add this direction to available directions
                            availableDirections.add(direction);
                        }
                    }
                }

                if (availableDirections.size() > 0) {
                    //if there's more than 1 direction to go
                    for (int i = 1; i < availableDirections.size(); i++) {
                        //clone current head
                        Head newHead = currentHead.clone();

                        //move the player
                        newHead.movePlayer(availableDirections.get(i));

                        //add new head to queue
                        headsQueue.add(newHead);
                    }

                    //for the last direction just move the player
                    currentHead.movePlayer(availableDirections.get(0));
                    //and add current head to queue
                    headsQueue.add(currentHead);
                }
            }
        }
        return new ArrayList<>();
    }
}
