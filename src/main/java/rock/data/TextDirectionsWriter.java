package rock.data;

import rock.world.Direction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextDirectionsWriter implements DirectionsWriter {
    private final File file;

    public TextDirectionsWriter(File file) {
        this.file = file;
    }

    @Override
    public void write(List<Direction> directions) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        //how many steps
        bufferedWriter.write(String.valueOf(directions.size()));

        bufferedWriter.newLine();

        //steps itself
        for (Direction direction : directions) {
            bufferedWriter.write(direction.toString());
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
