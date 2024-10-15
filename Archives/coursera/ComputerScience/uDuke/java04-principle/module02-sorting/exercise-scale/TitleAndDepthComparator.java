import java.util.*;

/**
 * TitleAndDepthComparator
 *   compare by Title, if equal compare by Depth 
 *
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int diff = qe1.getInfo().compareTo(qe2.getInfo());
        if (diff != 0) {
            return diff;
        }
        return Double.compare(qe1.getDepth(), qe2.getDepth());
    }
}
