package etude07;

import java.util.*;

public class Anagrams {

    /* Dictionary stored in a list. */
    private static ArrayList<String> dictionary = new ArrayList<String>();

    /* Posible anagrams indexed by word count. */
    private static HashMap<Integer, ArrayList<String>> anagrams = new HashMap<Integer, ArrayList<String>>();

    private static int MAX_WORDS_TO_USE;

    static int iterationCount = 0;

    static ArrayList<String> permutations;

    public static void main(String[] args) {

        /* Clean input. */
        String sentenceToRearrange = args[0];
        sentenceToRearrange = sentenceToRearrange.replaceAll("[^a-zA-Z0-9]", "");
        String bag = sentenceToRearrange.toLowerCase();
        int bagSize = sentenceToRearrange.length();

        MAX_WORDS_TO_USE = Integer.parseInt(args[1]);

        /* Scan dictionary into an array list. */
        Scanner dictionaryScanner = new Scanner(System.in);
        while(dictionaryScanner.hasNextLine()) {
            dictionary.add(dictionaryScanner.nextLine());
        }

        findAnagrams(bag, MAX_WORDS_TO_USE);

        for(Map.Entry<Integer, ArrayList<String>> entry : anagrams.entrySet()) {
            for(String word : entry.getValue()) {
                System.out.println(word);
            }
        }
    }

    public static void findAllSubsetsOf(String bag) {

    }

    private static boolean findAnagrams(String word, int max_words) {
        System.out.println(word);
        if(word.length() == 0){
            iterationCount++;
            return true;
        }

        if(max_words == 0) return false;

        double possibleCombos = Math.pow(2, word.length());
        for(int i = (int) possibleCombos; i >= 0; i--) {
            String possible_word = "";
            for(int bit = 0; bit < word.length(); bit++) {
               if(((i >> bit) & 1) == 1) possible_word += word.charAt(bit);
           }
           addOneWordAnagramsFor(possible_word);

           for(String possiblePermutation : permutations) {
               System.out.println(possiblePermutation);
               if(dictionary.contains(possiblePermutation)) {
                   if(findAnagrams(word.replaceAll(possiblePermutation, ""), max_words - 1)) {
                       addAnagram(possiblePermutation);
                       return true;
                   }
               }
           }
        }
        return false;
    }

    private static void addOneWordAnagramsFor(String word) {
        permutations = new ArrayList<String>();
        permutation("", word);
    }

    private static void permutation(String prefix, String str) {
      int n = str.length();
      if (n == 0) {
         permutations.add(prefix);
      } else {
        for (int i = 0; i < n; i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
      }
    }

        private static void addAnagram(String anagram){
          if(anagrams.get(iterationCount) == null) {
            anagrams.put(new Integer(iterationCount), new ArrayList<String>());
          }
          anagrams.get(iterationCount).add(anagram);
        }
}
