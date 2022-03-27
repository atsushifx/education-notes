
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    /**
     * printQuakes
     *   print out Quakes list 
     */
    private void printQuakes(ArrayList<QuakeEntry> quakes, int quakeNumber) {
        for (int i=0; i<quakes.size(); i++) {
            if (quakeNumber>0 && i>quakeNumber) { break; }
            System.out.println(quakes.get(i));
        }
        if (quakeNumber > 0) {
            System.out.println("Print quake entry in position " + quakeNumber);
            System.out.println(quakes.get(quakeNumber));
        }
    }
    
    /**
     * sortWithCompareTo
     *   sort quakes by compareTo method
     */
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("\n  q.1 - sort with compareTo");
        Collections.sort(list);
        printQuakes(list, 50);
        
    }
    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    
    
    /**
     * sortByTitleAndDepth
     *   sort by Tilte, if title equals sort by depth
     */
    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("q.2 - title & depth sort");
        Collections.sort(list, new TitleAndDepthComparator());
        printQuakes(list, 50);
        
    }
    
    /**
     * sortByLastWordInTitleThenByMagnitude
     *   sort quakes by last word from title and magnitude
     *
     */
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("\n q.3 - sort by title & magnitude");
        
        TitleLastAndMagnitudeComparator cp = new TitleLastAndMagnitudeComparator();
        Collections.sort(list, cp);
        printQuakes(list, 50);
    }
}
