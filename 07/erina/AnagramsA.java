import java.util.*;

public class AnagramsA {

    private static String inputAnagram = "";
    private static int inputAnagramLength;
    private static char[] inputChar;
    private static Map<String, String> dictionary = new HashMap<String, String>();
    private static List<List<String>> anagrams = new ArrayList<List<String>>();

    public static void main(String[] args) {
        if(args.length == 2){ 
            for(int i = 0; i < args[0].length(); i++){
                if(Character.isLetter(args[0].charAt(i))){
                    inputAnagram += args[0].charAt(i);
                }
            }
            inputAnagram = inputAnagram.toLowerCase();
            inputChar = inputAnagram.toCharArray();
            inputAnagramLength = inputAnagram.length();
            Arrays.sort(inputChar);
            Scanner scan = new Scanner(System.in);
            while(scan.hasNextLine()){
                storeDictionary(scan);
            }
            //  System.out.println("HELLO");

            permutation(inputAnagram);
            for(int i = 0; i < anagrams.size(); i++){
                if(anagrams.get(i).size() > 1){
                    System.out.println(anagrams.get(i));
                }
                for(int j = 0; j < anagrams.get(i).size(); j++){
                    if(anagrams.get(i).get(j).length() == inputAnagramLength){
                        System.out.println(anagrams.get(i));
                    }
                }
            }
        }
    }

    private static void storeDictionary(Scanner sc){
        String word = sc.nextLine();
        if(dictionary.get(word) == null){
            dictionary.put(word, word);
        }
    }

    private static void stringPermutations(String inputAnagram){
        String sub;
        for(int i = 0; i < inputAnagram.length(); i++){
            for(int j = 1; j <= inputAnagram.length()-i; j++){
                sub = inputAnagram.substring(i, i+j);
                checkDict(sub);
            }
        }
    }

    public static void permutation(String str){
        permutation("", str);
    }

    private static void permutation(String prefix, String str){
        int n = str.length();
        if(n == 0) {
            stringPermutations(prefix);
        }
        else {
            for(int i = 0; i < n; i++){
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }

    private static void checkDict(String word){
        if(dictionary.containsKey(word)){
            if(anagrams.isEmpty()){
                anagrams.add(new ArrayList<String>());
                anagrams.get(0).add(word);
                dictionary.remove(word);
                return;
            }
            String temp = "";
            for(int i = 0; i < anagrams.size(); i++){
                for(int j = 0; j < anagrams.get(i).size(); j++){
                    temp = anagrams.get(i).get(j);
                    if(isAnagram(word, anagrams.get(i).get(j))){
                        anagrams.get(i).add(word);
                        dictionary.remove(word);
                        return;
                    }
                }
            }
            if(!isAnagram(word, temp)){
                anagrams.add(new ArrayList<String>());
                anagrams.get(anagrams.size()-1).add(word);
                dictionary.remove(word);
            }
        }
    }

    private static boolean isAnagram(String str1, String str2){
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        char[] str1str2 = concat(str1Array, str2Array);
        Arrays.sort(str1str2);
        if(Arrays.equals(str1str2, inputChar)){
            return true;
        }
        return false;
    }

    public static char[] concat(char[] a, char[] b) {
        int aLen = a.length;
        int bLen = b.length;
        char[] c = new char[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}
