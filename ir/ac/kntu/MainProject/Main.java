package ir.ac.kntu.MainProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            Scanner file = new Scanner(new File("input.txt"));
            String input = file.nextLine();
            while (file.hasNextLine()) {
                input += '\n' + file.nextLine();
            }
            CFG cfg = new CFG(input);
            
            System.out.println(cfg.toString());
            System.out.println("chomsky normal form:");
            cfg.toCNF();
            System.out.println(cfg.toString());
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}