import edu.duke.*;
import java.util.*;

/**
 * examWeek03
 *  examination of accesslog
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 *
 */
public class examWeek03
{
    private String LogFile = "logs/weblog1_log";
    /**
     * test
     */
    public void examQ2() {
        System.out.println("\n  exam q.2 - access log with　status");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(LogFile);
        
        la.printAllHigherThanNum(400);
    }
    
    /**
     * test
     */
    public void examQ3() {
        System.out.println("\n  exam q.3 - log on Day");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(LogFile);
        
        String oneday = "Mar 17";
        ArrayList<String> ipList = la.uniqueIPVisitsOnDay(oneday);
        System.out.println(oneday+"'s  visits is " + ipList.size());
    }
    
    /**
     * exam q.4
     *   status in range
     */
    public void examQ4() {
        System.out.println("\n  exam q.3 - log on Day");
        
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(LogFile);
        
        int cnt = la.countUniqueIPsInRange(300,399);
        System.out.println("log count in status 300-399 is " + cnt);
    }
}
