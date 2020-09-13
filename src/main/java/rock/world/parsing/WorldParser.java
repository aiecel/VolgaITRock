package rock.world.parsing;

import rock.world.World;

import java.io.File;
import java.io.FileNotFoundException;

public interface WorldParser {
    World parse(File file) throws FileNotFoundException;

    default World parse(String path) throws FileNotFoundException {
        return parse(new File(path));
    }
}
