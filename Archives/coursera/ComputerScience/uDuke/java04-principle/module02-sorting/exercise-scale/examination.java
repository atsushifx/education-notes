import java.util.*;

/**
 * examination
 *   output sorted quakes for examination
 * 
 * @author  Atsushi Furukawa
 * @version 1.0.0
 */
public class examination
{
    /**
     * printQuakes
     *   print out Quakes list 
     */
    private void printQuakes(ArrayList<QuakeEntry> quakes, int quakeNumber) {
        
        for (int i=0; i<quakes.size(); i++) {
            if (quakeNumber>0 && i>quakeNumber) { break; }
            if (i >= (quakeNumber-5)) {
                System.out.println(quakes.get(i));
            }
        }
        
        if (quakeNumber > 0) {
            System.out.println("Print quake entry in position " + quakeNumber);
            System.out.println(quakes.get(quakeNumber));
        }
    }
    
    
    /**
     * exam3
     *   print and sort
     */
    public void exam3() {
        System.out.println("\n  --- exam q.6 - ");
        EarthQuakeParser parser = new EarthQuakeParser();
        String source;
        ArrayList<QuakeEntry> list;
        
        // q.6
        System.out.println("\n q.6 - Collection sort compareTo");
        source = "data/earthQuakeDataWeekDec6sample1.atom";
        list  = parser.read(source);
        Collections.sort(list);
        printQuakes(list, 600);
        
        // q.7
        System.out.println("\n q.7 - sort with compareTitle");
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        printQuakes(list, 500);
        
        // q.8
        System.out.println("\n q.8 - sort by title & magnitude");
        source = "data/earthQuakeDataWeekDec6sample2.atom";
        list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        printQuakes(list, 500);
        
    }
}
