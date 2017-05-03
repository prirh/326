import java.util.*;
/**
* Anagrams.java
* COSC326 Etude 7
* Finding Anagrams
*
* @author Erina Jeffery
* @author Rhianne Price
* May 2017
**/

public class Anagrams {
  /* Dictionary indexed by word length, each set of words in sorted order. */
  private static TreeMap<Integer, TreeSet<String>> dictionary;
  /* All posible anagrams in sorted order including spaces. */
  private static ArrayList<String> alphabeticalAnagrams;
  /* Possible word combinations, indexed by words in the combination. */
  private static TreeMap<Integer, ArrayList<ArrayList<Integer>>> possibleCombos;

  private static String sentenceToRearrange;
  private static int maxWords;

  /**
  * Main method reads in and cleans the sentence to rearrange and the
  * maximum number of words to use from the command line arguments. It then
  * reads in the dictionary from standard input and then organises it by
  * words length, then alphabetically. This ensures or anagrams are found
  * in the correct order.
  */
  public static void main(String[] args) {
    /* Clean input. */
    sentenceToRearrange = args[0].replaceAll("[^a-zA-Z0-9]", "");
    sentenceToRearrange = sentenceToRearrange.toLowerCase();

    maxWords = Integer.parseInt(args[1]);
    if(maxWords < 1) return;

    /* Initialise lists. */
    possibleCombos = new TreeMap<Integer, ArrayList<ArrayList<Integer>>>();
    dictionary = new TreeMap<Integer, TreeSet<String>>();
    alphabeticalAnagrams = new ArrayList<String>();

    /* Scan in and organise dictionary. */
    Scanner dictionaryScanner = new Scanner(System.in);
    while(dictionaryScanner.hasNextLine()) {
      String word = dictionaryScanner.nextLine();
      int length = word.length();
      if(dictionary.get(length) == null) {
        dictionary.put(length, new TreeSet<String>());
      }
      dictionary.get(length).add(word);
    }

    findCombos(sentenceToRearrange.length(), new ArrayList<Integer>());
    findAndPrintAnagrams();
  }

  /**
  * Recursively finds all possible ways to make a sentence of a certain
  * length with no more than the maximum number of words specified. The
  * for loop starting at n ensures our combos come out with the largest word
  * first.
  * @param n the number of letters we should use.
  * @param combo the list of word lengths we are building.
  */
  private static void findCombos(int n, ArrayList<Integer> combo) {
    if (n == 0 && combo.size() > 0 && combo.size() <= maxWords) {
      if(possibleCombos.get(combo.size()) == null) {
        possibleCombos.put(combo.size(), new ArrayList<ArrayList<Integer>>());
      }
      possibleCombos.get(combo.size()).add(combo);
      return;
    }
    for (int i = n; i > 0; i--) {
      ArrayList<Integer> a = new ArrayList<>(combo);
      if(dictionary.get(i) == null) continue;
      a.add(i);
      findCombos(n - i, a);
    }
  }
  /**
  * Outer loop for our recursive find anagrams from a combo method.
  * Iterates through all the possible word lenghth combos in order of
  * increasing number of words, then decreasing word length, and calls
  * our method to find all possible anagrams of that word combo. This
  * ensures anagrams are printed in order of increasing word count, then
  * decreasing word length.
  */
  private static void findAndPrintAnagrams() {
    Set<Integer> wordsCounts = possibleCombos.keySet();
    for(Integer count : wordsCounts) {
      for(ArrayList<Integer> combination : possibleCombos.get(count)) {
        anagramsOfShape(combination, "", sentenceToRearrange);
      }
    }
  }
  /**
  * Given a word length combo, this method recursively finds all anagrams
  * of our input sentence that conform to that sentence shape. This works by
  * taking the input bag of characters and finding everything in the dictionary
  * of the length of the first word in the combo that can be made from the bag,
  * removing the relevant letters from the bag, and then doing the same for
  * the rest of the letters in the combo, and prints the anagram if all the
  * letters in the bag are used. We have avoided duplicates by keeping track
  * of the anagrams we already have. Since the algorithm loops through the
  * dictionary, and the dictionary is in alphabetical order, the first
  * anagram we find is the one we want to keep.
  * @param combo the given sentence shape to try match.
  * @param anagram the anagram we are building, gets added to on each call.
  * @param bag the bag of characters we have to work with.
  */
  private static void anagramsOfShape(List<Integer> combo, String anagram, String bag) {
    if(combo.size() == 0 && bag.length() == 0) {
      String[] alphabeticalAnagram = anagram.trim().split(" ");
      Arrays.sort(alphabeticalAnagram);
      String alphabeticalAnagramS = Arrays.toString(alphabeticalAnagram);
      if(!alphabeticalAnagrams.contains(alphabeticalAnagramS)) {
        alphabeticalAnagrams.add(alphabeticalAnagramS);
        System.out.println(anagram.trim());
      }
      return;
    }

    if(combo.size() == 0 || bag.length() == 0) return;
    for(String word : dictionary.get(combo.get(0))){
      String newBag = bag;
      for(int i = 0; i < word.length(); i++) {
        if(newBag.contains(word.substring(i, i + 1))) {
          newBag = newBag.replaceFirst(word.substring(i, i + 1), "");
        } else {
          break;
        }
      }
      ArrayList<Integer> newCombo = new ArrayList<>(combo);
      newCombo.remove(0);
      anagramsOfShape(newCombo, anagram + " " + word, newBag);
    }
  }
}
