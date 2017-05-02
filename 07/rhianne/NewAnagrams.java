import java.util.*;
import java.util.regex.*;

public class NewAnagrams {

    private static TreeMap<Integer, ArrayList<String>> dictionary;
    private static int maxWordsToUse;
    private static ArrayList<ArrayList<String>> anagrams;
    private static TreeMap<Integer, ArrayList<ArrayList<Integer>>> possibleWordLengthCombos;
    private static String sentenceToRearrange;

    public static void main(String[] args) {
        /* Clean input. */
        sentenceToRearrange = args[0];
        sentenceToRearrange = sentenceToRearrange.replaceAll("[^a-zA-Z0-9]", "");
        sentenceToRearrange = sortWord(sentenceToRearrange.toLowerCase());

        maxWordsToUse = Integer.parseInt(args[1]);
        if(maxWordsToUse < 1) return;

        possibleWordLengthCombos = new TreeMap<Integer, ArrayList<ArrayList<Integer>>>();
        dictionary = new TreeMap<Integer, ArrayList<String>>();
        anagrams = new ArrayList<ArrayList<String>>();

        /* Scan in and organise dictionary. */
        Scanner dictionaryScanner = new Scanner(System.in);
        while(dictionaryScanner.hasNextLine()) {
            String word = dictionaryScanner.nextLine();
            int length = word.length();
            if(dictionary.get(length) == null) {
                dictionary.put(length, new ArrayList<String>());
            }
            dictionary.get(length).add(word);
        }
        findPossibleWordLengthCombos();
        findAnagrams();
        printAnagrams();
    }

    private static String sortWord(String word) {
        char[] sortedWord = word.toCharArray();
        Arrays.sort(sortedWord);
        return new String(sortedWord);
    }

    private static void printAnagrams() {
      for(ArrayList<String> anagram : anagrams) {
        for(String word : anagram) {
          System.out.print(word + " ");
        }
        System.out.println();
      }
    }

    private static void findPossibleWordLengthCombos() {
      findPossibleWordLengthCombos(sentenceToRearrange.length(), maxWordsToUse, new ArrayList<Integer>());
    }

    private static void findPossibleWordLengthCombos(int n, int max, ArrayList<Integer> wordsLengths) {
        if (n == 0 && wordsLengths.size() > 0) {
          if(possibleWordLengthCombos.get(wordsLengths.size()) == null) {
            possibleWordLengthCombos.put(wordsLengths.size(), new ArrayList<ArrayList<Integer>>());
          }
            possibleWordLengthCombos.get(wordsLengths.size()).add(wordsLengths);
            return;
        }
        for (int i = Math.min(max, n); i > 0; i--) {
            ArrayList<Integer> a = new ArrayList<>(wordsLengths);
            if(dictionary.get(i) == null) continue;
            a.add(i);
            findPossibleWordLengthCombos(n - i, i, a);
        }
    }

    private static void findAnagrams() {
      Set<Integer> wordsCounts = possibleWordLengthCombos.keySet();
      for(Integer count : wordsCounts) {
          for(ArrayList<Integer> combination : possibleWordLengthCombos.get(count)) {
            anagramsFromCombination(combination, new ArrayList<>(), sentenceToRearrange);
          }
      }
    }

    private static void anagramsFromCombination(ArrayList<Integer> combo, ArrayList<String> anagram, String bag){
        if (combo.size() == 0 && bag.length() == 0) {
            anagrams.add(anagram);
            return;
        }

        if(combo.size() == 0 || bag.length() == 0) return;

        for(Integer wordLength : combo) {
            for(String word : dictionary.get(wordLength)){
              String newBag = bag;
              for(int i = 0; i < word.length(); i++) {
                if(newBag.contains(word.substring(i, i + 1))) {
                  newBag = newBag.replaceFirst(word.substring(i, i + 1), "");
                } else {
                  break;
                }
              }
                ArrayList<Integer> newCombo = new ArrayList<>(combo);
                newCombo.remove(wordLength);
                ArrayList<String> newAnagram = new ArrayList<>(anagram);
                newAnagram.add(word);
                anagramsFromCombination(newCombo, newAnagram, newBag);
            }
         }
      }
   }
