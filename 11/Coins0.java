/**
 * Coins0.java
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
public class Coins0 {

  private String coins;
  private String target;
  private int heads;
  private int tails;
  private char first;

  public Coins0(int heads, int tails) {
    this.heads = heads;
    this.tails = tails;
    first = tails < heads ? 'H' : 'T';
    char other = first == 'H' ? 'T' : 'H';
    target = String(first);

    for(int i = 1; i < heads + tails; i++) {
      if(i % 2 == 0) target += other;
      else target += first;
    }

    coins = "..";

    while(heads > 0) {
      coins += "H";
    }
    while(tails > 0) {
      coins += "T";
      tails--;
    }
    coins += ".."

    System.out.println(target);
    System.out.println(coins);
  }

  public int rearrange() {
    if(heads != tails) {}
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

      Coins0 coins = new Coins0(heads, tails);
      if(show) System.out.println(coins);
      System.out.println(coins.solve(show) + " moves");
    }
  }
