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
            System.out.println(input);
            //CFG cfg = new CFG(input);
            //cfg.toCNF();
            // TODO enable line below after compeleting funtion
            //System.out.println(cfg.toString());
            file = new Scanner(System.in);
            for (String string : file.nextLine().split("->")) {
                System.out.println(string.trim());
            }
            file = null;
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}