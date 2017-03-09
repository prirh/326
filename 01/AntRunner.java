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

  private static boolean firstAnt = true;
  /**
   * Entry point of the program. Scans DNALines and number of runs from STDIN,
   * For each scenario
   *
   * @param args command line arguments not used.
   **/
  public static void main(String[] args) {
      Scanner inputScanner = new Scanner(System.in);
      StringBuilder input = new StringBuilder();
      while(inputScanner.hasNextLine()) {
          input.append(inputScanner.nextLine()).append("\n");
      }
      Scanner scenarioScanner = new Scanner(input.toString().trim());
      StringBuilder scenario = new StringBuilder();

      while(scenarioScanner.hasNextLine()) {
          String line = scenarioScanner.nextLine();
          if(line.equals("")){
              runAnt(scenario.toString().trim());
              scenario = new StringBuilder();
              firstAnt = false;
          } else if(!line.startsWith("#")) {
          scenario.append(line).append("\n");
      }
    }
      runAnt(scenario.toString().trim());
  }


  /**
   * All the necessary operations for "running" the ant. For a scenarion, it
   * creates and runs a new ant, echos the input, and prints out the final
    * position of that ant.
   * @param scenario command line arguments not used.
   **/
  private static void runAnt(String scenario) {
      if (!firstAnt) {
          System.out.println();
      }
      System.out.println(scenario);
      Ant ant = new Ant(scenario);
      ant.run();
      System.out.println(ant.getPosition());
  }
}
