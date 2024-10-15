import java.util.*;

/**
 * TitleLastAndMagnitudeComparator
 *   get last word from title and sort list by it, 
 *   if it's same sort by magnitude
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> { 
    /**
     * getLastWord
     *   QuakeEntryから、Titleの最後の文字列(=地域)を取り出す。
     */
    private String getLastWord(String title) {
        String[] words = title.split(",");
        return words[words.length-1].trim();
    }
    
    /**
     * compare
     *   地震情報をtitle(info)とMagnitudeで比較
     */
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String title1 = getLastWord(qe1.getInfo());
        String title2 = getLastWord(qe2.getInfo());
        
        int diff = title1.compareTo(title2);
        if (diff != 0) {
            return diff;
        }
        diff = Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        if (diff != 0) {
            return diff;
        }
        return Double.compare(qe1.getDepth(), qe2.getDepth());
    }
}


