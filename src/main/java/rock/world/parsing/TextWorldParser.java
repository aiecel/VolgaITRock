package rock.world.parsing;

import rock.world.Coords;
import rock.world.Player;
import rock.world.World;
import rock.world.WorldImpl;
import rock.world.objects.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextWorldParser implements WorldParser {
    @Override
    public World parse(File file) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            int height = scanner.nextInt();
            int width = scanner.nextInt();

            scanner.nextLine();

            World world = new WorldImpl(width, height);
            char character;
            String line;
            for (int y = 0; y < height; y++) {
                line = scanner.nextLine();
                for (int x = 0; x < width; x++) {
                    character = line.charAt(x * 2);
                    addToWorld(character, world, new Coords(x, y));
                }
            }
            return world;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Can't parse " + file.getName() + ": file not found");
        }
    }

    private void addToWorld(char character, World world, Coords position) {
        WorldObject objectToAdd;
        if (character == '.') {
            objectToAdd = new Floor(world, position);               //floor
        } else if (character == 'S') {
            world.setPlayer(new Player(world, position));           //player
            objectToAdd = new Floor(world, position);               //add floor to start
        } else if (character == 'X') {
            objectToAdd = new Wall(world, position);                //wall
        } else if (character == 'Q') {
            objectToAdd = new Exit(world, position);                //exit
        } else if (character == 'H') {
            objectToAdd = new HealthKit(world, position);           //health kit
        } else if (Character.isLetter(character)) {
            if (Character.isUpperCase(character)) {
                objectToAdd = new Door(world, position, Character.toLowerCase(character));              //door
            } else {
                objectToAdd = new Key(world, position, character);  //key
            }
        } else if (Character.isDigit(character)) {
            objectToAdd = new Fire(world, position, Character.getNumericValue(character) * 20); //fire
        } else {
            System.out.println("Unknown char: '" + character + "'");
            return;
        }

        world.addObject(objectToAdd);
    }
}
