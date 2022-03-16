import java.util.*;
import edu.duke.*;
/**
 * EarthQuakeClient2
 *   quakes filter called by interface
 */
public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * filter
     *   filter quakes called with quakesfilter 
     */
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
    /**
     * read quakes & filter them, and output filtered quakes
     * 
     */
    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("\nread data for "+list.size()+" quakes.\n");
        /*
        Filter f = new MagnitudeFilter(4.00, 5.00);
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        
        Filter fdepth = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> m8  = filter(m7, fdepth);
        */
        Filter fdistance= new DistanceFilter(new Location(35.42, 139.43), 10000 * 1000);
        ArrayList<QuakeEntry> q2  = filter(list, fdistance);
        
        Filter fphrase = new PhraseFilter("Japan", "end");
        ArrayList<QuakeEntry> q3 = filter(q2, fphrase);
        outputQuakes(q3, fphrase.getName());
    }
    
    /**
     * readQuakes
     *   read quakes data from file / web 
     */
    private ArrayList<QuakeEntry> readQuakes(String quakeType) {
        String source;
        if (quakeType == "production") {
            source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        } else if (quakeType == "test") {
            source = "data/nov20quakedata.atom";
        } else {
            source = "data/nov20quakedatasmall.atom";
        }
        EarthQuakeParser parser = new EarthQuakeParser(); 
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        return list;
    }
    
    /**
     * outputQuakes
     *   output quakes by filter them
     */
    private void outputQuakes(ArrayList<QuakeEntry> quakes, String filter) {
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.println(filter);
        System.out.println("found " + quakes.size() + " quakes.\n");
    }
    
    
    /**
     * testMatchAllFilter
     *   test MatchAllFilter 
     */
    public void testMatchAllFilter() {
        ArrayList<QuakeEntry> list  = readQuakes("development");         
        System.out.println("\n  read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("a", "any"));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        outputQuakes(quakes, maf.getName());
    }
    
    /**
     * testMatchAllFilter
     *   test MatchAllFilter 
     */
    public void testMatchAllFilter2() {
        ArrayList<QuakeEntry> list  = readQuakes("development");         
        System.out.println("\n  read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000*1000));
        maf.addFilter(new PhraseFilter("Ca", "any"));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        outputQuakes(quakes, maf.getName());
    }
    
    /**
     * createCSV
     *   read quakes and output quakes with CSV format
     *   
     */
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    /**
     * dumpCSV
     *   output quakes data by CSV format
     *   
     */
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
