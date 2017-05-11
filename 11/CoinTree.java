package etude11;
/**
 * CoinTree.java
 * COSC326 Etude 11
 * Heads and Tails
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * May 2017
 **/
import java.util.*;

public class CoinTree {
      private String coins;
      private String target;
      private int heads;
      private int tails;
      private char first;
      private CoinSequence root;

      public CoinTree(int heads, int tails) {
        this.heads = heads;
        this.tails = tails;
        first = tails < heads ? 'H' : 'T';
        char other = first == 'H' ? 'T' : 'H';
        target = "";
        for(int i = 0; i < heads + tails; i++) {
          if(i % 2 == 0) target += first;
          else target += other;
        }

        coins = "..";
        while(heads > 0) {
          coins += "H";
          heads--;
        }
        while(tails > 0) {
          coins += "T";
          tails--;
        }
        coins += "..";
        root = new CoinSequence(coins, 0);

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

          CoinTree coins = new CoinTree(heads, tails);

        //   System.out.println(coins.target);
          System.out.println(coins.root.sequence);
          coins.root.addChildren(coins.target);
          for(CoinSequence child : coins.root.children) {
              System.out.println(child.sequence);
          }

        //   Queue<CoinSequence> searchQ = new ArrayDeque<CoinSequence>();
        //   searchQ.add(coins.root);
        //   while(!searchQ.isEmpty()) {
        //       CoinSequence currentSequence = searchQ.poll();
        //       if(currentSequence.addChildren(coins.target) > 0) {
        //           System.out.println(currentSequence.depth + 1);
        //           return;
        //       }
        //       for(CoinSequence child : currentSequence.children) {
        //            searchQ.add(child);
        //            System.out.println(child.sequence + " " + child.depth);
        //       }
        //   }
        // }
    }

}
