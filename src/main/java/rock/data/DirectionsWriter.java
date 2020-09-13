package rock.data;

import rock.world.Direction;

import java.io.IOException;
import java.util.List;

public interface DirectionsWriter {
    void write(List<Direction> directions) throws IOException;
}
