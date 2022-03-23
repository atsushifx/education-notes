
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    /**
     * getLargestDepth
     *   get shallowest(=larget depth) Quake from Quakes
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int max= from;
        for (int i=from+1; i<quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(max).getDepth()) {
                max = i;
            }
        }
        return max;
    }
    
    /**
     * swapQuake
     *   swap QuakeEntry in Quakes
     */
    private void swapQuakes(ArrayList<QuakeEntry> quakes, int i1, int i2) {
        QuakeEntry q1 = quakes.get(i1);
        QuakeEntry q2 = quakes.get(i2);
        quakes.set(i1, q2);
        quakes.set(i2, q1);
    }
    
    /**
     * sortByDepth
     *   sort Quakes by depth
     */
    public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size(); i++) {
            int l = getLargestDepth(in, i);
            swapQuakes(in, i, l); 
        }
        return in;
    }
    
    /**
     * onePassBubbleSort
     *   b  ubble sort one pass with quakes
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i=0; i<quakeData.size()-numSorted-1; i++) {
            int j = i + 1;
            if (quakeData.get(i).getMagnitude() > quakeData.get(j).getMagnitude()) {
                swapQuakes(quakeData, i, j);
            }
        }
    }
    
    /**
     * 
     */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size(); i++) {
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass " + i);
            printQuakes(in);
        }
    }
    
    /**
     * printQuakes
     *   print quakes n pass
     */
    private void printQuakes(ArrayList<QuakeEntry> quakes) {
        for (QuakeEntry q: quakes) {
            System.out.println(q);
        }
    }
    
    /**
     * checkInSortedOrder
     *   check if quakes is already sorted
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=0; i<quakes.size(); i++) {
            for (int j=i+1; j<quakes.size(); j++) {
                if (quakes.get(i).getMagnitude() > quakes.get(j).getMagnitude()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * sortByMagnitudeWithBubbleSortWithCheck
     *   sort quakes with Bubble Sort, but if sorted already then break
     */
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int i;
        for (i=0; i<in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            onePassBubbleSort(in, i);
        }
        System.out.println(i + " passes needed");
    }
    
    /**
     * sortByMagnitudeWithCheck 
     *   sort quakes with selection sort, but if already sorted then break
     */
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {   
        int i=0;
        for (i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            
            int minIdx = getSmallestMagnitude(in,i);
            swapQuakes(in, i, minIdx);
        }
        System.out.println(i + " passes needed.");
    }
    
    
    
    /**
     * testSort
     *   test sort quakes
     */
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source ="../data/earthquakeDataSampleSix1.atom"; 
        String source ="../data/earthquakeDataSampleSix2.atom"; 
        
        ArrayList<QuakeEntry> list  = parser.read(source);  

        System.out.println("\n  -- sort by bubble sort");
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitudeWithCheck(list);
        System.out.println("\n sorted");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
