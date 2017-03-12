package etude01;
import java.util.*;
/**
 * Ant.java
 * COSC326 Etude 1
 * Ants on a Plane
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * February 2017
 **/
public class Ant {
    /**
     * Constants for indexing by compass direction.
     **/
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    /**
     * A grid for the ant to walk on.
     **/
    private Grid grid;

    /**
     * A map of possible states of the grid to instructions for this ant.
     **/
    private HashMap<Character, DNALine> dna;

    /**
     * Total number of steps this ant should complete.
     **/
    private int finalSteps;

    /**
     * The position of the ant.
     */
    private int x, y;

    /**
     * Constructor initialises an ant by scanning the given DNA input, and
     * creating a grid for the ant to live on. DNA is indexed by the
     * possible states of the grid.
     * @param inputDNA string describing the ant's instructions
     **/
    public Ant(String inputDNA) {
        x = 0;
        y = 0;
        grid = new Grid(inputDNA.charAt(0));
        dna = new HashMap<Character, DNALine>();

        /* Number of steps read from last line of input. */
        String lastLine = inputDNA.substring(inputDNA.lastIndexOf("\n") + 1);
        finalSteps = Integer.parseInt(lastLine);

        /* Rest of the input assumed to be lines of DNA. */
        String dnaLines = inputDNA.substring(0, inputDNA.lastIndexOf("\n"));
        Scanner dnaScanner = new Scanner(dnaLines);

        /* Add each line to this ants DNA. */
        while(dnaScanner.hasNextLine()){
            addDNALine(dnaScanner.nextLine());
        }
    }

    /**
     * Creates a new DNALine instance, and stores it in the ants DNA,
     * which is indexed by the possible states of the grid.
     * @param dnaString The String to add to this ants DNA
     **/
    private void addDNALine(String dnaString) {
        dna.put(dnaString.charAt(0), new DNALine(dnaString));
    }

    /**
     * Initialises a new grid space, and completes the given number of steps
     * by updating the ants position and the state on the grid depending on
     * this ants DNA.
     **/
    public void run(){
        /* Ant starts at (0, 0) from the south. */
        int lastDir = NORTH;
        char currentState;
        char nextDir;
        char nextState;
        for(int stepsDone = 0; stepsDone < finalSteps; stepsDone++) {
            currentState = grid.getTile(x, y);
            /* Find the next direction and next state in the ants DNA
             * by the current state, and latest direction. */
            nextDir = dna.get(currentState).nextDir.charAt(lastDir);
            nextState = dna.get(currentState).nextState.charAt(lastDir);

            /* Update the ants position and the current tile's state
             * accordingly. */
            switch(nextDir) {
                case 'N' : grid.setTile(x, y++, nextState);
                lastDir = NORTH;
                break;
                case 'E' : grid.setTile(x++, y, nextState);
                lastDir = EAST;
                break;
                case 'S' : grid.setTile(x, y--, nextState);
                lastDir = SOUTH;
                break;
                case 'W' : grid.setTile(x--, y, nextState);;
                lastDir = WEST;
            }
        }
    }

    /**
     * Gets this ants current position and formats the position nicely.
     * @return an (x, y) description of the ants position.
     **/
    public String getPosition(){
        return (new StringBuilder("# ").append(x).append(" ").append(y))
            .toString();
    }

    private class DNALine {
        /**
         * Strings describing the next drection for the ant to move in, and the
         * next state to change a tile to, depending on it's last direction.
         */
        public String nextDir, nextState;

        /**
         * Constructor takes a DNA string and splits it into two parts.
         * One part determines the next direction to take, and the second
         * determines the state to change the next tile to.
         * @param dnaString string describing the ant's instructions.
         **/
        public DNALine(String dnaString) {
            nextDir = dnaString.substring(2, 6);
            nextState = dnaString.substring(7, 11);
        }
    }
}
