package rock.world;

import java.util.HashSet;
import java.util.Set;

public enum Direction {
    U(0, -1),
    D(0, 1),
    L(-1, 0),
    R(1, 0);

    private final int xMovement;
    private final int yMovement;

    Direction(int xMovement, int yMovement) {
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public Coords getCoords() {
        return new Coords(xMovement, yMovement);
    }

    public static Set<Direction> directionSet() {
        Set<Direction> directionSet = new HashSet<>();

        directionSet.add(U);
        directionSet.add(D);
        directionSet.add(L);
        directionSet.add(R);

        return directionSet;
    }
}
