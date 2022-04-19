import edu.duke.*;
import java.util.*;

/**
 * examWebsiteVisits
 *   examination of analyze accesslog for counting visits
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 *
 */
public class examWebsiteVisits
{
    private String LogFile = "logs/weblog1_log";

    /**
     * exam q1 - 
     *   counting ips
     */
    void exam01() {
        System.out.println("\n  exam q.1 - analyze log & count visits.");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(LogFile);
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        
        System.out.println("q.1 most visits");
        int n = la.mostNumberVisitsByIP(visits);
        System.out.println("most visits is " + n);
        
        System.out.println("q.2 most visits IPs");
        ArrayList<String> ipList = la.iPsMostVisits(visits);
        System.out.println("most visits IP is \n" + ipList);

    }
    
    /**
     * exam q3 - 
     *   counting ip by days
     */
    void exam02() {
        System.out.println("\n  exam q.1 - analyze log & count visits.");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(LogFile);
        HashMap<String, ArrayList<String>> visitsbyDay = la.iPsForDays();
        
        System.out.println("q.3 days most visits");
        String day = la.dayWithMostIPVisits(visitsbyDay);
        System.out.println("Most visit day is " + day);
        
        System.out.println("q.4 most visits on day");
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(visitsbyDay, "Mar 17");
        System.out.println("Most visit IPs is " + ipList);
    }
}


