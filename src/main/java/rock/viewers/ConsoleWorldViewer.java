package rock.viewers;

import rock.world.World;
import rock.world.objects.*;

public class ConsoleWorldViewer implements WorldViewer {
    private World world;

    @Override
    public void initView(World world) {
        this.world = world;
        System.out.println("Console view");
    }

    @Override
    public void updateView() {
        System.out.println("Health: " + world.getPlayer().getHealth());

        StringBuilder keysBuilder = new StringBuilder().append("Keys: ");
        world.getPlayer().getKeys().forEach(key -> keysBuilder.append(key).append(" "));
        System.out.println(keysBuilder);

        System.out.println("Step: " + world.getPlayer().getDistanceTravelled());
        printWorld();
        System.out.println();
    }

    @Override
    public void showMessage(String message) {
        System.out.println("MESSAGE: " + message);
    }

    @Override
    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    private void printWorld() {
        char[][] worldChars = new char[world.getWidth()][world.getHeight()];

        WorldObject object;
        char objectChar;
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                object = world.getObject(x, y);
                if (object instanceof Floor) {
                    objectChar = '.';
                } else if (object instanceof Door) {
                    objectChar = Character.toUpperCase(((Door) object).getLetter());
                } else if (object instanceof Key) {
                    objectChar = ((Key) object).getLetter();
                } else if (object instanceof Exit) {
                    objectChar = 'Q';
                } else if (object instanceof HealthKit) {
                    objectChar = 'H';
                } else if (object instanceof Fire) {
                    objectChar = 'F';
                } else {
                    objectChar = 'X';
                }
                worldChars[x][y] = objectChar;
            }
        }

        //player
        worldChars[world.getPlayer().getPosition().getX()][world.getPlayer().getPosition().getY()] = 'R';

        //print the world
        for (int j = 0; j < worldChars[0].length; j++) {
            for (int i = 0; i < worldChars.length; i++) {
                System.out.print(worldChars[i][j] + " ");
            }
            System.out.println();
        }
    }
}
