import java.util.*;

/**
 * Tester2
 *   test weblog analyze by visitor
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 */
public class Tester2
{
    /**
     * access log file for test
     */
    private String myLogFile = "logs/weblog3-short_log ";
  
    /**
     * testVisitsPerIP
     */
    public void testVisitsPerIP() {
        System.out.println("\n  test Visits per IP");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        System.out.println("Visit is " + visits);
    }
    
    /**
     * testMostNumbetVisits
     *   analyze log and get max number of visits
     */
    public void testMostNumberVisits() {
        System.out.println("\n  test most visits number");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        int mostNum = la.mostNumberVisitsByIP(visits);
        System.out.println("most visits is " + mostNum);
    }
    
    /**
     * testMostVisitsIP
     */
    public void testMostVisitsIP() {
        System.out.println("\n  test most visits IPs");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, Integer> visits = la.countVisitsPerIP();
        ArrayList<String> ipList = la.iPsMostVisits(visits);
        System.out.println("most visit IPs " + ipList);
    }
    
    /**
     * testIPsForDays
     */
    public void testIPsForDays() {
        System.out.println("\n  test IPS on someday");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, ArrayList<String>> daysIP = la.iPsForDays();
        System.out.println("days visit IPs\n"+daysIP);
    }
    
    /**
     * testDayWithMostIPVisits
     *   get day that most visits
     */
    public void testDayWithMostIPVisits() {
        System.out.println("\n  test day on most visit IPs");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, ArrayList<String>> daysIP = la.iPsForDays();
        String oneday = la.dayWithMostIPVisits(daysIP);
        System.out.println("most visit day is " + oneday); 
    }
    
    /**
     * testIPsWithMostVisitsOnDay
     *   
     */
    public void testIPsWithMostVisitsOnDay() {
        System.out.println("\n  test most visit IPs someday");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(myLogFile);
        HashMap<String, ArrayList<String>> daysIP = la.iPsForDays();
        
        String day = "Sep 30";
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(daysIP, day);
        System.out.println("Most visit on " + day + " is\n" + ipList);
       
    }
}

