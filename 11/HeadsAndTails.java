/**
 * HeadsAndTails.java
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
public class HeadsAndTails{
  /**
   * Reads the number of heads and tails from the command line, and prints ou
   * the final solution.
   * @param args first argument specifies the number of heads, second for tails.
   */
  public static void main(String[] args) {
    int numHeads = Integer.parseInt(args[0]);
    int numTails = Integer.parseInt(args[1]);
    ArrayList<String> coins = new ArrayList<String>();
    String firstCoin;
    int steps = 0;

    if(Math.abs(numHeads - numTails) > 1) {
      System.out.println("Impossible");
      return;
    }

    /* Set up the initial coin sequence. */
    if(numTails > numHeads) {
      firstCoin = "T";
    } else {
      firstCoin = "H";
    }

    while(numHeads > 0) {
      coins.add("H");
      numHeads--;
    }

    while(numTails > 0) {
      coins.add("T");
      numTails--;
    }

    printCoins(coins);

    /* Get the appropriate coin type to the start of the sequence. */
    while(!firstCoin.equals(coins.get(0))) {
      fixStart(coins);
      printCoins(coins);
      steps++;
    }

    /* Keep swapping coins until finished. */
    while(!swapCoins(coins)){
      printCoins(coins);
      steps++;
    }

    System.out.println(steps + " steps");
  }

    /**
     * This method moves the coin type of greater number to the start of the
     * sequence of coins.
     * @param coins is an ArrayList of "H" and "T" strings.
     */
    public static void fixStart(ArrayList<String> coins){
      for(int i = 0; i < coins.size(); i++){
        if(coins.get(i).equals("T")){
          Collections.swap(coins, i, i - 1);
          return;
        }
      }
    }

  /**
   * This method finds the leftmost point where coins can be swapped to fix a
   * double-up and then swaps the coins at that point.
   * @param coins is an ArrayList of "H" and "T" strings.
   */
  public static boolean swapCoins(ArrayList<String> coins){
    for(int i = 1; i < coins.size() - 1; i++){
      if(coins.get(i).equals(coins.get(i - 1)) && !coins.get(i).equals(coins.get(i + 1))){
        Collections.swap(coins, i, i + 1);
        return false;
      }
    }
    return true;
  }


  /**
   * This method prints out a coin sequence represented by an ArrayList.
   * @param coins is an ArrayList of "H" and "T" strings.
   */
  public static void printCoins(ArrayList<String> coins){
    String line = "";
    for(int i = 0; i < coins.size(); i++){
        line = line + coins.get(i) + " ";
      }
      System.out.println(line);
    }
  }
