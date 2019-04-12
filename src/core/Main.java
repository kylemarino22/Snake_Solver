package core;

import java.io.FileNotFoundException;

public class Main{

    public static void main(String[] args) {

        final String fileName = "resources/level51.txt";

        GridParser gp;
        try {
             gp = new GridParser(fileName);
             PrintGrid.printGrid(gp.getGrid().grid, true);
             Solver s = new Solver(gp.getGrid());

        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }

    }
}
