/**
 * LargestQuakes
 *   found largest magnitude quakes
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * @date    2022-03-11
 */
import java.util.*;

/**
 * LargestQuakes
 *   search Largest magnitude Nth Quakes to list 
 */
public class LargestQuakes
{
    /**
     * indexOfLargest
     */
    int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIndex = -1;
        double maxMag = 0.0;
        
        for (int i=0; i<data.size(); i++) {
            QuakeEntry qe = data.get(i);
            if (maxIndex<0 || (qe.getMagnitude()>maxMag)) {
                maxIndex = i;
                maxMag = qe.getMagnitude();
            }
        }
        return maxIndex;
    }
    
    /**
     * getLargest
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        for (int i=0; i<howMany; i++) {
            int index = indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }
    
    
    /**
     * findLargestQuakes
     *   search largest magnitude Nth quakes and print these.
     */
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";  // for examination
        // String source = "data/nov20quakedatasmall.atom";  // for Assignment5
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("\n  read data for "+list.size());
        
        /*
        // test: get 1 quake from list
        int index = indexOfLargest(list);
        System.out.println("Quake[" + index + "]: " + list.get(index));
        */
        ArrayList<QuakeEntry> found = getLargest(list, 50);
        for(int i=0; i<found.size(); i++){
            QuakeEntry entry = found.get(i);
            System.out.println(entry);
        }
        System.out.println("number found: " + found.size());
    }
}
