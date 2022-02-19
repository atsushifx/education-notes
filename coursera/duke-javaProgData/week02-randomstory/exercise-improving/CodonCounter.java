import java.util.*;
import edu.duke.*;
/**
 * CodonCounter
 *   create codon map anf count codons
 * 
 * @author  Furukawa Atsushi <atsushifx@gmail.com>
 * @version 1.0.0
 */
public class CodonCounter
{
    private HashMap<String, Integer> myCodonMap;
    
    /**
     * constructor
     */
    public CodonCounter() {
        myCodonMap = new HashMap<String, Integer>();
        
    }
    
    /**
     * buildCodonMap
     *   build codon map from DNA strand
     *   
     */
    void buildCodonMap(int start, String dna) {
        myCodonMap.clear();
        int len = dna.length();
        for (int i = start; i<len; i+=3) {
            if (i+3 <= len) {
                String codon=dna.substring(i, i+3);
                addCodon(codon);
            }
        }
    }
    
    /**
     * addCodon
     *   add codon to codon map
     */
    private void addCodon(String codon) {
        if (myCodonMap.containsKey(codon)) {
            int cnt = myCodonMap.get(codon);
            myCodonMap.put(codon, cnt + 1);
        } else {
            myCodonMap.put(codon, 1);
        }
    }
    
    
    /**
     *  printCodonCounts
     */
    void printCodonCounts(int start, int end) {
        for (String codon : myCodonMap.keySet()) {
            int cnt = myCodonMap.get(codon);
            if (start<=cnt && (cnt<=end || end<=0)) {
                System.out.println(codon + "\t" + cnt);
            }
        }
    }
    
    
    
    /**
     * tester
     *   test build codon map
     */
    public void tester() {
        System.out.println("\n 　test codon map");
        String dna = "CGTTCAAGTTCAA";
        
        System.out.println("start 0");
        buildCodonMap(0, dna);
        printCodonCounts(0, 15);
        
        System.out.println("start 1");
        buildCodonMap(1, dna);
        printCodonCounts(0, 15);
        
        System.out.println("start 2");
        buildCodonMap(2, dna);
        printCodonCounts(0, 15);
    }
}
