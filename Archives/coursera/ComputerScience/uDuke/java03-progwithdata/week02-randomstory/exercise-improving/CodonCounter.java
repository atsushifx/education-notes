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
        dna = dna.trim();
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
        if (codon.length() != 3){ return; }
        
        if (myCodonMap.containsKey(codon)) {
            int cnt = myCodonMap.get(codon);
            myCodonMap.put(codon, cnt + 1);
        } else {
            myCodonMap.put(codon, 1);
        }
    }
    
    /**
     * getMostCommonCodon 
     */
    String getMostCommonCodon() {
        String commonCodon = "";
        int maxCnt = 0;
        
        for (String codon : myCodonMap.keySet()) {
            int cnt = myCodonMap.get(codon);
            
            if (cnt>maxCnt || maxCnt==0) {
                maxCnt = cnt;
                commonCodon = codon;
            }
        }
        return commonCodon;
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
        FileResource fr = new FileResource("data/smalldna.txt");
        String dna = fr.asString();
        
        for (int st=0; st<=2; st++) {
            System.out.println("\n  start " + st);
            buildCodonMap(st, dna);
            String codon = getMostCommonCodon();
            int cnt = myCodonMap.get(codon);
            System.out.println("MostCommon: " + codon + " " + cnt);
            printCodonCounts(0, 15);
        } 
    }
}
