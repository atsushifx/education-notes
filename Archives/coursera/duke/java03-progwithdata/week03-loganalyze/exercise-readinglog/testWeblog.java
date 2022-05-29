import java.util.*;
import edu.duke.*;

/**
 * testWeblog
 *   Web Server Log test
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * 
 */
public class testWeblog
{
    private String myLogFile = "logs/weblog2_log";
    
    /**
     * exam01
     *   q.3 - 
     */
    void exam01() {
        System.out.println("\n  Week03 Test - analyze weblog");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        
        System.out.println("q.4 - unique ip");
        int ipcnt = la.countUniqueIPs();
        System.out.println("  Unique IP number: " + ipcnt);
        
        System.out.println("q.5 - unique ip on Day");
        ArrayList<String> ipList = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("  Unique IP on Sep.27 are " + ipList.size());
    
        System.out.println("q.6 - count ip in range");
        ipcnt = la.countUniqueIPsInRange(200,299);
        System.out.println("  IP in range(200-299): " + ipcnt);
          
            }
    
    /**
     * exam01
     *   q.7 - with count Visits 
     */
    void exam02() {
        System.out.println("\n  Week03 Test - analyze weblog & count visits");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        
        System.out.println("q.7 - count most visits");
        int cnt = la.mostNumberVisitsByIP(visits);
        System.out.println("  most visits by IP:　" + cnt);
        
        System.out.println("q.8 - most visits IPs");
        ArrayList<String> ipList = la.iPsMostVisits(visits);
        System.out.println("  visits by IP:　" + ipList);
    }
    
    /**
     * exam03
     *   q.7 - with count Visits 
     */
    void exam03() {
        System.out.println("\n  Week03 Test - count visits by day");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, ArrayList<String>> visitsbyDay = la.iPsForDays();
        
        System.out.println("q.9 - most visits day");
        String day = la.dayWithMostIPVisits(visitsbyDay);
        System.out.println("  most visit day is " + day);
        
        day = "Sep 30";
        System.out.println("q.10 - most visits on " + day);
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(visitsbyDay, day);
        System.out.println("  most visit IPs are　" + ipList);
        
    }
}
