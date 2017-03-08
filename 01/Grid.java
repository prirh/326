package etude01;
import java.util.*;
/**
 * Grid.java
 * COSC326 Etude 1
 * Ants on a Plane
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * February 2017
 **/
public class Grid {
    /**
     * grid stores characters representing states of tiles.
     */
    private HashMap<Integer, HashMap<Integer, Character>> grid;

    /**
     * initialState stores the default state of tiles in the grid.
     */
    private char initialState;

    /**
     * Constructor initialises an empty grid represented by a hashmap
     * of hashmaps, indexed by an x and y coordinate repectively.
     * @param c the default state of each tile in the grid.
     **/
    public Grid(char c){
        grid = new HashMap<Integer, HashMap<Integer, Character>>();
        initialState = c;
    }

    /**
     * Changes the state of a tile in the grid.
     * @param x the x coordinate of the tile to be changed.
     * @param y the y coordinate of the tile to be changed.
     * @param c the character representation of the state the
     *        tile is to change to
     **/
    public void setTile(int x, int y, char c) {
        grid.put(x, new HashMap<Integer, Character>());
        grid.get(x).put(y, c);
    }

    /**
     * If the tile has been visited before this method finds the tile,
     * and returns it's state. If the tile doesnt exist yet, the default state
     * is returned.
     * @param x the x coordinate of the tile to be found.
     * @param y the y coordinate of the tile to be found.
     * @return the charcter representation of the state of the tile being
     *         searched for.
     **/
    public char getTile(int x, int y) {
        if(grid.get(x) != null && grid.get(x).get(y) != null) {
            return grid.get(x).get(y);
        }
        return initialState;
    }
}
