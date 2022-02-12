import edu.duke.*;

/**
 * WordLength
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
public class WordLength
{
    /**
     * chop
     *   cut char from word if char is not letter
     * 
     */
    private String chop(String word) {
        if (!Character.isLetter(word.charAt(0))) {
            word = word.substring(1);
        }
        
        int l = word.length();
        if (!Character.isLetter(word.charAt(l-1))) {
            word = word.substring(0, l - 1);
        }
        return word;
    }
    
    /**
     * countWordLength
     *   count word length index
     */
    void countWordLength(FileResource fr, int counts[]) {
        int maxLength = -1;
        for (String word : fr.words()) {
            word = chop(word);
            int len = word.length();
            counts[len] += 1;
            
            if (len >=maxLength) {
                maxLength = len;
            }
        }
    }
    
    /**
     * indexOfMax
     *   search most counted index
     */
    int indexOfMax(int[] counts) {
        int idx = -1;
        int max = -1;
        for (int i=0; i<counts.length; i++) {
            if ((idx<0) || (counts[i]>max)) {
                idx = i;
                max = counts[i];
            }
        }
        return idx;
    }
    
    
    
    /**
     * testCountWordLength
     */
    public void testCountWordLength() {
        System.out.println("\n  1. count word length.");
        
        FileResource fr = new FileResource("data/smallHamlet.txt");
        int[] counts = new int[31];
        
        countWordLength(fr, counts);
        
        for (int i=1; i<counts.length; i++) {
            System.out.println("len:" + i + " = " + counts[i]);
        }
        
        int idxMax = indexOfMax(counts);
        System.out.println("Index of max:"+idxMax+", counts:" + counts[idxMax]);
    }
    
}
