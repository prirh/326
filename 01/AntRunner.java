package etude01;
import java.util.*;
/**
 * AntRunner.java
 * COSC326 Etude 1
 * Ants on a Plane
 *
 * @author Erina Jeffery
 * @author Rhianne Price
 * February 2017
 **/
public class AntRunner {
  /**
   * Entry point of the program. Scans DNALines and number of runs from STDIN,
   * For each scenario creates and runs a new ant, echos the input, and prints
   * out the final position of that ant.
   *
   * @param args command line arguments not used.
   **/
  public static void main(String[] args) {
    Scanner scenarioScanner = new Scanner(System.in).useDelimiter("\n\n");
    boolean firstLine = true;
    while(scenarioScanner.hasNext()) {
      String scenario = scenarioScanner.next().trim();
      scenario = scenario.replaceAll("(?m)^#.*", "").trim();
      if (firstLine) {
          firstLine = false;
      } else {
          System.out.println();
      }

      System.out.println(scenario);
      Ant ant = new Ant(scenario);
      ant.run();
      System.out.println(ant.getPosition());
    }
  }
}
