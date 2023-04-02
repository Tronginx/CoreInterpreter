package lab3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Part 3 of Project 1 for CSE 3341. An interpreter for Core.
 *
 * @author Wayne Heym
 * @author Tron Tian
 *
 */
public final class CoreInterpreter {

    public static Scanner data;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoreInterpreter() {
    }

    /**
     * Number of expected command-line arguments.
     */
    private static final int ARG_NUMBER = 3;

    /**
     * Print flag string.
     */
    private static final String PRINT_STRING = "print";

    /**
     * Do not print flag string.
     */
    private static final String DO_NOT_PRINT_STRING = "doNotPrint";

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Student solutions are not required to check for correct command-line
         * arguments; they are only required to work properly when the proper
         * command-line arguments are supplied.
         */
        if (args.length != ARG_NUMBER) {
            System.err.println("Error: Expecting " + ARG_NUMBER
                    + " arguments: exactly 2 file arguments "
                    + "followed by a flag regarding printing,");
            System.err.println("but seeing only " + args.length + " arguments");
            return;
        }
        if ((!args[2].equals(PRINT_STRING))
                && (!args[2].equals(DO_NOT_PRINT_STRING))) {
            System.err.println(
                    "Error: Expecting the third argument to be either \""
                            + PRINT_STRING + "\" or \"" + DO_NOT_PRINT_STRING
                            + "\" but seeing \"" + args[2] + '"');
            return;
        }
        boolean shouldPrint = args[2].equals(PRINT_STRING);
        Scanner pgm;
        try {
            pgm = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening file: " + args[0]);
            return;
        }
        try {
            data = new Scanner(Paths.get(args[1]));
        } catch (IOException e) {
            System.err.println("Error opening file: " + args[1]);
            pgm.close();
            return;
        }
        Tokenizer1.set(pgm);
        Prog program = new Prog();
        try {
            program.parse();
            if (shouldPrint) {
                program.print();
                //System.out.println("Successful get in shouldPrint");
            }
          //  Id.prepareRun(data);
            program.exec();
            //System.out.println("Successful get in exec");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage());
            /*
             * The following call is used only during development. It is
             * commented out in "released" versions of the program.
             */
            //            e.printStackTrace();
        } finally {
            /*
             * Close scanners
             */
            pgm.close();
            data.close();
        }
    }

}