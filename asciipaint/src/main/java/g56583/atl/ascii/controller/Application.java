package g56583.atl.ascii.controller;

import g56583.atl.ascii.DesignPattern.Command.*;
import g56583.atl.ascii.model.AsciiPaint;
import g56583.atl.ascii.view.View;

import java.util.Arrays;
import java.util.Stack;

/**
 * The main controller class for the AsciiPaint application.
 * It manages user input, commands, and interactions with the model and view.
 */
public class Application {
    private AsciiPaint paint;
    private final View view;
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Constructs an Application instance with the specified view.
     *
     * @param view The view to be associated with the controller. Must not be null.
     * @throws NullPointerException if the view parameter is null.
     */
    public Application(View view) {
        if (view == null) {
            throw new NullPointerException("The view cannot be null.");
        }
        this.view = view;
    }

    /**
     * Main control loop of the AsciiPaint application.
     * It listens for user commands and processes them accordingly.
     */
    public void controller() {
        boolean exit = false;
        view.showCommand();
        view.displayInformation(paint);
        while (!exit) {
            String command = view.askCommand("Enter a command").toLowerCase();
            String[] elements = command.split(" ");

            switch (elements[0]) {
                case "add" -> handleAddCommand(elements);
                case "show" -> view.displayDraw(paint);
                case "help" -> view.showCommand();
                case "exit" -> {
                    exit = true;
                    view.displayGoodbye();
                }
                case "list" -> view.displayList(paint);
                case "color" -> handleChangeColorCommand(elements);
                case "create" -> handleCreateCommand(elements);
                case "move" -> handleMoveCommand(elements);
                case "remove" -> handleRemoveCommand(elements);
                case "group" -> handleGroupCommand(elements);
                case "ungroup" -> handleUngroupCommand(elements);
                case "undo" -> {
                    if (!undoStack.isEmpty()) {
                        Command com = undoStack.pop();
                        com.undo();
                        redoStack.push(com);
                    }
                }
                case "redo" -> {
                    if (!redoStack.isEmpty()) {
                        Command com = redoStack.pop();
                        com.redo();
                        undoStack.push(com);
                    }
                }
                default -> view.displayError("Invalid command. Type 'help' for instructions.");
            }
        }
    }

    /**
     * Handles the 'group' command by grouping specified shapes.
     * The command groups multiple shapes into a single composite shape.
     *
     * @param elements An array of strings representing the command elements.
     *                 The first element is the command name, followed by indices of shapes to be grouped.
     */
    private void handleGroupCommand(String[] elements) {
        try {
            if (elements.length < 2) {
                throw new IllegalArgumentException("Invalid command format. Use 'group index'");
            }
            Group groupCommand = new Group(paint, Arrays.copyOfRange(elements, 1, elements.length));
            groupCommand.execute();
            undoStack.push(groupCommand);
            redoStack.clear();
            view.success();
        } catch (NumberFormatException e) {
            view.displayError("Invalid group index format. Use 'group index'");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }

    }

    /**
     * Handles the 'ungroup' command to disassemble a composite shape back into its individual shapes.
     *
     * @param elements An array of strings representing the command elements,
     *                 where the second element is the index of the group to ungroup.
     */
    private void handleUngroupCommand(String[] elements) {
        if (elements.length != 2) {
            view.displayError("Invalid command format. Use 'ungroup groupIndex'");
            return;
        }

        try {
            int groupIndex = Integer.parseInt(elements[1]);
            Ungroup ungroupCommand = new Ungroup(paint, groupIndex);
            ungroupCommand.execute();
            undoStack.push(ungroupCommand);
            redoStack.clear();
            view.success();
        } catch (NumberFormatException e) {
            view.displayError("Invalid group index format.");
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Initializes the application by setting up the view and prompting the user
     * for the dimensions of the drawing canvas.
     */
    public void start() {
        view.initialize();
        boolean start = false;
        while (!start) {
            String answer = view.askCommand("Give the dimensions of your drawing: (For example: 50 50 or 'default')");
            answer = answer.toLowerCase();
            if (answer.trim().equalsIgnoreCase("default")) {
                setPaint(new AsciiPaint());
                start = true;
            } else {
                String[] elements = answer.split(" ");
                if (elements.length != 2) {
                    view.displayError("Error: Please enter two space-separated values for width and height.");
                } else {
                    try {
                        int width = Integer.parseInt(elements[0]);
                        int height = Integer.parseInt(elements[1]);
                        if (width <= 0 || height <= 0) {
                            view.displayError("Error: Width and height must be positive values.");
                        } else {
                            setPaint(new AsciiPaint(width, height));
                            start = true;
                        }
                    } catch (NumberFormatException e) {
                        view.displayError("Error: Invalid input. Please enter numeric values for width and height.");
                    } catch (Exception e) {
                        view.displayError(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Sets the AsciiPaint instance to be used by the controller.
     *
     * @param paint The AsciiPaint instance to be set.
     */
    public void setPaint(AsciiPaint paint) {
        this.paint = paint;
    }

    /**
     * Handles the "color" command by changing the color of a shape in the AsciiPaint model.
     *
     * @param elements An array of command elements.
     */
    private void handleChangeColorCommand(String[] elements) {
        if (elements.length != 3) {
            view.displayError("Invalid command format. Use 'color index newColor'");
            return;
        }
        try {
            Color changeColorCommand = new Color(paint, elements);
            changeColorCommand.execute();
            undoStack.push(changeColorCommand);
            redoStack.clear();
            view.success();
        } catch (RuntimeException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Handles the "create" command by creating a new AsciiPaint instance with the specified dimensions.
     *
     * @param elements An array of command elements.
     */
    private void handleCreateCommand(String[] elements) {
        if (elements.length != 3 || !elements[1].matches("\\d+") || !elements[2].matches("\\d+")) {
            view.displayError("Invalid command format. Use 'create width height'");
            return;
        }

        try {
            int width = Integer.parseInt(elements[1]);
            int height = Integer.parseInt(elements[2]);
            Create createCommand = new Create(paint, width, height);
            createCommand.execute();
            undoStack.push(createCommand);
            redoStack.clear();

            view.success();
        } catch (NumberFormatException e) {
            view.displayError("Width and height must be numeric values.");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Handles the "move" command by moving a shape in the AsciiPaint model.
     *
     * @param elements An array of command elements.
     */
    private void handleMoveCommand(String[] elements) {
        if (elements.length != 4) {
            view.displayError("Invalid command format. Use 'move index dx dy'");
            return;
        }

        try {
            int shapeIndex = Integer.parseInt(elements[1]);
            double dx = Double.parseDouble(elements[2]);
            double dy = Double.parseDouble(elements[3]);

            Move moveCommand = new Move(paint, shapeIndex, dx, dy);
            moveCommand.execute();
            undoStack.push(moveCommand);
            redoStack.clear();

            view.success();
        } catch (NumberFormatException e) {
            view.displayError("Invalid command format. Use 'move index dx dy'");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Handles the 'remove' command to delete a shape from the drawing.
     *
     * @param elements An array of strings representing the command elements.
     *                 The second element should be the index of the shape to remove.
     */
    private void handleRemoveCommand(String[] elements) {
        if (elements.length != 2) {
            view.displayError("Invalid command format. Use 'remove index'");
            return;
        }
        try {
            int shapeIndex = Integer.parseInt(elements[1]);
            Remove removeCommand = new Remove(paint, shapeIndex);
            removeCommand.execute();
            undoStack.push(removeCommand);
            redoStack.clear();

            view.success();
        } catch (NumberFormatException e) {
            view.displayError("Invalid command format. Use 'remove index'");
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }


    /**
     * The main method to start the AsciiPaint application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        View view = new View();
        Application controller = new Application(view);
        controller.start();
        controller.controller();
    }

    /**
     * Handles the "add" command by adding a shape to the AsciiPaint model.
     *
     * @param elements An array of command elements.
     */
    private void handleAddCommand(String[] elements) {
        if (!isValidAddCommand(elements)) {
            view.displayError("Invalid command format.");
            return;
        }
        try {
            Add addCommand = new Add(paint, elements);
            addCommand.execute();
            undoStack.push(addCommand);
            redoStack.clear();
            view.success();
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Validates the 'add' command input to ensure it conforms to the expected format and parameters.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the command format is valid, false otherwise.
     */
    private boolean isValidAddCommand(String[] elements) {
        if (elements.length < 3) {
            view.displayError("Not enough parameters.");
            return false;
        }

        String shapeType = elements[1];
        switch (shapeType) {
            case "circle" -> {
                return validateCircleCommand(elements);
            }
            case "rectangle" -> {
                return validateRectangleCommand(elements);
            }
            case "line" -> {
                return validateLineCommand(elements);
            }
            case "square" -> {
                return validateSquareCommand(elements);
            }
            default -> {
                view.displayError("Unknown shape type.");
                return false;
            }
        }
    }

    /**
     * Validates the 'circle' shape command input for correct format and numerical parameters.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the command format and parameters are valid, false otherwise.
     */
    private boolean validateCircleCommand(String[] elements) {
        if (elements.length != 6) {
            view.displayError("Invalid command format. Use 'add circle x y radius color'");
            return false;
        }
        return checkNumeric(elements, 2, 3, 4) && validateColorAndBounds(elements);
    }

    /**
     * Validates the 'rectangle' shape command input for correct format and numerical parameters.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the command format and parameters are valid, false otherwise.
     */
    private boolean validateRectangleCommand(String[] elements) {
        if (elements.length != 7) {
            view.displayError("Invalid command format. Use 'add rectangle x y width height color'");
            return false;
        }
        return checkNumeric(elements, 2, 3, 4, 5) && validateColorAndBounds(elements);
    }

    /**
     * Validates the 'line' shape command input for correct format and numerical parameters.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the command format and parameters are valid, false otherwise.
     */
    private boolean validateLineCommand(String[] elements) {
        if (elements.length != 7) {
            view.displayError("Invalid command format. Use 'add line x1 y1 x2 y2 color'");
            return false;
        }
        return checkNumeric(elements, 2, 3, 4, 5) && validateColorAndBounds(elements);
    }

    /**
     * Validates the 'square' shape command input for correct format and numerical parameters.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the command format and parameters are valid, false otherwise.
     */
    private boolean validateSquareCommand(String[] elements) {
        if (elements.length != 6) {
            view.displayError("Invalid command format. Use 'add square x y side color'");
            return false;
        }
        return checkNumeric(elements, 2, 3, 4) && validateColorAndBounds(elements);
    }

    /**
     * Checks if the specified elements at given indices are numeric.
     *
     * @param elements An array of strings representing potential numeric values.
     * @param indices  An array of indices indicating which elements to check.
     * @return true if all specified elements are numeric, false otherwise.
     */
    private boolean checkNumeric(String[] elements, int... indices) {
        try {
            for (int index : indices) {
                Integer.parseInt(elements[index]);
            }
            return true;
        } catch (NumberFormatException e) {
            view.displayError("Integer values are required for indices.");
            return false;
        }
    }

    /**
     * Validates color and bounds for shapes.
     *
     * @param elements An array of strings representing the command elements.
     * @return true if the color is valid and within drawing bounds, false otherwise.
     */
    private boolean validateColorAndBounds(String[] elements) {
        char color = elements[elements.length - 1].toUpperCase().charAt(0);
        int x = Integer.parseInt(elements[2]);
        int y = Integer.parseInt(elements[3]);
        double[] values = Arrays.stream(elements, 4, elements.length - 1)
                .mapToDouble(Double::parseDouble)
                .toArray();

        return isValidColor(color);
    }


    private boolean isValidColor(char color) {
        return (color >= 'A' && color <= 'Z') || (color >= 'a' && color <= 'z');
    }
}
