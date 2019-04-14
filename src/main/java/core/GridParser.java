package core;

import GridObjects.*;
import GridObjects.Block.Block;
import GridObjects.Block.BlockGroup;
import GridObjects.Snake.Body;
import GridObjects.Snake.GreenSnake;
import GridObjects.Snake.Snake;
import GridObjects.Snake.YellowSnake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class GridParser {

    private Grid Grid;
    private int height;
    private int width;

    private final int MAX_SNAKE_LENGTH = 20;

    public GridParser (String fileName) throws FileNotFoundException {


        this.Grid = new Grid();
        File file = new File(fileName);
        Scanner sc;
        try{
            sc = new Scanner(file);

        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found!");
        }


        String newLine;
        boolean skipLine = false;
        boolean levelConstruction = false;
        int levelLine = 0;
        while (sc.hasNextLine()) {

            newLine = sc.nextLine();

            newLine = newLine.replaceAll("\\s","");
            String[] cells = newLine.split(",",-1);

            if (cells[0].length() == 0) {
                continue;
            }

            if (cells[0].charAt(0) == '=') {
                skipLine = !skipLine;
                continue;
            }

            if (skipLine != false) {
                continue;
            }

            if (cells[0].equals("Height")) {
                height = parseInt(cells[1]);
            }

            if (cells[0].equals("Width")) {
                width = parseInt(cells[1]);
                this.Grid.grid = new GridObject[height][width];
            }

            if (cells[0].equals("Moves")) {
                this.Grid.TOTAL_MOVES = parseInt(cells[1]);

            }

            if (cells[0].equals("Level")) {
                levelConstruction = true;
                continue;
            }

            if (levelConstruction) {
                for (int i = 0; i < cells.length; i++) {

                    String[] data = cells[i].split("\\.", -1);
                    if (data[0].equals("")) {
                        this.Grid.grid[levelLine][i] = new Empty();
                    }
                    else if (data[0].equals("X")) {
                        this.Grid.grid[levelLine][i] = new Wall();
                    }
                    else if (data[0].equals("-")) {
                        this.Grid.grid[levelLine][i] = new Exit();
                    }
                    else if (data[0].equals("^")) {
                        this.Grid.grid[levelLine][i] = new Apple();
                    }
                    else if (data[0].equals("v")) {
                        this.Grid.grid[levelLine][i] = new Mushroom();
                    }
                    else if (data[0].charAt(0) == '[') {

                        int block_id = Character.getNumericValue(data[0].charAt(1));


                        Block temp = new Block(block_id);

                        temp.setCoords(levelLine, i);
                        this.Grid.grid[levelLine][i] = temp;

                        try{
                            BlockGroup currentGroup = this.Grid.blockMap.get(block_id);
                            currentGroup.blockArray[currentGroup.blockCount] = temp;
                            currentGroup.blockCount++;

                        }
                        catch (NullPointerException e) {
                            this.Grid.blockMap.put(block_id, new BlockGroup(
                                    block_id,
                                    new Block[MAX_SNAKE_LENGTH]));
                            this.Grid.blockMap.get(block_id).blockArray[0] = temp;
                            this.Grid.blockMap.get(block_id).blockCount++;
                        }



                    }
                    else if (data[0].equals("#") || data[0].equals("+") ){
                        Body temp = new Body(
                                GridObjectType.GREEN_SNAKE,
                                parseInt(data[1]),
                                0);

                        if(data[0].equals("#")) { temp.setHead(true); }

                        temp.setCoords(levelLine, i);
                        this.Grid.grid[levelLine][i] = temp;

                        try{
                            this.Grid.snakeMap.get(0).bodyArray[parseInt(data[1])] = temp;
                        }
                        catch (NullPointerException e) {
                            this.Grid.snakeMap.put(0, new GreenSnake(
                                    new Body[MAX_SNAKE_LENGTH]));
                            this.Grid.snakeMap.get(0).bodyArray[parseInt(data[1])] = temp;
                        }
                    }
                    else if (data[0].equals("*") || data[0].equals("@") ){
                        Body temp = new Body(
                                GridObjectType.YELLOW_SNAKE,
                                parseInt(data[1]),
                                parseInt(data[2]));

                        if(data[0].equals("@")) { temp.setHead(true); }

                        temp.setCoords(levelLine, i);
                        this.Grid.grid[levelLine][i] = temp;

                        try{
                            this.Grid.snakeMap.get(parseInt(data[2])).bodyArray[parseInt(data[1])] = temp;
                        }
                        catch (NullPointerException e) {
                            this.Grid.snakeMap.put(parseInt(data[2]), new YellowSnake(
                                    new Body[MAX_SNAKE_LENGTH]));
                            this.Grid.snakeMap.get(parseInt(data[2])).bodyArray[parseInt(data[1])] = temp;
                        }
                    }
                }
                levelLine++;
            }
        }

        Grid.consolidate();
    }

    public Grid getGrid() {
        return Grid;
    }

}
