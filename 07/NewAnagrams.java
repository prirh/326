import java.util.*;

public class NewAnagrams {

    private static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    private static int maxWordsToUse;
    private static ArrayList<String> anagrams = new ArrayList<String>();

    public static void main(String[] args) {
        /* Clean input. */
        String sentenceToRearrange = args[0];
        sentenceToRearrange = sentenceToRearrange.replaceAll("[^a-zA-Z0-9]", "");
        String bag = sentenceToRearrange.toLowerCase();

        maxWordsToUse = Integer.parseInt(args[1]);

        /* Scan in and organise dictionary. */
        Scanner dictionaryScanner = new Scanner(System.in);
        while(dictionaryScanner.hasNextLine()) {
            String word = dictionaryScanner.nextLine();
            String key = sortWord(word);
            if(dictionary.get(key) == null) {
                dictionary.put(key, new ArrayList<String>());
            }
            dictionary.get(key).add(word);
        }

        findAnagrams(bag, maxWordsToUse);

        // for(Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
        //     System.out.println(entry);
        // }
    }

    private static String sortWord(String word) {
        char[] sortedWord = word.toCharArray();
        Arrays.sort(sortedWord);
        return new String(sortedWord);
    }

    private static boolean findAnagrams(String word, int maxWords) {
        if(word.length() == 0) {
            return true;
        }
        if(maxWords == 0) return false;

        double possibleCombos = Math.pow(2, word.length());
        for(int i = (int) possibleCombos; i >= 0; i--) {
            String possibleWord = "";
            for(int bit = 0; bit < word.length(); bit++) {
               if(((i >> bit) & 1) == 1) possibleWord += word.charAt(bit);
           }
           if(dictionary.containsKey(sortWord(possibleWord))) {
            for(String word1 : dictionary.get(sortWord(possibleWord))) {
               if(findAnagrams(word1.replaceAll(possibleWord, ""), maxWords - 1)) {
                  System.out.print(word1 + " ");
                       return true;
                   }
               }
           }
       }
       return false;
   }
   }
