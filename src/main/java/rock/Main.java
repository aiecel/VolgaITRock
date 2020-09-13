package rock;

import rock.data.DirectionsWriter;
import rock.data.TextDirectionsWriter;
import rock.pathfinding.Pathfinder;
import rock.viewers.ConsoleWorldViewer;
import rock.viewers.gui.GuiWorldViewer;
import rock.viewers.WorldViewer;
import rock.world.Direction;
import rock.world.World;
import rock.world.parsing.TextWorldParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int DELAY_MS = 500;
    public static final String DEFAULT_MAP_FOLDER = "maps/";
    public static final String DEFAULT_MAP_FILE = "map.txt";
    public static final String DEFAULT_OUTPUT_FILE = "moves.txt";

    private static World world;

    public static void main(String[] args) {
        WorldViewer viewer;
        DirectionsWriter writer = new TextDirectionsWriter(new File(DEFAULT_OUTPUT_FILE));

        //create viewer
        if (Arrays.asList(args).contains("-console")) {
            viewer = new ConsoleWorldViewer();
        } else {
            viewer = new GuiWorldViewer();
        }

        //parse world
        try {
            String worldPath = Arrays.stream(args).filter(s -> !s.startsWith("-")).findFirst().orElse(DEFAULT_MAP_FILE);
            world = new TextWorldParser().parse(DEFAULT_MAP_FOLDER + worldPath);
        } catch (FileNotFoundException e) {
            viewer.showError(e.getMessage());
            return;
        }

        viewer.initView(world);

        //find the path
        Pathfinder pathfinder = new Pathfinder();
        List<Direction> path = pathfinder.findPath(world);

        if (path.size() == 0) {
            viewer.showError("Can't find safe way out :(");
            return;
        }

        //save the path
        try {
            writer.write(path);
            viewer.showMessage("Path saved!");
        } catch (IOException e) {
            viewer.showError("Can't save path: " + e.getMessage());
        }

        //view the path
        for (Direction direction : path) {
            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            world.getPlayer().move(direction);
            viewer.updateView();
        }

        viewer.showMessage("Rock is out!");
    }
}
