package core;

import java.io.FileNotFoundException;

public class Main{

    public static void main(String[] args) {

        final String fileName = "resources/input.csv";

        GridParser gp;
        try {
             gp = new GridParser(fileName);
             PrintGrid.PrintGrid(gp.getGrid().grid);
             Solver s = new Solver(gp.getGrid());

        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }

    }
}
