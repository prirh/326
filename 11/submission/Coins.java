/**
 * Coins.java
 * COSC326 Etude 11
 * Heads and Tails
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * May 2017
 **/

import java.util.*;

/**
 * Given the number of heads followed by the number of tails, finds a way
 * to move the coins to make an alternating pattern.
 */
public class Coins {

  private ArrayList<Character> coins;
  private int heads;
  private int tails;

  public Coins(int heads, int tails) {
    this.heads = heads;
    this.tails = tails;
    coins = new ArrayList<Character>();

    while(heads > 0) {
      coins.add('H');
      heads--;
    }
    while(tails > 0) {
      coins.add('T');
      tails--;
    }
  }

  /**
   * Finds the moves required to make the heads and tails alternate using the
   * allowed moves. Does this by breaking up the double ups and closing the gaps
   * behind them.
   * @param show if set, method will print steps.
   * @return the number of moves to arrange the coins correctly.
   */
  public int solve(boolean show) {
    int moves = 0;
    char first = 'H';
    if(tails > heads) {
      first = 'T';
      moves += shiftBlockToFront(heads + 1, coins.size(), show);
    }

    /* look for HHT (or TTH) pattern and move the HT (or TH) to the start, and
       close the gap. */
    for(int i = 0; i < coins.size() - 2; i++) {
      if(coins.get(i) == first && coins.get(i + 1) == first && coins.get(i + 2) != first) {
        moves += shiftBlockToFront(i + 1, i + 3, show);
      }
    }
    return moves;
  }

  /**
   * Moves the specified sublist of coins to the start of the sequence of coins
   * and counts the number of allowed moved that that operation takes.
   * @param start the index of the first character in the block.
   * @param end the index of the last character to include + 1.
   * @param show if set, method will print steps.
   * @return the number of moves required to shift the block to the start.
   */
  private int shiftBlockToFront(int start, int end, boolean show) {
    List<Character> block = new ArrayList<Character>(coins.subList(start, end));
    for(int i = end - 1; i >= start; i--) {
      coins.remove(i);
    }

    String startBlock = "";
    if(show) {
      startBlock = toString(new ArrayList<Character>(coins.subList(0, start)));
      startBlock += "  " + toString(new ArrayList<Character>(coins.subList(start, coins.size())));
    }

    coins.addAll(0, block);

    if(end == coins.size() || start == 0) {
      if(show) System.out.println(toString());
      return 1; // Did't have to close gap, one move used.
    }
    if(show) {
      System.out.println(toString(block) + startBlock);
      System.out.println(toString());
    }
    return 2; // Shifted coins and closed gap, two moves used.
  }

  /**
   * @return a string of H and T representing the state of the coins
   */
  public String toString(){
    String line = "";
    for(int i = 0; i < coins.size(); i++){
        line = line + coins.get(i) + " ";
      }
      return line;
    }
    public String toString(List<Character> list){
      String line = "";
      for(int i = 0; i < list.size(); i++){
          line = line + list.get(i) + " ";
        }
        return line;
      }

    /**
     * Reads the number of heads and tails from the command line, and prints out
     * the number of steps to alternate reach the final solution.
     * @param args first argument specifies the number of heads, second for tails.
     *             if given a third argument s, the steps of the solution will
                   we printed.
     */
    public static void main(String[] args) {
      int heads = Integer.parseInt(args[0]);
      int tails = Integer.parseInt(args[1]);
      boolean show = false;
      if(args.length == 3) show = "s".equals(args[2]);
      int steps = 0;

      if(Math.abs(heads - tails) > 1) {
        System.out.println("Impossible");
        return;
      }

      if(heads == 1 && tails == 1) {
        System.out.println("0 moves");
        return;
      }

      Coins coins = new Coins(heads, tails);
      if(show) System.out.println(coins);
      System.out.println(coins.solve(show) + " moves");
    }
  }
