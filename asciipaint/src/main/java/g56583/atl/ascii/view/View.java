package g56583.atl.ascii.view;

import java.util.List;
import java.util.Scanner;

import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.model.Shape;

/**
 * The View class handles the user interface and interaction for the AsciiPaint application.
 * It provides methods to display messages, collect user input, and show the ASCII drawing.
 */
public class View {
    String ANSI_RED = "\u001B[31m";
    String ANSI_RESET = "\u001B[0m";
    Scanner in = new Scanner(System.in);

    /**
     * Constructs a new View instance.
     * The constructor is empty because there are no specific initializations or resources
     * that need to be set up when creating a View object. The Scanner for user input is
     * initialized here and can be used throughout the class.
     */
    public View() {
    }

    /**
     * Displays the initial welcome message for the AsciiPaint application.
     */
    public void initialize() {
        System.out.println("Welcome To AsciiPaint v1!");
        System.out.println("-------------------------\n");
    }

    /**
     * Prompts the user with a message and collects their input.
     *
     * @param message The message to display as a prompt.
     * @return The user's input as a String.
     */
    public String askCommand(String message) {
        System.out.println(message);
        return in.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Displays the available commands and their descriptions.
     */
    public void showCommand() {
        System.out.println("Write 'add circle line column radius color' for adding a circle at position (line, column) with radius and color (A-Z).");
        System.out.println("Write 'add rectangle line column width height color' for adding a rectangle at position (line, column) with width, height, and color (A-Z).");
        System.out.println("Write 'add square line column size color' for adding a square at position (line, column) with size and color (A-Z).");
        System.out.println("Write 'add line x1 y1 x2 y2 color' for adding a line from point (x1, y1) to point (x2, y2) with color (A-Z).");
        System.out.println("Write 'show' to display your drawing.");
        System.out.println("Write 'list' to see the list the list of forms on your drawing");
        System.out.println("Write 'move position line column' to move the form at the position in the list to the given  line and column");
        System.out.println("Write ´remove index´ to remove the shape at the given index");
        System.out.println("Write 'color position color' to change the color of the form at the position in the list with the new color");
        System.out.println("Write 'group index1 index2 ...' to group shapes with the specified indices.");
        System.out.println("Write 'ungroup index' to ungroup the composite shape at the specified index.");
        System.out.println("Write 'undo' to undo the last command");
        System.out.println("Write 'redo' to redo the last undone command");
        System.out.println("Write 'help' to view the command list.");
        System.out.println("Write 'create line column' to create a new illustration with dimensions line and column.");
        System.out.println("Write 'exit' to exit the program.");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Displays the list of added forms in the AsciiPaint drawing.
     *
     * @param paint The AsciiPaint instance containing the drawing.
     */
    public void displayList(AsciiPaint paint) {
        String shapesInfo = paint.getShapesList();
        System.out.println(shapesInfo);
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Displays information about the dimensions of the current drawing.
     *
     * @param paint The AsciiPaint instance containing the drawing.
     */
    public void displayInformation(AsciiPaint paint) {
        System.out.println(paint.getDrawingDimensions());
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Displays a success message after adding a form.
     */
    public void success() {
        System.out.println("Command executed with success");
    }

    /**
     * Displays the ASCII representation of the current drawing.
     *
     * @param paint The AsciiPaint instance containing the drawing.
     */
    public void displayDraw(AsciiPaint paint) {
        System.out.println(paint.asAscii());
    }

    /**
     * Displays a goodbye message when exiting the application.
     */
    public void displayGoodbye() {
        System.out.println("\nSee you next time!");
    }


}