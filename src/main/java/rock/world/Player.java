package rock.world;

import rock.world.objects.Damaging;
import rock.world.objects.InteractiveWorldObject;
import rock.world.objects.WorldObject;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private final World world;
    private Coords position;

    private final Collection<Character> keys;
    private int health;

    private int distanceTravelled;

    public Player(World world, Coords position) {
        this(world, position, new ArrayList<>(), 100, 0);
    }

    public Player(World world, Coords position, Collection<Character> keys, int health, int distanceTravelled) {
        this.world = world;
        this.position = position;
        this.keys = keys;
        this.health = health;
        this.distanceTravelled = distanceTravelled;
    }

    public World getWorld() {
        return world;
    }

    public Coords getPosition() {
        return position;
    }

    public void addKey(char letter) {
        keys.add(letter);
    }

    public boolean hasKey(char letter) {
        return keys.contains(letter);
    }

    public Collection<Character> getKeys() {
        return keys;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void damage(int damage) {
        if (damage >= health) {
            health = 0;
        } else {
            health -= damage;
        }
    }

    public void heal() {
        health = 100;
    }

    public int getHealth() {
        return health;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void move(Direction direction) {
        //get target's coords
        Coords targetCoords = position.add(direction.getCoords());

        if (world.getObject(targetCoords) == null) return;

        //check if object is interactive
        WorldObject targetObject = world.getObject(targetCoords);
        if (targetObject instanceof InteractiveWorldObject) {
            //interact with this object
            ((InteractiveWorldObject) targetObject).interact();
        }

        //check if object is walkable
        if (targetObject.isWalkable()) {
            //move to target's coords
            position = targetCoords;
            distanceTravelled++;
        }

        //if the object is damaging
        if (targetObject instanceof Damaging) {
            //get damage
            damage(((Damaging) targetObject).getDamage());
        }
    }
}