/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */
import java.util.*;

/**
 * ClosestQuakes
 *   search quakes near by location
 */
public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for (int j=0; j<howMany; j++) {
            QuakeEntry qe = getClosestQuake(quakeData, current, ret);
            ret.add(qe);
        }
        return ret;
    }
    
    /**
     * getClosestQuake
     *   get closest Quake from list
     */
    private QuakeEntry getClosestQuake(ArrayList<QuakeEntry> quakeList, Location current, ArrayList<QuakeEntry> exList) {
        int minIndex = -1;
        double minDist = 0;
        for (int i=0; i<quakeList.size(); i++) {
            QuakeEntry qe = quakeList.get(i); 
            double dist = current.distanceTo(qe.getLocation());
            if (!exList.contains(qe)) {
                if (minIndex<0 || (dist<minDist)) {
                    minIndex = i;
                    minDist = dist;
                }
            }
        }
        return quakeList.get(minIndex);
    }
    
    /**
     * findClosestQuakes
     *   test search quakes near by location
     */
    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";  // for Assignment4
        
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("\n  read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta, 3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
