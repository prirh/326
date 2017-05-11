package etude11;
import java.util.*;

public class CoinSequence {
    String sequence;
    List<CoinSequence> children;
    int depth;
    int gaps;
    int runs;
    CoinSequence parent;

    public CoinSequence(String sequence, int depth, int gaps, CoinSequence parent) {
        this.depth = depth;
        this.sequence = sequence;
        this.gaps = gaps;
        this.children = new ArrayList<CoinSequence>();
        this.parent = parent;

        // for(int i = 0;  i < sequence.length() - 1; i++) {
        //     if(sequence.charAt(i) == '.' && sequence.charAt(i + 1) == '.') {
        //         this.gaps++;
        //     } else if(sequence.charAt(i) == '-' && sequence.charAt(i + 1) == '-') {
        //         this.gaps++;
        //     }
        // }
    }

    public int checkChildren(String target) {
        if(children.contains(target)) return depth;
        return 0;
    }

    public CoinSequence addChildren(String target) {
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
                int childGaps = this.gaps;

                String end = "..";
                String start = "..";


                /* Fill gap with pair. */
                s[gapIndex] = sequence.charAt(pairIndex);
                s[gapIndex + 1] = sequence.charAt(pairIndex + 1);

                s[pairIndex] = '-';
                s[pairIndex + 1] = '-';
                if(gapIndex == 0) {
                    childSequence += start;
                    childGaps++;
                }

                if(pairIndex == 2 && gapIndex != 0) {
                    s[pairIndex] = '.';
                    s[pairIndex + 1] = '.';
                    childSequence += new String(s, 2, sequence.length() - 2);
                    start = "";
                } else if(pairIndex == sequence.length() - 4 && gapIndex != sequence.length() - 2) {
                    s[pairIndex] = '.';
                    s[pairIndex + 1] = '.';
                    childSequence += new String(s, 0, sequence.length() - 2);
                    end = "";
                } else {
                    childSequence += new String(s);
                }

                if(gapIndex == sequence.length() - 2) {
                    childSequence += end;
                    childGaps++;
                }

                CoinSequence child = new CoinSequence(childSequence, depth + 1, childGaps, this);
                if(childSequence.equals(target)) return child;
                if(childGaps <= 3) {
                    if(!children.isEmpty() && child.gaps < children.get(0).gaps) {
                        children.add(0, child);
                    } else {
                        children.add(child);
                    }
                }
            }
        }
        return null;
    }
}
