package etude11;
import java.util.*;

public class CoinSequence {
    String sequence;
    List<CoinSequence> children;
    int depth;

    public CoinSequence(String sequence, int depth) {
        this.depth = depth;
        this.sequence = sequence;
        children = new ArrayList<CoinSequence>();
    }

    public int checkChildren(String target) {
        if(children.contains(target)) return depth;
        return 0;
    }

    public int addChildren(String target) {
        ArrayList<Integer> pairs = new ArrayList<Integer>();
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        for(int i = 0;  i < sequence.length() - 1; i++) {
            if(sequence.charAt(i) == '.' && sequence.charAt(i + 1) == '.') {
                gaps.add(i);
            } else if(sequence.charAt(i) == '-' && sequence.charAt(i + 1) == '-') {
                gaps.add(0, i);
            } else if((sequence.charAt(i) == 'H' || sequence.charAt(i) == 'T') && (sequence.charAt(i + 1) == 'H' || sequence.charAt(i + 1) == 'T')) {
                pairs.add(i);
            }
        }

        for(Integer pairIndex : pairs) {
            for(Integer gapIndex : gaps) {
                String childSequence = "";
                char[] s = sequence.toCharArray();

                /* Fill gap with pair. */
                s[gapIndex] = sequence.charAt(pairIndex);
                s[gapIndex + 1] = sequence.charAt(pairIndex + 1);

                s[pairIndex] = '-';
                s[pairIndex + 1] = '-';

                if(pairIndex == 2 || pairIndex == sequence.length() - 4) {
                    s[gapIndex] = '.';
                    s[gapIndex + 1] = '.';
                    childSequence += new String(s);
                } else {
                    if(gapIndex == 0) {
                        childSequence += "..";
                    }
                    childSequence += new String(s);

                    if(gapIndex == sequence.length() - 2) {
                        childSequence += "..";
                    }
                }
                if(new String(s).equals(target)) return depth + 1;
                children.add(new CoinSequence(childSequence, depth + 1));
            }
        }
        return 0;
    }
}
