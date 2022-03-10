import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (from.distanceTo(qe.getLocation()) < distMax) {
                answer.add(qe);    
            }

        }
        return answer;
    }
    
    /**
     * quakes filter by depth
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (minDepth<qe.getDepth() && qe.getDepth()<maxDepth) {
                answer.add(qe);    
            }
        }
        return answer;
    }
    
    
    /**
     * quakes filter by phrase
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String indicate) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            int idx = info.indexOf(where);
            if ((indicate=="start" && idx==0)  || 
                    (indicate=="end" && idx==info.length()-where.length()) ||
                    (indicate=="any" && idx>=0)) {
                answer.add(qe);
            }
            
        }
        return answer;
    }
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom"; // for test this method
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigQuakeList = filterByMagnitude(list, 5.0);
        printQuakes(bigQuakeList);
    }
    
    
    /**
     * output quakes
     */
    void printQuakes(ArrayList<QuakeEntry> quakes) {
        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        } 
        System.out.println("Found " + quakes.size() + " quakes that match that criteria");
    }
    
    /**
     * search earthquakes near by city
     * 
     */
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom"; // for test this method
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("\n read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        //System.out.println("\n  quakes near by Durham, NC");
        
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        System.out.println("quakes near by Bridgeport, NC");
        
        
        ArrayList<QuakeEntry> quakesNearby = filterByDistanceFrom(list, 1000* 1000, city);
        for (QuakeEntry qe: quakesNearby) {
            float dist = city.distanceTo(qe.getLocation());
            System.out.println(dist / 1000 +"\t"+qe.getInfo());
        }
        System.out.println("Found " + quakesNearby.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    /**
     * quakesOfDepth
     *   filter earthquakes by quake depth
     */
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom"; // for test this method
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("\n read data for "+list.size()+" quakes");
        double minDepth = -10000.0;
        double maxDepth = -5000.0;
        
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> quakes = filterByDepth(list, minDepth, maxDepth);
        printQuakes(quakes);
    }
    
    
    /**
     * quakesByPhrase
     */
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom"; // for test this method
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("\n read data for "+list.size()+" quakes");
        
        System.out.println("Find quakes by phrase 'California' at end.");
        ArrayList<QuakeEntry> quakes = filterByPhrase(list, "California", "end");
        printQuakes(quakes);
    }
}
