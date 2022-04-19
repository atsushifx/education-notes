import java.util.*;

/**
 * Tester
 *   test Apache access log class
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * @date    2022-02-23
 */

public class Tester
{
    /**
     * access log file for test
     */
    private String logfile = "logs/short-test_log";
       
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        System.out.println("\n  test access.log analyze");
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile(logfile);
        la.printAll();
    }
    
    /**
     * testUniqueIP
     */
    public void testUniqueIP() {
        System.out.println("\n  test count uniuque IPs");
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile(logfile);
        int ipnum = la.countUniqueIPs();
        
        System.out.println("unique IP is " + ipnum);
    }
    
    /**
     * testPrintchkStatus
     *   check status & print higher than
     */
    public void testPrintchkStatus() {
        System.out.println("\n  test print with check status");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(logfile);
        la.printAllHigherThanNum(300);
    }
    
    
    /**
     * testVisitsOnDay
     *   指定した日のuniqueIPリストを取得
     */
    public void testVisitsOnDay() {
        System.out.println("\n  test unique ip addr list on same day");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(logfile);
        
        ArrayList<String> ipList = la.uniqueIPVisitsOnDay("Sep 30");
        for (String ip : ipList) {
            System.out.println(ip);
        }
    }
    
    /** 
     * testUniqueIPsInRange
     */
    public void testUniqueIPsInRange() {
        System.out.println("\n  test unique ip count on status code in range");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(logfile);
        
        int ipnum = la.countUniqueIPsInRange(200,299);
        System.out.println("unique IP is " + ipnum);
    }
}
