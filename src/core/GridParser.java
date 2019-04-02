package core;

import GridObjects.*;
import GridObjects.Snake.Body;
import GridObjects.Snake.GreenSnake;
import GridObjects.Snake.YellowSnake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class GridParser {

    private GridObject[][] Grid;
    private HashMap<Integer, Body[]> snakeMap = new HashMap<Integer, Body[]>();
    private int height;
    private int width;

    final int MAX_SNAKE_LENGTH = 15;

    public GridParser (String fileName) throws FileNotFoundException {

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

            if (skipLine) {
                continue;
            }

            if (cells[0].equals("Height")) {
                height = parseInt(cells[1]);
            }

            if (cells[0].equals("Width")) {
                width = parseInt(cells[1]);
                this.Grid = new GridObject[height][width];
            }

            if (cells[0].equals("Level")) {
                levelConstruction = true;
                continue;
            }

            if (levelConstruction) {
                for (int i = 0; i < cells.length; i++) {

                    String[] data = cells[i].split("\\.", -1);
                    if (data[0].equals("")) {
                        this.Grid[levelLine][i] = new Empty();

                    }
                    else if (data[0].equals("X")) {
                        this.Grid[levelLine][i] = new Wall();
                    }
                    else if (data[0].equals("-")) {
                        this.Grid[levelLine][i] = new Exit();
                    }
                    else if (data[0].equals("+")) {
                        Body temp = new Body(
                                GridObjectType.GREEN_SNAKE,
                                parseInt(data[1]),
                                0,
                                false);
                        this.Grid[levelLine][i] = temp;

                        try{
                            this.snakeMap.get(0)[parseInt(data[1])] = temp;
                        }
                        catch (NullPointerException e) {
                            this.snakeMap.put(0, new Body[MAX_SNAKE_LENGTH]);
                            this.snakeMap.get(0)[parseInt(data[1])] = temp;
                        }



                    }
                    else if (data[0].equals("#")) {
                        Body temp = new Body(
                                GridObjectType.GREEN_SNAKE,
                                parseInt(data[1]),
                                0,
                                true);
                        temp.setCoords(levelLine, i);
                        this.Grid[levelLine][i] = temp;
                        this.snakeMap.get(0)[parseInt(data[1])] = temp;

                        try{
                            this.snakeMap.get(0)[parseInt(data[1])] = temp;
                        }
                        catch (NullPointerException e) {
                            this.snakeMap.put(0, new Body[MAX_SNAKE_LENGTH]);
                            this.snakeMap.get(0)[parseInt(data[1])] = temp;
                        }
                    }
                }
                levelLine++;
            }
        }

        consolidate();
    }

    private void consolidate() {
        //Snake consolidation

        for(int i = 0; i < snakeMap.size(); i++) {
            if (i == 0) {
                int[] coords = snakeMap.get(i)[0].getCoords();
                Grid[coords[0]][coords[1]] = new GreenSnake(snakeMap.get(i));
            }
            else {
                int[] coords = snakeMap.get(i)[0].getCoords();
                Grid[coords[0]][coords[1]] = new YellowSnake(snakeMap.get(i));
            }

        }


    }

    public GridObject[][] getGrid() {
        return Grid;
    }
}
